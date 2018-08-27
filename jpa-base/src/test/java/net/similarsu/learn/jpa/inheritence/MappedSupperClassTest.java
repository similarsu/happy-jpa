package net.similarsu.learn.jpa.inheritence;

import net.similarsu.learn.jpa.BaseTest;
import net.similarsu.learn.jpa.base.po.inheritance.mappedsuperclass.FullTimeEmployee;
import net.similarsu.learn.jpa.base.po.inheritance.mappedsuperclass.PartTimeEmployee;
import org.junit.Test;

public class MappedSupperClassTest extends BaseTest {

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
