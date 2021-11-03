package com.sda.project.mapper;

import com.sda.project.dto.ProductDto;
import com.sda.project.model.Category;
import com.sda.project.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // toEntity (ProductDto productDto)
    public Product map(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setCategory(Category.valueOf(productDto.getCategory()));
        product.setPrice(Double.valueOf(productDto.getPrice()));
        product.setUnitsInStock(Double.valueOf(productDto.getUnitsInStock()));
        return product;
    }

    public ProductDto map(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setDescription(entity.getDescription());
        dto.setCategory(String.valueOf(entity.getCategory()));
        dto.setPrice(String.valueOf(entity.getPrice()));
        dto.setUnitsInStock(String.valueOf(entity.getUnitsInStock()));
        return dto;
    }

    public Product update(Product productToUpdte, ProductDto data) {
        productToUpdte.setProductName(data.getProductName());
        productToUpdte.setDescription(data.getDescription());
        productToUpdte.setPrice(Double.valueOf(data.getPrice()));
        return productToUpdte;
    }
}
