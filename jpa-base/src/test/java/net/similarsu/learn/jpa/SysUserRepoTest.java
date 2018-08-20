package net.similarsu.learn.jpa;

import net.similarsu.learn.jpa.base.po.User;
import org.junit.Test;

import javax.persistence.EntityTransaction;

public class SysUserRepoTest extends BaseTest{

    @Test
    public void persist(){
        User user=new User();
        user.setName("test");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }
}
