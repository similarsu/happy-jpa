package net.similarsu.learn.jpa.inheritence;

import net.similarsu.learn.jpa.BaseTest;
import net.similarsu.learn.jpa.base.po.inheritance.noentitysuperclass.FullTimeEmployee;
import net.similarsu.learn.jpa.base.po.inheritance.noentitysuperclass.PartTimeEmployee;
import org.junit.Test;

public class NoEntitySupperClassTest extends BaseTest {

    @Test
    public void test(){

    }

    @Test
    public void persist(){
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
        fullTimeEmployee.setName("John");
        fullTimeEmployee.setSalary(1000);
        entityManager.persist(fullTimeEmployee);

        PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
        partTimeEmployee.setName("Lily");
        partTimeEmployee.setHourlyWage(100f);
        entityManager.persist(partTimeEmployee);
    }
}
