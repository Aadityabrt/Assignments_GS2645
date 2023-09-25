package com.Cartify.service;

import com.Cartify.entity.Orders;
import com.Cartify.entity.Products;
import com.Cartify.entity.UserCarts;
import com.Cartify.repository.OrderRepo;
import com.Cartify.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public Orders createOrder(String fullName, String address) {
        Orders order = new Orders(fullName, address);
        List<UserCarts> cartItems = cartService.getCartItems();
        double orderTotal = calculateOrderTotal(cartItems);

        order.setOrderTotal(orderTotal);
        System.out.println("🔴 🔴 order total is: " +  orderTotal);
        return order;
    }

    @Transactional
    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    public double calculateOrderTotal(List<UserCarts> cartItems) {
        double total = 0;
        for (UserCarts cartItem : cartItems) {
            total += cartItem.getProductId().getPrice();
            total *= cartItem.getQuantity();
        }
        return total;

    }

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }
//    @Transactional
//    public void processOrder(Orders order) {
//        saveOrder(order);
//        cartService.clearCart();
//    }
}

