package com.smartphones.Repository;

import com.smartphones.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    List<Customer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName,
                                                                                      Pageable pageable);
    List<Customer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndEmailContaining(
            String firstName, String lastName, String email, Pageable pageable
    );
}
