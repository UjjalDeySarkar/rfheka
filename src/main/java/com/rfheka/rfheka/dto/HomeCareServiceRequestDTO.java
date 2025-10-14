package com.rfheka.rfheka.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HomeCareServiceRequestDTO {
    private String name;
    private String description;
    private Double price;
    private String providerName;
    private String contactNumber;
    private Long cityId;
    private Set<String> categories; // category names
}
