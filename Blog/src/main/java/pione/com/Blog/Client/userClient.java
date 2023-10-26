package pione.com.Blog.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pione.com.Blog.user;

import java.util.List;

@FeignClient(name = "user-service",url = "${application.config.user-url}")
public interface userClient {
    @GetMapping("/blog/{blog-id}")
    List<user> findAllUsersByBlog(
            @PathVariable("blog-id") Integer blogId
    );
}
