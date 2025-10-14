package com.rfheka.rfheka.controller;

import com.rfheka.rfheka.dto.HomeCareCategoryRequestDTO;
import com.rfheka.rfheka.dto.HomeCareCategoryResponseDTO;
import com.rfheka.rfheka.service.HomeCareCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homecare/categories")
@RequiredArgsConstructor
public class HomeCareCategoryController {

    private final HomeCareCategoryService categoryService;

    @PostMapping
    public ResponseEntity<HomeCareCategoryResponseDTO> addCategory(@RequestBody HomeCareCategoryRequestDTO dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }

    @GetMapping
    public ResponseEntity<List<HomeCareCategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
