package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.CityRequestDTO;
import com.rfheka.rfheka.dto.CityResponseDTO;
import com.rfheka.rfheka.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponseDTO> addCity(@RequestBody CityRequestDTO dto) {
        CityResponseDTO response = cityService.addCity(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CityResponseDTO>> getAllCities() {
        List<CityResponseDTO> cityList = cityService.getAllCities();
        return ResponseEntity.ok(cityList);
    }
}
