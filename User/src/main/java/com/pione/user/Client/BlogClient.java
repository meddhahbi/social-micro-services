package com.pione.user.Client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "blog-service",url = "${application.config.blog-url}")
public interface BlogClient {
}
