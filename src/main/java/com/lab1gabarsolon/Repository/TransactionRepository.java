package com.lab1gabarsolon.Repository;

import com.lab1gabarsolon.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
