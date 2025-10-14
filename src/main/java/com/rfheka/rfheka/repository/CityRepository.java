package com.rfheka.rfheka.repository;

import com.rfheka.rfheka.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(String name);
}
