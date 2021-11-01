package com.sda.project.service;

import com.sda.project.dto.ProductDto;
import com.sda.project.mapper.ProductMapper;
import com.sda.project.model.Product;
import com.sda.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public void save(@ModelAttribute ProductDto productDto) {
        Product product = productMapper.map(productDto);
        productRepository.save(product);
    }
}
