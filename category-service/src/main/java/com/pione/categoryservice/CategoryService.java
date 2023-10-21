package com.pione.categoryservice;


import com.pione.categoryservice.Client.BlogClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    @Autowired
    private CategoryRepository repository;

    @Autowired
    private BlogClient client;


    public void saveCategory(Category category){
        repository.save(category);
    }

    public List<Category> findAllCategory(){
        return repository.findAll();
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }


    public FullCategoryResponse findCategoriesWithBlogs(Integer categoryId) {

        var category = repository.findById(categoryId)
                .orElse(
                        Category
                                .builder()
                                .name("NOT_FOUND")
                                .build()

                );

        var blogs = client.findAllBlogsByCategory(categoryId);

        return  FullCategoryResponse
                .builder()
                .name(category.getName())
                .blogs(blogs)
                .build();
    }
}
