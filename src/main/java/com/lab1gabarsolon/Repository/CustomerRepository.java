package com.lab1gabarsolon.Repository;

import com.lab1gabarsolon.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
