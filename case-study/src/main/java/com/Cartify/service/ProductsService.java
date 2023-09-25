package com.Cartify.service;

import com.Cartify.entity.Products;
//import com.Cartify.helper.ProductsInCategories;
import com.Cartify.repository.CartRepo;
import com.Cartify.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    CartRepo cartRepo;

    @Transactional
    public List<Products> getAllProducts(){
        return productsRepo.findAll();
    }
    @Transactional
    public Products getProductByProductId(int id){
        Optional<Products> product = productsRepo.findById(id);
        if(product.isPresent()) return product.get();
        throw new RuntimeException("🔴 Product With this Id is Not Present 🔴");
    }

    @Transactional
    public List<Products> searchProducts(String searchText){
        return productsRepo.searchProducts(searchText);
    }

   // @Transactional
//    public void addToCart(int productId){
//
//        UserCarts cart = new UserCarts();
//    }


//    @Transactional
//    public Products getProductByProductName(String name){
//        Optional<Products> product = productsRepo.getProductByProductName(name);
//        if(product.isPresent()) return product.get();
//        throw new RuntimeException("🔴 Product With this Name is Not Present 🔴");
//    }
    @Transactional
    public boolean addOrModifyProducts(Products product){
        System.out.println("💥 "+product.getDescription() + " 💥");
        Products prod = productsRepo.save(product);
        return prod != null;
    }
    @Transactional
    public boolean deleteByProductId(int id){
        long cnt = productsRepo.count();
        productsRepo.deleteById(id);
        System.out.println("💥 Hi From ProductService ");
        long cnt2 = productsRepo.count();
        return cnt > cnt2;
    }
}
