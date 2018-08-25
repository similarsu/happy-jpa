package net.similarsu.learn.jpa;

import net.similarsu.learn.jpa.base.po.Contact;
import org.junit.Test;

import javax.persistence.EntityTransaction;

public class ContactRepoTest extends BaseTest{

    @Test
    public void test(){

    }

    @Test
    public void testPersist(){
        Contact contact = new Contact();
        contact.setEmail("163");
        /*Validator validator=Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Contact>> violations=validator.validate(contact);*/
        entityManager.persist(contact);
    }
}
