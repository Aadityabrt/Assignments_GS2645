package com.Cartify.repository;

import com.Cartify.entity.Categories;
import com.Cartify.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepo extends JpaRepository<Categories,Integer> {
    //Optional<Categories> getCategoryByCategoryName(String name);
}
