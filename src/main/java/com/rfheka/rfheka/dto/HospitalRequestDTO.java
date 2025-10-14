package com.rfheka.rfheka.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HospitalRequestDTO {
    private String name;
    private String address;
    private String contactNumber;
    private Double rating;
    private Long cityId;  // important to link hospital with city
    private Set<String> specializations; // names of specializations
}
