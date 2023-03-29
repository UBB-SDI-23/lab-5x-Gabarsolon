package com.lab1gabarsolon.Repository;

import com.lab1gabarsolon.Model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {

}