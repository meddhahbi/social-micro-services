package com.example.commentservice;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;


    public void saveComment(Comment comment){
        comment.setCreated(new Date());
        repository.save(comment);
    }

    public Comment createComment(String content, Integer blogId, Integer userId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreated(new Date()); // Automatically set the creation timestamp
        comment.setBlogId(blogId);
        comment.setUserId(userId);
        return repository.save(comment);
    }

    public List<Comment> findAllComment(){
        return repository.findAll();
    }

    public void deleteComment(Integer id) {
        repository.deleteById(id);
    }

    public List<Comment> findAllCommentsByBlog(Integer commentId) {
        return repository.findAllByBlogId(commentId);
    }




}
