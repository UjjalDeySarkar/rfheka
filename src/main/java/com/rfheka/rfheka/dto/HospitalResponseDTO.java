package com.rfheka.rfheka.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HospitalResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private Double rating;
    private Long cityId;
    private String cityName;
    private Set<String> specializations;
}
