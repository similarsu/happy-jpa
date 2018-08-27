package net.similarsu.learn.jpa.inheritence;

import net.similarsu.learn.jpa.BaseTest;
import net.similarsu.learn.jpa.base.po.inheritance.abstractentity.FullTimeEmployee;
import net.similarsu.learn.jpa.base.po.inheritance.abstractentity.PartTimeEmployee;
import org.junit.Test;

public class AbstractEntityTest extends BaseTest {

    @Test
    public void test(){

    }

    @Test
    public void persist(){
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
        fullTimeEmployee.setName("john");
        fullTimeEmployee.setSalary(3000);
        entityManager.persist(fullTimeEmployee);
        System.out.println(fullTimeEmployee.hashCode());

        PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
        partTimeEmployee.setName("Lily");
        partTimeEmployee.setHourlyWage(100.0f);
        entityManager.persist(partTimeEmployee);
    }
}
