package com.pione.categoryservice;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private CategoryService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Category category){
        service.saveCategory(category);
    }


    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories(){
        return ResponseEntity.ok(service.findAllCategory());
    }

}
