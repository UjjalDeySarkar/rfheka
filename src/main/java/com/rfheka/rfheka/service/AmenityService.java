package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.AmenityRequestDTO;
import com.rfheka.rfheka.dto.AmenityResponseDTO;
import com.rfheka.rfheka.dto.SpecializationRequestDTO;
import com.rfheka.rfheka.dto.SpecializationResponseDTO;
import com.rfheka.rfheka.entity.Amenity;
import com.rfheka.rfheka.entity.Specialization;
import com.rfheka.rfheka.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityService {
    private final AmenityRepository amenityRepository;

    public AmenityResponseDTO addAmenity(AmenityRequestDTO dto) {
        if (amenityRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Amenity already exists");
        }

        Amenity amenity = new Amenity();
        amenity.setName(dto.getName());
        Amenity saved = amenityRepository.save(amenity);

        AmenityResponseDTO response = new AmenityResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        return response;
    }

    public List<AmenityResponseDTO> getAllAmenity() {
        return amenityRepository.findAll().stream().map(spec -> {
            AmenityResponseDTO dto = new AmenityResponseDTO();
            dto.setId(spec.getId());
            dto.setName(spec.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
