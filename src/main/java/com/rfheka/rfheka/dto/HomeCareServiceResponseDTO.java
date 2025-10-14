package com.rfheka.rfheka.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HomeCareServiceResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String providerName;
    private String contactNumber;
    private Long cityId;
    private String cityName;
    private Set<String> categories;
}
