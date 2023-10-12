package com.pione.categoryservice;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;


    public void saveCategory(Category category){
        repository.save(category);
    }

    public List<Category> findAllCategory(){
        return repository.findAll();
    }




}
