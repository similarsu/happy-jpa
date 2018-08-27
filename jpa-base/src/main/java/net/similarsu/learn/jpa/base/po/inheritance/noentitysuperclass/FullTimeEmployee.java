package net.similarsu.learn.jpa.base.po.inheritance.noentitysuperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FullTimeEmployee extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer employeeId;

    protected Integer salary;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
