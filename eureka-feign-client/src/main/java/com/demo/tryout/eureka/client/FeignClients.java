package com.demo.tryout.eureka.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.eureka.dto.User;

public class FeignClients {

    @FeignClient("${fd.microservice.user.name}")
    public interface UserClient {

        @GetMapping("/test/user/{id}")
        ResponseEntity<User> getUser(@PathVariable("id") String id);

    }
}
