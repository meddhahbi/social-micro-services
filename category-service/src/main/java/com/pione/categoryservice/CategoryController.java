package com.pione.categoryservice;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
public class CategoryController {



    @Autowired
    private CategoryService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody Category category){
           service.saveCategory(category);
           return ResponseEntity.status(HttpStatus.CREATED).body("Category saved successfully!");


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCategory(@PathVariable Integer id){
            service.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category deleted successfully!");

    }


    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories(){
            return ResponseEntity.ok(service.findAllCategory());
    }


    @GetMapping("/with-posts/{category-id}")
    public ResponseEntity<FullCategoryResponse> findCategories(
            @PathVariable("category-id") Integer categoryId){
            return ResponseEntity.ok(service.findCategoriesWithBlogs(categoryId));

    }




}
