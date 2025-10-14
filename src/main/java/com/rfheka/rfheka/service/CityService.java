package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.CityRequestDTO;
import com.rfheka.rfheka.dto.CityResponseDTO;
import com.rfheka.rfheka.entity.City;
import com.rfheka.rfheka.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public CityResponseDTO addCity(CityRequestDTO dto) {
        if (cityRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("City name already exists");
        }
        City city = new City();
        city.setName(dto.getName());
        City saved = cityRepository.save(city);

        CityResponseDTO response = new CityResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setHospitalIds(List.of());
        response.setHotelIds(List.of());
        return response;
    }

    public List<CityResponseDTO> getAllCities() {
        return cityRepository.findAll().stream().map(city -> {
            CityResponseDTO dto = new CityResponseDTO();
            dto.setId(city.getId());
            dto.setName(city.getName());
            if (city.getHospitals() != null) {
                dto.setHospitalIds(
                        city.getHospitals()
                                .stream()
                                .map(h -> h.getId())
                                .collect(Collectors.toList())
                );
            } else {
                dto.setHospitalIds(List.of());
            }
            if (city.getHotels() != null) {
                dto.setHotelIds(
                        city.getHotels()
                                .stream()
                                .map(h -> h.getId())
                                .collect(Collectors.toList())
                );
            } else {
                dto.setHotelIds(List.of());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}