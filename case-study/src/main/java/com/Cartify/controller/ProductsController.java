package com.Cartify.controller;

import com.Cartify.entity.Products;
//import com.Cartify.helper.ProductsInCategories;
import com.Cartify.repository.ProductsRepo;
import com.Cartify.service.CartService;
import com.Cartify.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequestMapping("/products")

public class ProductsController {
    @Autowired
    ProductsService productsService;

    @Autowired
    CartService cartService;

    @GetMapping("")
    public String getAllProducts(Model model) {
        List<Products> products = productsService.getAllProducts();
        model.addAttribute("products",products);
        return "displayProducts";
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<Products> getProductById(@PathVariable int productId,Model model) {
        return new ResponseEntity<Products>(productsService.getProductByProductId(productId),HttpStatus.OK);
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("inputText") String keyword,Model model){
        System.out.println("🔴 search Test is :" + keyword);
        List<Products> searchResults = productsService.searchProducts(keyword);
        model.addAttribute("products", productsService.searchProducts(keyword));
        return "displayProducts";
    }

    @PostMapping("")
    @ResponseBody
    public HttpStatus addProduct(@RequestBody Products product) {
        System.out.println("💥 "+product.getName() + " 💥");
        if(productsService.addOrModifyProducts(product)) return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }

    @PutMapping(consumes = "application/json")
    @ResponseBody
    public HttpStatus modifyProduct(@RequestBody Products product) {
        if(productsService.addOrModifyProducts(product))
            return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }

    @DeleteMapping(value = "/{productId}", consumes = "application/json")
    @ResponseBody
    public HttpStatus deleteProduct(@PathVariable int productId) {
        System.out.println("🔴 Hi from delete_product 🔴");
        if (productsService.deleteByProductId(productId))
            return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }
    @ExceptionHandler(RuntimeException.class)
    public HttpStatus productNotFoundHandler(){
        System.out.println(RuntimeException.class);
        return HttpStatus.NOT_FOUND;
    }

}
