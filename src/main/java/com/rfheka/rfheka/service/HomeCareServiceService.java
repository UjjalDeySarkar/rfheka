package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.HomeCareServiceRequestDTO;
import com.rfheka.rfheka.dto.HomeCareServiceResponseDTO;
import com.rfheka.rfheka.entity.City;
import com.rfheka.rfheka.entity.HomeCareService;
import com.rfheka.rfheka.entity.HomeCareServiceCategory;
import com.rfheka.rfheka.repository.CityRepository;
import com.rfheka.rfheka.repository.HomeCareServiceCategoryRepository;
import com.rfheka.rfheka.repository.HomeCareServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeCareServiceService {

    private final HomeCareServiceRepository serviceRepository;
    private final CityRepository cityRepository;
    private final HomeCareServiceCategoryRepository categoryRepository;

    public HomeCareServiceResponseDTO addService(HomeCareServiceRequestDTO dto) {
        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));

        Set<HomeCareServiceCategory> categories = dto.getCategories().stream()
                .map(name -> categoryRepository.findByName(name)
                        .orElseGet(() -> {
                            HomeCareServiceCategory newCat = new HomeCareServiceCategory();
                            newCat.setName(name);
                            return categoryRepository.save(newCat);
                        }))
                .collect(Collectors.toSet());

        HomeCareService service = new HomeCareService();
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        service.setProviderName(dto.getProviderName());
        service.setContactNumber(dto.getContactNumber());
        service.setCity(city);
        service.setCategories(categories);

        HomeCareService saved = serviceRepository.save(service);
        return mapToResponse(saved);
    }

    public List<HomeCareServiceResponseDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<HomeCareServiceResponseDTO> getServicesByCity(Long cityId) {
        return serviceRepository.findByCityId(cityId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HomeCareServiceResponseDTO mapToResponse(HomeCareService s) {
        HomeCareServiceResponseDTO dto = new HomeCareServiceResponseDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setDescription(s.getDescription());
        dto.setPrice(s.getPrice());
        dto.setProviderName(s.getProviderName());
        dto.setContactNumber(s.getContactNumber());
        dto.setCityId(s.getCity().getId());
        dto.setCityName(s.getCity().getName());
        dto.setCategories(
                s.getCategories()
                        .stream()
                        .map(HomeCareServiceCategory::getName)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
