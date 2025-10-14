package com.rfheka.rfheka.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CityResponseDTO {
    private Long id;
    private String name;
    private List<Long> hospitalIds; // or List<HospitalResponseDTO> if you need detailed info
    private List<Long> hotelIds; // or List<HotelResponseDTO> if you need detailed info
    private List<Long> homeCareServiceIds; // or List<HomeCareServiceResponseDTO> if you need detailed info
}
