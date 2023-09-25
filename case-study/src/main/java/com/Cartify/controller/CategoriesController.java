package com.Cartify.controller;

import com.Cartify.entity.Categories;
import com.Cartify.entity.Products;
//import com.Cartify.helper.ProductsInCategories;
import com.Cartify.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    CategoriesService categoriesService;
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Categories>> getAllCategories(){
        return new ResponseEntity<>(categoriesService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping(value = "/{categoryId}", produces = "application/json")
    public ResponseEntity<Categories> getCategoryByCategoryId(@PathVariable int categoryId) {
        return new ResponseEntity<>(categoriesService.getCategoryByCategoryId(categoryId),HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    public HttpStatus addCategory(@RequestBody Categories category) {
        if(categoriesService.addOrModifyProducts(category)) return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }

    @PutMapping(consumes = "application/json")
    public HttpStatus modifyCategory(@RequestBody Categories category) {
        if(categoriesService.addOrModifyProducts(category)) return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }
    @DeleteMapping(value = "/{categoryId}",consumes = "application/json")
    public HttpStatus deleteCategory(int id) {
        if(categoriesService.deleteByCategoryId(id)) return HttpStatus.OK;
        return HttpStatus.NOT_MODIFIED;
    }
//    @GetMapping(value = "/{categoryName}", produces = "application/json")
//    public ResponseEntity<Categories> getCategoryByCategoryName(@PathVariable String categoryName) {
//        return new ResponseEntity<>(categoriesService.getCategoryByCategoryName(categoryName),HttpStatus.OK);
//    }

}
