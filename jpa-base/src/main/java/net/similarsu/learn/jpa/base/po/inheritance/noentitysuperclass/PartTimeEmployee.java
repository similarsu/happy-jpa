package net.similarsu.learn.jpa.base.po.inheritance.noentitysuperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PartTimeEmployee extends Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer employeeId;
    protected Float hourlyWage;

    public Float getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Float hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
