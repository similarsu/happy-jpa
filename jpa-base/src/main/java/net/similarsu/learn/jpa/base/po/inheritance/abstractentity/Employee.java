package net.similarsu.learn.jpa.base.po.inheritance.abstractentity;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(
        name = "emp_type",
        discriminatorType = DiscriminatorType.INTEGER
)
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer employeeId;

    protected String name;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
