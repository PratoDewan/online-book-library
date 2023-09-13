package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.ResponseDto;
import com.prato.onlinebooklibrary.model.UserDto;
import com.prato.onlinebooklibrary.service.UserService;
import com.prato.onlinebooklibrary.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {
    @Autowired
    private UserAuthService userService;
    @Autowired
    private UserService userOnlyService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> registerUser() {
        return new ResponseEntity<>(userOnlyService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) throws Exception {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
}
