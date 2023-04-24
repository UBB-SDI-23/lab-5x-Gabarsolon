package com.smartphones.Repository;

import com.smartphones.Model.Smartphone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
    List<Smartphone> findByPriceGreaterThan(BigDecimal price, Pageable pageable);
    List<Smartphone> findByBrandContainingIgnoreCase(String brand, Pageable pageable);
    List<Smartphone> findByBrandContainingIgnoreCaseAndModelContainingIgnoreCase(String brand, String model, Pageable pageable);
    List<Smartphone> findByBrandContainingIgnoreCaseAndModelContainingIgnoreCaseAndPrice(String brand, String model, BigDecimal price, Pageable pageable);

}