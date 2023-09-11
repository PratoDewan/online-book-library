package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.service.UserOnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserOnlyService userService;
    @GetMapping("/all")
    public ResponseEntity<List<User>> registerUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        userService.addNewUser(user);
        return new ResponseEntity<>("User added successfully!", HttpStatus.CREATED);
    }
}
