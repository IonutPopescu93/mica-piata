package com.sda.project.service;

import com.sda.project.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

//    @Autowired
//    ProductService productService;
//
//    @Test
//    void update() {
//        // given
//        // create pet in db
//        ProductDto productDto = new ProductDto();
//        productDto.setProductName("mere");
//        productService.save(productDto);
//
//        ProductDto updateData = new ProductDto();
//        updateData.setProductName("pere");
//
//        // when
//        productService.update();
//
//        // then
//        // find update product by id
//        ProductDto actualProduct = productService.findById(id);
//        // check that product was update
//        assertThat(actualProduct.getProductName().isEqualTo(expectedName);
//    }
}