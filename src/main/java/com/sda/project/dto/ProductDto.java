package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String productName;
    private String shortDescription;
    private String fullDescription;
    private String category;
    private String price;
    private String unitsInStock;
    private boolean isAvailable;

    private String photo;


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Transient
    public String getPhotoImagePath() {
        if (photo == null || id == null) return null;

        return "/product-photos/" + id + "/" + photo;
    }

}
