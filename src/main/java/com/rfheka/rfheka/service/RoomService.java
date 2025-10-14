package com.rfheka.rfheka.service;

import com.rfheka.rfheka.dto.RoomRequestDTO;
import com.rfheka.rfheka.dto.RoomResponseDTO;
import com.rfheka.rfheka.entity.Amenity;
import com.rfheka.rfheka.entity.City;
import com.rfheka.rfheka.entity.Facility;
import com.rfheka.rfheka.entity.Room;
import com.rfheka.rfheka.repository.AmenityRepository;
import com.rfheka.rfheka.repository.CityRepository;
import com.rfheka.rfheka.repository.FacilityRepository;
import com.rfheka.rfheka.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final CityRepository cityRepository;
    private final AmenityRepository amenityRepository;

    public RoomResponseDTO addRoom(RoomRequestDTO dto) {
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

        Room room = new Room();
        room.setName(dto.getName());
        room.setAddress(dto.getAddress());
        room.setContactNumber(dto.getContactNumber());
        room.setRating(dto.getRating());
        room.setCity(city);
        room.setAmenities(amenities);

        Room saved = roomRepository.save(room);
        return mapToResponse(saved);
    }

    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<RoomResponseDTO> getRoomsByCity(Long cityId) {
        return roomRepository.findByCityId(cityId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RoomResponseDTO mapToResponse(Room r) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setAddress(r.getAddress());
        dto.setContactNumber(r.getContactNumber());
        dto.setRating(r.getRating());
        dto.setCityId(r.getCity().getId());
        dto.setCityName(r.getCity().getName());
        dto.setAmenities(r.getAmenities().stream()
                .map(Amenity::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}
