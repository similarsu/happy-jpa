package net.similarsu.learn.spring.data.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void add(){
        Customer customer2=new Customer("Jim","Green");
        customerRepository.save(customer2);
        Customer customer1=new Customer("Jim111111111111","Green");
        customerRepository.save(customer1);
    }
}
