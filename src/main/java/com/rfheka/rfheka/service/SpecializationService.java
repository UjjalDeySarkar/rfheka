package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.SpecializationRequestDTO;
import com.rfheka.rfheka.dto.SpecializationResponseDTO;
import com.rfheka.rfheka.entity.Specialization;
import com.rfheka.rfheka.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationResponseDTO addSpecialization(SpecializationRequestDTO dto) {
        if (specializationRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Specialization already exists");
        }

        Specialization specialization = new Specialization();
        specialization.setName(dto.getName());
        Specialization saved = specializationRepository.save(specialization);

        SpecializationResponseDTO response = new SpecializationResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        return response;
    }

    public List<SpecializationResponseDTO> getAllSpecializations() {
        return specializationRepository.findAll().stream().map(spec -> {
            SpecializationResponseDTO dto = new SpecializationResponseDTO();
            dto.setId(spec.getId());
            dto.setName(spec.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
