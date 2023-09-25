package com.Cartify.controller;

import com.Cartify.entity.Orders;
import com.Cartify.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CheckOutController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        return "checkOut";
    }

    @PostMapping("/processOrder")
    public String processOrder(@RequestParam("fullName") String fullName, @RequestParam("address") String address, Model model) {
        Orders order = orderService.createOrder(fullName, address);
        orderService.saveOrder(order);
        model.addAttribute("orders",order);
        return "orderConfirmation";
    }

    @PostMapping("/allOrders")
    public String getAllOrders(Model model){
        List<Orders> orders = orderService.getAllOrders();
        model.addAttribute("orders",orders);
        return "allOrders";
    }
}
