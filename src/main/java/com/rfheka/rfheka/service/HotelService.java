package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.HospitalResponseDTO;
import com.rfheka.rfheka.dto.HotelRequestDTO;
import com.rfheka.rfheka.dto.HotelResponseDTO;
import com.rfheka.rfheka.entity.*;
import com.rfheka.rfheka.repository.AmenityRepository;
import com.rfheka.rfheka.repository.CityRepository;
import com.rfheka.rfheka.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final AmenityRepository amenityRepository;

    public HotelResponseDTO addHotel(HotelRequestDTO dto) {
        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found"));

        Set<Amenity> amenities = dto.getAmenities().stream()
                .map(name -> amenityRepository.findByName(name)
                        .orElseGet(() -> {
                            Amenity newAmenity = new Amenity();
                            newAmenity.setName(name);
                            return amenityRepository.save(newAmenity);
                        }))
                .collect(Collectors.toSet());

        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setContactNumber(dto.getContactNumber());
        hotel.setRating(dto.getRating());
        hotel.setCity(city);
        hotel.setAmenities(amenities);

        Hotel saved = hotelRepository.save(hotel);

        return mapToResponse(saved);
    }

    public List<HotelResponseDTO> getAllHotel() {
        return hotelRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDTO> getHotelsByCity(Long cityId) {
        return hotelRepository.findByCityId(cityId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HotelResponseDTO mapToResponse(Hotel h) {
        HotelResponseDTO dto = new HotelResponseDTO();
        dto.setId(h.getId());
        dto.setName(h.getName());
        dto.setAddress(h.getAddress());
        dto.setContactNumber(h.getContactNumber());
        dto.setRating(h.getRating());
        dto.setCityId(h.getCity().getId());
        dto.setCityName(h.getCity().getName());
        dto.setAmenities(
                h.getAmenities()
                        .stream()
                        .map(Amenity::getName)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
