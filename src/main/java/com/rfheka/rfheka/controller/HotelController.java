package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.HotelRequestDTO;
import com.rfheka.rfheka.dto.HotelResponseDTO;
import com.rfheka.rfheka.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody HotelRequestDTO dto) {
        return ResponseEntity.ok(hotelService.addHotel(dto));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<HotelResponseDTO>> getHotelsByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(hotelService.getHotelsByCity(cityId));
    }
}