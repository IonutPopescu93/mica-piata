package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String productName;
    private String description;
    private String category;
    private String price;
    private String unitsInStock;

}
