package net.similarsu.learn.spring.data.jpa;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceTest extends ApplicationTest{

    @Autowired
    private CustomerService customerService;

    @Test
    public void save(){
        customerService.add();
    }
}
