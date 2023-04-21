package com.smartphones.Repository;

import com.smartphones.Model.Display;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DisplayRepository extends JpaRepository<Display, Long> {
    public List<Display> findByTypeContaining(String typeInfix, Pageable pageable);
    public List<Display> findByTypeContainingAndSize(String typeInfix, Double size, Pageable pageable);
    public List<Display> findByTypeContainingAndSizeAndResolutionWidthAndResolutionHeight(
            String typeInfix, Double size, Integer resolutionWidth, Integer resolutionHeight,
            Pageable pageable);
}
