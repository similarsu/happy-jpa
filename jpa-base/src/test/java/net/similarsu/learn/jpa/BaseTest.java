package net.similarsu.learn.jpa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BaseTest {
    private static EntityManagerFactory entityManagerFactory;

    protected static EntityManager entityManager;

    private EntityTransaction entityTransaction;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
        entityManagerFactory= Persistence.createEntityManagerFactory("school_crm");
        entityManager=entityManagerFactory.createEntityManager();
    };

    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass");
        if(entityManager!=null){
            entityManager.close();
        }
        if(entityManagerFactory!=null){
            entityManagerFactory.close();
        }
    };

    @Before
    public void before(){
        System.out.println("@Before");
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    @After
    public void after(){
        System.out.println("@After");
        entityTransaction.commit();
    }

}
