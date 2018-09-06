package net.similarsu.learn.jpa;

import net.similarsu.learn.jpa.base.po.Color;
import net.similarsu.learn.jpa.base.po.People;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class PeopleReopTest extends BaseTest {

    @Test
    public void test(){

    }

    @Test
    public void persist(){
        People people = new People("color");
        people.setName("test1");
        people.setHeight(1.80f);
        people.setWeight(170f);
        people.setColor(Color.WHITE);
        people.setBirth(LocalDate.of(1989,11,2));
        people.setAlive(false);
        entityManager.persist(people);
    }

    @Test
    public void load(){
        People people=entityManager.find(People.class,1l);
        assert people.getAlive() == true;
    }


    @Test
    public void findByName(){
        List<People> peopleList=
                entityManager.createQuery("select p from net.similarsu.learn.jpa.base.po.People p where name=:name")
                .setParameter("name","test")
                .setMaxResults(10)
                .getResultList();
        assert peopleList.size()>0;

    }

    @Test
    public void findByNamePosition(){
        List<People> peopleList=
                entityManager.createQuery("select p from net.similarsu.learn.jpa.base.po.People p where name=?1")
                        .setParameter(1,"test")
                        .setMaxResults(10)
                        .getResultList();
        assert peopleList.size()>0;

    }

    @Test
    public void findByNameQuery(){
        List<People> peopleList=
                entityManager.createNamedQuery("findByName")
                        .setParameter("name","test")
                        .setMaxResults(10)
                        .getResultList();
        assert peopleList.size()>0;
    }

    @Test
    public void findAll(){
        CriteriaQuery<People> criteriaQuery= criteriaBuilder.createQuery(People.class);
        Root<People> peopleRoot = criteriaQuery.from(People.class);
        //criteriaQuery.select(peopleRoot);
        TypedQuery<People> peopleTypedQuery = entityManager.createQuery(criteriaQuery);
        List<People> peopleList = peopleTypedQuery.getResultList();
        assert peopleList.size() > 0 ;
    }

}
