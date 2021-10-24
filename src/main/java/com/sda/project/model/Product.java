package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productName;

    private String description;

    private Category category;

    private Double price;

    private Double unitsInStock;

}
