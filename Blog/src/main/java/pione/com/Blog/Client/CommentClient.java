package pione.com.Blog.Client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "comment-service", url = "${application.config.comment-url}")
public interface CommentClient {
}
