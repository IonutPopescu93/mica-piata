package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String shortDescription;

    private String fullDescription;
    @Enumerated(EnumType.STRING)
    private Category category;

    private Double price;
    private Double unitsInStock;

    private String photo;
    private boolean isAvailable;

}
