package com.example.commentservice.Client;

import com.example.commentservice.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service",url = "${application.config.comment-url}")
public interface BlogClient {

    @GetMapping("/blog/{blog-id}")
    List<Comment>findAllCommentsByBlog(
            @PathVariable("blog-id") Integer blogId
    );

}
