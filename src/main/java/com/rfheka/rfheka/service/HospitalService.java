package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.HospitalRequestDTO;
import com.rfheka.rfheka.dto.HospitalResponseDTO;
import com.rfheka.rfheka.entity.City;
import com.rfheka.rfheka.entity.Hospital;
import com.rfheka.rfheka.entity.Specialization;
import com.rfheka.rfheka.repository.CityRepository;
import com.rfheka.rfheka.repository.HospitalRepository;
import com.rfheka.rfheka.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final CityRepository cityRepository;
    private final SpecializationRepository specializationRepository;

    public HospitalResponseDTO addHospital(HospitalRequestDTO dto) {
        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));

        Set<Specialization> specializations = dto.getSpecializations().stream()
                .map(name -> specializationRepository.findByName(name)
                        .orElseGet(() -> {
                            Specialization newSpec = new Specialization();
                            newSpec.setName(name);
                            return specializationRepository.save(newSpec);
                        }))
                .collect(Collectors.toSet());

        Hospital hospital = new Hospital();
        hospital.setName(dto.getName());
        hospital.setAddress(dto.getAddress());
        hospital.setContactNumber(dto.getContactNumber());
        hospital.setRating(dto.getRating());
        hospital.setCity(city);
        hospital.setSpecializations(specializations);

        Hospital saved = hospitalRepository.save(hospital);

        return mapToResponse(saved);
    }

    public List<HospitalResponseDTO> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<HospitalResponseDTO> getHospitalsByCity(Long cityId) {
        return hospitalRepository.findByCityId(cityId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HospitalResponseDTO mapToResponse(Hospital h) {
        HospitalResponseDTO dto = new HospitalResponseDTO();
        dto.setId(h.getId());
        dto.setName(h.getName());
        dto.setAddress(h.getAddress());
        dto.setContactNumber(h.getContactNumber());
        dto.setRating(h.getRating());
        dto.setCityId(h.getCity().getId());
        dto.setCityName(h.getCity().getName());
        dto.setSpecializations(
                h.getSpecializations()
                        .stream()
                        .map(Specialization::getName)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
