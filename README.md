# 概述

```
该工程有jpa-base模块构成
```
# jpa-base

```
jpa基础相关示例代码，基于hibernate jpa provider
```

# 疑问

## 1、final

```
The class must not be declared final.
but when i declared final, success, why?
```
## 2、 a public or protected, no-argument constructor

```
The class must have a public or protected, no-argument constructor.
it see no affect for use.
```

## 3、启动验证

```
1、在persistence.xml 使用validation-mode，可不配置，使用默认值AUTO
        <!--
         * AUTO 当验证提供程序在类路径上存在时,启用验证(默认).
         * CALLBACK 如果没有可用的验证提供程序,那么启用验证并抛出一个错误.
         * NONE 禁用验证
         -->
        <validation-mode>AUTO</validation-mode>
2、同时引入validation provider
compile group: 'org.glassfish', name: 'javax.el', version: '3.0.1-b10'
```

## 4、主键

**单一**

```
@Entity
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ……
}
```

**复合**

使用@EmbeddedId

```
public final class LineItemKey implements Serializable {
    private Integer customerOrder;
    private int itemId;

    public LineItemKey() {}

    public LineItemKey(Integer order, int itemId) {
        this.setCustomerOrder(order);
        this.setItemId(itemId);
    }

    @Override
    public int hashCode() {
        ……
    }

    @Override
    public boolean equals(Object otherOb) {
        ……
    }

    @Override
    public String toString() {
        ……
    }
    /* Getters and setters */
    ……
}

@Entity
public class LineItem {
    @EmbeddedId
    private LineItemKey lineItemKey;
    ……
}
```

使用@IdClass

```
public final class LineItemKey implements Serializable {
    private Integer customerOrder;
    private int itemId;

    public LineItemKey() {}

    public LineItemKey(Integer order, int itemId) {
        this.setCustomerOrder(order);
        this.setItemId(itemId);
    }

    @Override
    public int hashCode() {
        ……
    }

    @Override
    public boolean equals(Object otherOb) {
        ……
    }

    @Override
    public String toString() {
        ……
    }
    /* Getters and setters */
    ……
}

@Entity
@IdClass(LineItemKey.class)
public class LineItem {
    @Id
    private Integer customerOrder;
    @Id
    private int itemId;
}
```

## 内嵌类

**error**

```
public class ZipCode {
    String zip;
    String plusFour;
}

@Entity
public class Address {
    @Id
    protected long id;
    String street1;
    String street2;
    String city;
    String province;
    ZipCode zipCode;
    String country;
}
```
```
Could not determine type for: net.similarsu.learn.jpa.base.po.ZipCode, at table: Address
```

**@Embeddable**

```
@Embeddable
public class ZipCode {
    String zip;
    String plusFour;
}

@Entity
public class Address {
    @Id
    protected long id;
    String street1;
    String street2;
    String city;
    String province;
    ZipCode zipCode;
    String country;
}
```
you can use @column to change map field like below.
```
@Embeddable
public class ZipCode {
    @Column(name = "zip_code")
    String zip;
    String plusFour;
}
```

**@Embedded**

```
public class ZipCode {
    String zip;
    String plusFour;
}

@Entity
public class Address {
    @Id
    protected long id;
    String street1;
    String street2;
    String city;
    String province;
    @Embedded
    ZipCode zipCode;
    String country;
}
```

**@AttributeOverrides**
```
@Entity
public class Address {
    @Id
    protected long id;
    String street1;
    String street2;
    String city;
    String province;
    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "zip",column = @Column(name = "zip_code"))
                    ,@AttributeOverride(name = "plusFour",column = @Column(name = "plus"))
            }
    )
    ZipCode zipCode;
    String country;
}
```
use @AttributeOverrides to override the field name

**@Embeddable && @Embedded (recommend)**

```
@Embeddable
public class ZipCode {
    String zip;
    String plusFour;
}

@Entity
public class Address {
    @Id
    protected long id;
    String street1;
    String street2;
    String city;
    String province;
    @Embedded
    ZipCode zipCode;
    String country;
}
```

## 继承

### Abstract Entities

```
@Entity
public abstract class Employee {
    @Id
    protected Integer employeeId;
    ...
}
@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;
    ...
}
@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;
}
```

**建表语句**

```
create table Employee (DTYPE varchar(31) not null, employeeId integer not null, name varchar(255),
salary integer, hourlyWage float, primary key (employeeId)) engine=InnoDB
```

会多生成DTYPE字段，用于鉴别子类（自动插入FullTimeEmployee或PartTimeEmployee）


#### @Inheritance

**@Inheritance only effect the supper class which use @Entity**

##### SINGLE_TABLE

**@DiscriminatorColumn**

**改变生成的DTYPE字段**

```
@Entity
@DiscriminatorColumn(
        name = "emp_type",
        discriminatorType = DiscriminatorType.INTEGER
)
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer employeeId;

    ...
}
```

```
create table Employee (DTYPE varchar(31) not null, employeeId integer not null auto_increment, name varchar(255), salary integer, hourlyWage float, primary key (employeeId)) engine=InnoDB
insert into Employee (name, salary, DTYPE) values (?, ?, 'FullTimeEmployee')
insert into Employee (name, hourlyWage, DTYPE) values (?, ?, 'PartTimeEmployee')
```

##### TABLE_PER_CLASS
**like the way use of @MappedSuperclass**

**Support for this strategy is optional and may not be supported by all Java Persistence API providers.**
**The default Java Persistence API provider in GlassFish Server does not support this strategy.**


```
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer employeeId;
    protected String name;
    ……
}
```

**@GeneratedValue(strategy = GenerationType.IDENTITY) must be not use**

```
create table FullTimeEmployee (employeeId integer not null, name varchar(255), salary integer, primary key (employeeId)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table PartTimeEmployee (employeeId integer not null, name varchar(255), hourlyWage float, primary key (employeeId)) engine=InnoDB
select next_val as id_val from hibernate_sequence for update
update hibernate_sequence set next_val= ? where next_val=?
select next_val as id_val from hibernate_sequence for update
update hibernate_sequence set next_val= ? where next_val=?
insert into FullTimeEmployee (name, salary, employeeId) values (?, ?, ?)
insert into PartTimeEmployee (name, hourlyWage, employeeId) values (?, ?, ?)
```

##### JOINED

```
create table Employee (employeeId integer not null auto_increment, name varchar(255), primary key (employeeId)) engine=InnoDB
create table FullTimeEmployee (salary integer, employeeId integer not null, primary key (employeeId)) engine=InnoDB
create table PartTimeEmployee (hourlyWage float, employeeId integer not null, primary key (employeeId)) engine=InnoDB
alter table FullTimeEmployee add constraint FKhswwgg4gj8f6wu56853u2ey7g foreign key (employeeId) references Employee (employeeId)
alter table PartTimeEmployee add constraint FKxakb71b3ngaccwbj5ew739y6 foreign key (employeeId) references Employee (employeeId)

insert into Employee (name) values (?)
insert into FullTimeEmployee (salary, employeeId) values (?, ?)
insert into Employee (name) values (?)
insert into PartTimeEmployee (hourlyWage, employeeId) values (?, ?)
```

### @MappedSuperclass

**@Inheritance can not effect the supper class which use @MappedSuperclass**

```
Mapped superclasses cannot be queried and cannot be used in EntityManager or Query operations.
You must use entity subclasses of the mapped superclass in EntityManager or Query operations.
Mapped superclasses can’t be targets of entity relationships.
Mapped superclasses can be abstract or concrete.
```

```
@MappedSuperclass
public abstract class Employee {
    @Id
    protected Integer employeeId;
    ...
}

@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;
    ...
}

@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;
}
```

**建表语句**
```
create table FullTimeEmployee (employeeId integer not null auto_increment, name varchar(255), salary integer, primary key (employeeId)) engine=InnoDB
create table PartTimeEmployee (employeeId integer not null auto_increment, name varchar(255), hourlyWage float, primary key (employeeId)) engine=InnoDB
```

### no entity superclass

```
Entities may have non-entity superclasses, and these superclasses can be either abstract or concrete.
The state of non-entity superclasses is nonpersistent,
and any state inherited from the non-entity superclass by an entity class is nonpersistent.
Non-entity superclasses may not be used in EntityManager or Query operations.
Any mapping or relationship annotations in non-entity superclasses are ignored.

```

```
public class Employee {

    protected String name;
    ……
}

@Entity
public class FullTimeEmployee extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer employeeId;
    protected Integer salary;


}

@Entity
public class PartTimeEmployee extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer employeeId;
    protected Float hourlyWage;
}
```

**建表语句**
```
create table FullTimeEmployee (employeeId integer not null auto_increment, salary integer, primary key (employeeId)) engine=InnoDB
create table PartTimeEmployee (employeeId integer not null auto_increment, hourlyWage float, primary key (employeeId)) engine=InnoDB
```

## Criteria API && Metamodel

### define metamodel

```
@Entity
public class Pet {
    @Id
    protected Long id;
    protected String name;
    protected String color;
    @ManyToOne
    protected Set<Person> owners;
    ...
}

@StaticMetamodel(Pet.class)
public class Pet_ {

    public static volatile SingularAttribute<Pet, Long> id;
    public static volatile SingularAttribute<Pet, String> name;
    public static volatile SingularAttribute<Pet, String> color;
    public static volatile SetAttribute<Pet, Person> owners;
}
```

### obtain metamodel class

```
Call the getModel method on the query root object
or
Obtain an instance of the Metamodel interface and then pass the entity type to the instance’s entity method
```

```
EntityManager em = ...;
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery cq = cb.createQuery(Pet.class);
Root<Pet> pet = cq.from(Pet.class);
EntityType<Pet> Pet_ = pet.getModel();
```

```
EntityManager em = ...;
Metamodel m = em.getMetamodel();
EntityType<Pet> Pet_ = m.entity(Pet.class);
```
