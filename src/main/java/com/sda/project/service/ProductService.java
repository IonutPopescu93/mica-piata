package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.ProductDto;
import com.sda.project.mapper.ProductMapper;
import com.sda.project.model.Product;
import com.sda.project.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void save(@ModelAttribute ProductDto productDto) {
        Product product = productMapper.map(productDto);
        productRepository.save(product);
    }

    public List<ProductDto> findAll() {

        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.map(product))
                .collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {

        // find by id from repo
        return productRepository.findById(id)
                .map(product -> productMapper.map(product))
                .orElseThrow(() -> new ResourceNotFoundException("product not found"));
    }

    public void update(ProductDto dto) {

        log.info("updating product with id {} with data {}", dto.getId(), dto);

        productRepository.findById(dto.getId())
                .map(product -> productMapper.update(product, dto))
                .map(updatedProduct -> productRepository.save(updatedProduct))
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("product not found");
                });
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
