package com.rfheka.rfheka.repository;

import com.rfheka.rfheka.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByCityId(Long cityId);
}
