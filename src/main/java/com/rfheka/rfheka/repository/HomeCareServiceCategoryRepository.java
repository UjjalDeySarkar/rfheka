package com.rfheka.rfheka.repository;

import com.rfheka.rfheka.entity.HomeCareServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeCareServiceCategoryRepository extends JpaRepository<HomeCareServiceCategory, Long> {
    Optional<HomeCareServiceCategory> findByName(String name);
    boolean existsByName(String name);
}
