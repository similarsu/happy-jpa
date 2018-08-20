package net.similarsu.learn.jpa;

import net.similarsu.learn.jpa.base.po.Color;
import net.similarsu.learn.jpa.base.po.People;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class PeopleReopTest extends BaseTest {

    @Test
    public void test(){

    }

    @Test
    public void persist(){
        People people = new People();
        people.setName("test");
        people.setHeight(1.80f);
        people.setWeight(170f);
        people.setColor(Color.WHITE);
        people.setBirth(LocalDate.of(1989,11,2));
        EntityTransaction transaction=entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(people);
        transaction.commit();
    }

}
