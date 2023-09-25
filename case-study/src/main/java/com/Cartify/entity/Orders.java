package com.Cartify.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(name = "full_name")
    private String fullName;
    private String address;

    @Column(name="order_total")
    private double orderTotal;

    // Constructors

    public Orders() {
    }

    public Orders(String fullName, String Address) {
        this.fullName = fullName;
        this.address = Address;
    }

    public Orders(int orderId, String fullName, String address, double orderTotal) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.address = address;
        this.orderTotal = orderTotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
}
