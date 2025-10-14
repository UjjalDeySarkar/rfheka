package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.AmenityRequestDTO;
import com.rfheka.rfheka.dto.AmenityResponseDTO;
import com.rfheka.rfheka.dto.SpecializationRequestDTO;
import com.rfheka.rfheka.dto.SpecializationResponseDTO;
import com.rfheka.rfheka.service.AmenityService;
import com.rfheka.rfheka.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenity")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @PostMapping
    public ResponseEntity<AmenityResponseDTO> addAmenity(@RequestBody AmenityRequestDTO dto) {
        return ResponseEntity.ok(amenityService.addAmenity(dto));
    }

    @GetMapping
    public ResponseEntity<List<AmenityResponseDTO>> getAllAmenity() {
        return ResponseEntity.ok(amenityService.getAllAmenity());
    }
}
