package com.Cartify.repository;

import com.Cartify.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.Optional;

public interface ProductsRepo extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p WHERE p.name LIKE %:searchText% OR p.description LIKE %:searchText%")
    List<Products> searchProducts(String searchText);
    //Optional<Products> getProductByProductName(String productName);
}
