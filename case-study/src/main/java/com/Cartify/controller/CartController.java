package com.Cartify.controller;

import com.Cartify.entity.Products;
import com.Cartify.entity.UserCarts;
import com.Cartify.entity.Users;
import com.Cartify.repository.CartRepo;
import com.Cartify.repository.ProductsRepo;
import com.Cartify.service.CartService;
import com.Cartify.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    private Users presentUser;
    @Autowired
    CartService cartService;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    ProductsService productsService;
    @Autowired
    DisplayController displayController;
    @GetMapping(value = "/cart")
    public String viewCart(Model model) {
        List<UserCarts> cartItems = cartService.getCartItems();
        double cartTotal = cartService.getCartTotal();
        System.out.println("🔴 🔴 total Value is "+ cartTotal);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartTotal);
        return "cartPage";
    }

    @PostMapping("/addToCart/{productId}")
    public String productAddToCart(@PathVariable int productId){
        System.out.println("🔴 🔴 Im in CartController and productId is " + productId);
        Optional<Products> prod = productsRepo.findById(productId);
        Optional<List<UserCarts>> prodInCart = cartRepo.findByProductId(prod.get());
        if (prodInCart.isPresent() && !prodInCart.get().isEmpty()) {
            System.out.println("🔴 Product Already Present");

//            UserCarts cartItem = prodInCart.get().get(0);
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
            System.out.println("🔴 Hence Increasing Quantity in cart");
                // Update the item in the cart
            cartService.incrementCartItem(productId);

        }
        else cartService.addToCart(productId);


        return "redirect:/cart";
    }

    @PostMapping("/updateCartItem/increment/{productId}/{quantity}")
    public String incrementCartItem(@PathVariable int productId, @PathVariable int quantity) {
        System.out.println("🔴 quantity is "+ quantity +" product id is " + productId +"🔴 🔴");
        cartService.incrementCartItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/updateCartItem/decrement/{productId}/{quantity}")
    public String decrementCartItem(@PathVariable int productId, @PathVariable int quantity) {
        System.out.println("🔴 quantity is "+ quantity +" product id is " + productId +"🔴 🔴");
        cartService.decrementCartItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/removeCartItem/{productId}")
    public String removeCartItem(@PathVariable int productId) {
        System.out.println("🔴 💥 product "+ productId+" is going to get deleted");
        cartService.removeCartItem(productId);
        return "redirect:/cart";
    }

}
