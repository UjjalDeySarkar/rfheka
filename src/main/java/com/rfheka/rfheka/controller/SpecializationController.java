package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.SpecializationRequestDTO;
import com.rfheka.rfheka.dto.SpecializationResponseDTO;
import com.rfheka.rfheka.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping
    public ResponseEntity<SpecializationResponseDTO> addSpecialization(@RequestBody SpecializationRequestDTO dto) {
        return ResponseEntity.ok(specializationService.addSpecialization(dto));
    }

    @GetMapping
    public ResponseEntity<List<SpecializationResponseDTO>> getAllSpecializations() {
        return ResponseEntity.ok(specializationService.getAllSpecializations());
    }
}
