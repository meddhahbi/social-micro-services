package pione.com.Blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pione.com.Blog.Client.userClient;
/*import pione.com.Blog.Client.userClient;*/

import java.util.List;

@Service
public class blogService {
    @Autowired
    private blogRepository repository;

    @Autowired
    private userClient client;


    public void saveBlog(blog blog){
        repository.save(blog);
    }

    public List<blog> findAllBlog(){
        return repository.findAll();
    }

    public void deleteBlog(Integer id) {
        repository.deleteById(id);
    }

    public blog findBlogById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public blog updateBlog(blog blog) {
        return repository.save(blog);
    }

    public List<blog> findAllBlogsByCategory(Integer categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    public FullUserResponse findBlogsWithUsers(Integer userId) {

        var B = repository.findById(userId)
                .orElse(
                        blog
                                .builder()
                                .titre("NOT_FOUND")
                                .build()

                );

        var users = client.findAllUsersByBlog(userId);

        return  FullUserResponse
                .builder()
                .titre(B.getTitre())
                .users(users)
                .build();
    }
}
