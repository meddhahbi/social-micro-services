package pione.com.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blog")
public class blogController {

    @Autowired
    private blogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody blog blog){
        service.saveBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body("Blog saved successfully!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteBlog(@PathVariable Integer id){
        service.deleteBlog(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Blog deleted successfully!");
    }


    @GetMapping
    public ResponseEntity<List<blog>> findAllBlogs(){
        return ResponseEntity.ok(service.findAllBlog());
    }

    @GetMapping("/{id}")
    public ResponseEntity<blog> findBlog(@PathVariable Integer id) {
        return  ResponseEntity.ok(service.findBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<blog> updateBlog(@PathVariable Integer id, @RequestBody blog updatedBlog) {
        blog existingBlog = service.findBlogById(id);
        if (existingBlog != null) {

            existingBlog.setTitre(updatedBlog.getTitre());
            existingBlog.setDescription(updatedBlog.getAuteur());
            existingBlog.setAuteur(updatedBlog.getAuteur());
            existingBlog.setUserId(updatedBlog.getUserId());
            existingBlog.setCategoryId(updatedBlog.getUserId());

            blog updated = service.updateBlog(existingBlog);

            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/category/{category-id}")
    public ResponseEntity<List<blog>>findAllBlogs(
            @PathVariable("category-id") Integer categoryId
    ){
        return ResponseEntity.ok(service.findAllBlogsByCategory(categoryId));
    }



}
