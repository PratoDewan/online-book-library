package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.ResponseDto;
import com.prato.onlinebooklibrary.model.UserDto;
import com.prato.onlinebooklibrary.service.UserOnlyService;
import com.prato.onlinebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserOnlyService userOnlyService;
    @Autowired
    private UserService userService;
    @GetMapping("/all")
    public ResponseEntity<List<User>> registerUser(){
        return new ResponseEntity<>(userOnlyService.getAllUser(), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) throws Exception {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
}
