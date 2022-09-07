package com.demo.tryout.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.eureka.dto.User;
import com.demo.tryout.eureka.client.FeignClients;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private FeignClients.UserClient userClient;

    @GetMapping("/{id}")
    public ResponseEntity<User> user(@PathVariable String id) {
        ResponseEntity<User> response = this.userClient.getUser(id);
        if (response.getStatusCode() == HttpStatus.OK) {
        	LOGGER.debug("inside feign service {}", response.getBody().toString());
            return ResponseEntity.ok().body(response.getBody());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }


}
