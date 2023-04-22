package com.smartphones.Repository;

import com.smartphones.Model.Smartphone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
    List<Smartphone> findByPriceGreaterThan(BigDecimal price, Pageable pageable);
}