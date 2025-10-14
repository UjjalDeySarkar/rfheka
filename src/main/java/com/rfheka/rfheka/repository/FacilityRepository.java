package com.rfheka.rfheka.repository;

import com.rfheka.rfheka.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Optional<Facility> findByName(String name);
    boolean existsByName(String name);
}
