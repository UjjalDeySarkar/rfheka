package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.RoomRequestDTO;
import com.rfheka.rfheka.dto.RoomResponseDTO;
import com.rfheka.rfheka.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> addRoom(@RequestBody RoomRequestDTO dto) {
        return ResponseEntity.ok(roomService.addRoom(dto));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(roomService.getRoomsByCity(cityId));
    }
}
