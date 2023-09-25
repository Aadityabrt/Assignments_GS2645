package com.Cartify.service;

import com.Cartify.entity.Categories;
import com.Cartify.entity.Products;
//import com.Cartify.helper.ProductsInCategories;
import com.Cartify.repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepo categoriesRepo;
    //RestTemplate restTemplate;
    @Transactional
    public List<Categories> getAllCategories(){
        return categoriesRepo.findAll();
    }
    @Transactional
    public Categories getCategoryByCategoryId(int id){
        Optional<Categories> categories = categoriesRepo.findById(id);
        if(categories.isPresent()) return categories.get();
        throw new RuntimeException("🔴 Category with this id is Not present 🔴");
    }
    @Transactional
    public boolean addOrModifyProducts(Categories category){
        Categories cat = categoriesRepo.save(category);
        return cat != null;
    }
    @Transactional
    public boolean deleteByCategoryId(int id){
        long cnt = categoriesRepo.count();
        categoriesRepo.deleteById(id);
        return cnt > categoriesRepo.count();
    }
//    @Transactional
//    public Categories getCategoryByCategoryName(String name){
//        Optional<Categories> categories = categoriesRepo.getCategoryByCategoryName(name);
//        if(categories.isPresent()) return categories.get();
//        throw new RuntimeException("🔴 Category with this Name is Not present 🔴");
//    }
}
