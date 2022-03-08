package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.ProductDto;
import com.sda.project.mapper.ProductMapper;
import com.sda.project.model.Category;
import com.sda.project.model.Product;
import com.sda.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;

    }

    public ProductDto save(ProductDto productDto) {
        productDto.setAvailable(true);
        Product product = productMapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        ProductDto savedDto = productMapper.map(savedProduct);
        return savedDto;
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
        productRepository.findById(dto.getId())
                .map(product -> productMapper.update(product, dto))
                .map(updatedProduct -> productRepository.save(updatedProduct))
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("product not found");
                });
    }

    public void delete(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found"));
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public List<ProductDto> searchProductByNameLike(String value) {
        List<Product> products = productRepository.search(value);
        List <ProductDto> result = productMapper.map(products);
        return result;
    }

    public List<ProductDto> findAllByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        List <ProductDto> result = productMapper.map(products);
        return result;
    }
}
