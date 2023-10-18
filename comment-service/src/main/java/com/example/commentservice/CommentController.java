package com.example.commentservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/comments")
public class CommentController {


    @Autowired
    private CommentService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody Comment comment){
        service.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment saved successfully!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable Integer id){
        service.deleteComment(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment deleted successfully!");
    }


    @GetMapping
    public ResponseEntity<List<Comment>> findAllCategories(){
        return ResponseEntity.ok(service.findAllComment());
    }
}
