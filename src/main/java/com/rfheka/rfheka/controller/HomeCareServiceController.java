package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.HomeCareServiceRequestDTO;
import com.rfheka.rfheka.dto.HomeCareServiceResponseDTO;
import com.rfheka.rfheka.service.HomeCareServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homecare/services")
@RequiredArgsConstructor
public class HomeCareServiceController {

    private final HomeCareServiceService service;

    @PostMapping
    public ResponseEntity<HomeCareServiceResponseDTO> addService(@RequestBody HomeCareServiceRequestDTO dto) {
        return ResponseEntity.ok(service.addService(dto));
    }

    @GetMapping
    public ResponseEntity<List<HomeCareServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(service.getAllServices());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<HomeCareServiceResponseDTO>> getServicesByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(service.getServicesByCity(cityId));
    }
}
