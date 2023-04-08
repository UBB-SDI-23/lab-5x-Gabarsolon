package com.smartphones.Service;

import com.smartphones.Model.Customer;
import com.smartphones.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService extends EntityService<Customer>{
    @Autowired
    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public void update(Customer newCustomer, Long id){
        repository.findById(id).map(customer -> {
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
            customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setEmail(newCustomer.getEmail());
            return repository.save(customer);
        }).orElseGet(()->{
            newCustomer.setId(id);
            return repository.save(newCustomer);
        });
    }
}
