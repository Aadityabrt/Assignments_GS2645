package com.Cartify.repository;

import com.Cartify.entity.Products;
import com.Cartify.entity.UserCarts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<UserCarts, Integer> {
    Optional<List<UserCarts>> findByProductId(Products productId);

    void deleteByProductId(Products products);
}