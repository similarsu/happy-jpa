package net.similarsu.learn.jpa.base.po.inheritance.mappedsuperclass;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
