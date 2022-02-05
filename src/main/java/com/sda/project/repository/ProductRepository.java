package com.sda.project.repository;

import com.sda.project.model.Category;
import com.sda.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%" +" OR p.category LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")
    public List<Product> search(String keyword);

    @Query("SELECT p FROM Product p WHERE p.category LIKE %?1%")
    public List<Product> findByCategory(String keyword);

}
