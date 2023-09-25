package com.Cartify.service;

import com.Cartify.entity.Products;
import com.Cartify.entity.UserCarts;
import com.Cartify.repository.CartRepo;
import com.Cartify.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private ProductsRepo productRepository;


    public List<UserCarts> getCartItems() {
        // Retrieve cart items from the database or in-memory data structure
        return cartRepository.findAll();
    }

    public double getCartTotal() {
        List<UserCarts> cartItems = getCartItems();
        double total = 0;

        for (UserCarts cartItem : cartItems) {
            //System.out.println("💥 cart prices : " + cartItem.getProductId().getPrice());
            total += cartItem.getProductId().getPrice();
            total = total*cartItem.getQuantity();
        }

        return total;
    }

    public void addToCart(int productId) {
        Optional<Products> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Products product = productOptional.get();
            UserCarts cartItem = new UserCarts(product, 1);
            cartRepository.save(cartItem);
        }
    }

    public void incrementCartItem(int productId) {
        Optional<Products> prod = productRepository.findById(productId);
        Optional<List<UserCarts>> cartItemOptional = cartRepository.findByProductId(prod.get());

        if (cartItemOptional.isPresent()) {
            for(int i=0; i<cartItemOptional.get().size();i++){
                UserCarts cartItem = cartItemOptional.get().get(i);
                cartItem.setQuantity(cartItem.getQuantity() + 1);

                if (cartItem.getQuantity() <= 0) {
                    // Remove the item if the quantity is zero or negative
                    cartRepository.delete(cartItem);
                } else {
                    // Update the item in the cart
                    cartRepository.save(cartItem);
                }
            }

        }
    }

    public void decrementCartItem(int productId) {
        Optional<Products> prod = productRepository.findById(productId);
        Optional<List<UserCarts>> cartItemOptional = cartRepository.findByProductId(prod.get());

        if (cartItemOptional.isPresent()) {
            for(int i=0; i<cartItemOptional.get().size();i++){
                System.out.println("🔴 cart Items are decremented cart quantity before :" + cartItemOptional.get().get(i).getQuantity());
                UserCarts cartItem = cartItemOptional.get().get(i);
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                System.out.println("🔴 cart Items are decremented cart quantity after :" + cartItemOptional.get().get(i).getQuantity());
                if (cartItem.getQuantity() <= 0) {
                    cartRepository.delete(cartItem);
                } else {
                    cartRepository.save(cartItem);
                }
            }

        }
    }

    public void removeCartItem(int productId) {
        System.out.println("💥 inside CartService/removeCartItem 💥");
        Optional<Products> prod = productRepository.findById(productId);
        Optional<List<UserCarts>> cartItemOptional = cartRepository.findByProductId(prod.get());
        for(int i=0; i<cartItemOptional.get().size(); i++){
            cartRepository.deleteById(cartItemOptional.get().get(i).getCartId());
        }

    }

    public void clearCart() {
        cartRepository.deleteAll();
    }


}
