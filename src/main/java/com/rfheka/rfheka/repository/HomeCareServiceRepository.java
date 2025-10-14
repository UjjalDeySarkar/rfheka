package com.rfheka.rfheka.repository;

import com.rfheka.rfheka.entity.HomeCareService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeCareServiceRepository extends JpaRepository<HomeCareService, Long> {
    List<HomeCareService> findByCityId(Long cityId);
}
