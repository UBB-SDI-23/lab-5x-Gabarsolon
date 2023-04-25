package com.smartphones.Repository;

import com.smartphones.Model.CustomerTotalPriceDto;
import com.smartphones.Model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT new com.smartphones.Model.CustomerTotalPriceDto(c.id, c.firstName, c.lastName, c.phoneNumber, sum(s.price)) " +
            "FROM Transaction t INNER JOIN t.customer c INNER JOIN t.smartphone s " +
            "GROUP BY c.id, c.firstName, c.lastName, c.phoneNumber " +
            "ORDER BY sum(s.price) DESC")
    List<CustomerTotalPriceDto> findAllCustomersOrderedDescByTotalPriceOfBoughtSmartphones(Pageable pageable);
}
