package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.HospitalRequestDTO;
import com.rfheka.rfheka.dto.HospitalResponseDTO;
import com.rfheka.rfheka.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalResponseDTO> addHospital(@RequestBody HospitalRequestDTO dto) {
        return ResponseEntity.ok(hospitalService.addHospital(dto));
    }

    @GetMapping
    public ResponseEntity<List<HospitalResponseDTO>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<HospitalResponseDTO>> getHospitalsByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(hospitalService.getHospitalsByCity(cityId));
    }
}
