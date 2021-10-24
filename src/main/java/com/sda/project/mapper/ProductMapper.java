package com.sda.project.mapper;

import com.sda.project.dto.ProductDto;
import com.sda.project.model.Category;
import com.sda.project.model.Product;
import org.springframework.stereotype.Service;


@Service
public class ProductMapper {


    public Product map(ProductDto productDto) {
        Product product = new Product();

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setCategory(Category.valueOf(productDto.getCategory()));
        product.setPrice(Double.valueOf(productDto.getPrice()));
        product.setUnitsInStock(Double.valueOf(productDto.getUnitsInStock()));

        return product;
    }

}
