package com.rfheka.rfheka.repository;

import com.rfheka.rfheka.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCityId(Long cityId);
}
