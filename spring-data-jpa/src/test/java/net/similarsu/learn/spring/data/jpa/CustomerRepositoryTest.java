package net.similarsu.learn.spring.data.jpa;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepositoryTest extends ApplicationTest{

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void save(){
        Customer customer=new Customer("Jim","Green");
        customerRepository.save(customer);
    }
}
