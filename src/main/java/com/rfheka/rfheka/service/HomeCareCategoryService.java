package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.HomeCareCategoryRequestDTO;
import com.rfheka.rfheka.dto.HomeCareCategoryResponseDTO;
import com.rfheka.rfheka.entity.HomeCareServiceCategory;
import com.rfheka.rfheka.repository.HomeCareServiceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeCareCategoryService {

    private final HomeCareServiceCategoryRepository categoryRepository;

    public HomeCareCategoryResponseDTO addCategory(HomeCareCategoryRequestDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Category already exists");
        }
        HomeCareServiceCategory category = new HomeCareServiceCategory();
        category.setName(dto.getName());
        HomeCareServiceCategory saved = categoryRepository.save(category);

        HomeCareCategoryResponseDTO response = new HomeCareCategoryResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        return response;
    }

    public List<HomeCareCategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> {
                    HomeCareCategoryResponseDTO dto = new HomeCareCategoryResponseDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
