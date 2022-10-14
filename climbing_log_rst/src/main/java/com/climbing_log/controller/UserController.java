package com.climbing_log.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.climbing_log.model.User;
import com.climbing_log.service.UserDetailsServiceImpl;

@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @PostMapping(path = "/signup")
    public ResponseEntity<Integer> addUser(
            @RequestBody @Valid User newUser) {
        Integer userId = userDetailsService.addUser(newUser);
        return ResponseEntity.ok(userId);
    }

    // @GetMapping(path = "/login")
    // public ResponseEntity<String> getUser(
    //         @RequestBody @Valid User user) {
    //     userDetailsService.loadUserByUsername(user);
    //     return null;
    // }

    @GetMapping(path = "/")
    public String all() {
        return "All users can view this";
    }

    @GetMapping(path = "/user")
    public String user() {
        return "Users can view this";
    }
    
}
