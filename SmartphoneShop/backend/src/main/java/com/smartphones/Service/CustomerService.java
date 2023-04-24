package com.smartphones.Service;

import com.smartphones.Model.Customer;
import com.smartphones.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService extends EntityService<Customer>{
    @Autowired
    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public List<Customer> getCustomersByFirstNameLastNameEmail(String query){
        CustomerRepository customerRepository = (CustomerRepository)repository;
        PageRequest pageRequest = PageRequest.of(0, 20);

        String[] queryStrings = query.split(" ");
        Integer length = queryStrings.length;

        String firstName = queryStrings[0];
        if(length < 2)
            return customerRepository.findByFirstNameContainingIgnoreCase(firstName, pageRequest);

        String lastName = queryStrings[1];
        if(length < 3)
            return customerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName, pageRequest);

        String email = queryStrings[2];
        return customerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndEmailContaining(
                firstName, lastName, email, pageRequest
        );
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
