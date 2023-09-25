package com.Cartify.entity;

import jakarta.persistence.*;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

@Entity
@Table(name = "user_carts")
public class UserCarts {
    @Id
    @Column(name = "cart_id")
    private int cartId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Products productId;
    @Column(nullable = false)
    private int quantity;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public UserCarts() {
    }

    public UserCarts(Products productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UserCarts(Users userId, Products productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UserCarts(int cartId, Users userId, Products productId, int quantity, Timestamp createdAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
