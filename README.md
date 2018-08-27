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

### @DiscriminatorColumn

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

## @MappedSuperclass

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

## no entity superclass

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