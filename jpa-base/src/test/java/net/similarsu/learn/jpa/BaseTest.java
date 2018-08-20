package net.similarsu.learn.jpa;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseTest {
    protected EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    @Before
    public void setup(){
        entityManagerFactory= Persistence.createEntityManagerFactory("school_crm");
        entityManager=entityManagerFactory.createEntityManager();
    }

    @After
    public void cleanup(){
        if(entityManager!=null){
            entityManager.close();
        }
        if(entityManagerFactory!=null){
            entityManagerFactory.close();
        }
    }

}
