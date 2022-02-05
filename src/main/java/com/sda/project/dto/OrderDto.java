package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    private Long id;

    private double total;

    private String userFirstName;

    private String userLastName;

    private String userAddressCountry;

    private String userAddressCounty;

    private String userAddressPostalCode;

    private String userAddressLocality;

    private String status;

    public LocalDateTime orderDtodate;
}
