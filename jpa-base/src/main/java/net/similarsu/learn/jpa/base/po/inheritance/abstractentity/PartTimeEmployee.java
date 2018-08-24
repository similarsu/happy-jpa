package net.similarsu.learn.jpa.base.po.inheritance.abstractentity;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;

    public Float getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Float hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}