package com.pione.user.Client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "event-service",url = "${application.config.event-url}")
public interface EventClient {
}
