package com.rfheka.rfheka.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HotelRequestDTO {
    private String name;
    private String address;
    private String contactNumber;
    private Double rating;
    private Long cityId;
    private Set<String> amenities;
}
