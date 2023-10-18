package com.pione.categoryservice;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository repository;


    public void saveCategory(Category category){
        repository.save(category);
    }

    public List<Category> findAllCategory(){
        return repository.findAll();
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }


}
