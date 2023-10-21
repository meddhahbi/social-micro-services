package com.pione.categoryservice.Client;

import com.pione.categoryservice.blog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "blog-service",url = "${application.config.blog-url}")
public interface BlogClient {

    @GetMapping("/category/{category-id}")
    List<blog>findAllBlogsByCategory(
            @PathVariable("category-id") Integer categoryId
    );

}
