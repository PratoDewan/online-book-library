package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.service.AdminService;
import com.prato.onlinebooklibrary.service.CommonService;
import com.prato.onlinebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private  UserService userService;
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> findById(@PathVariable int userId){
        return new ResponseEntity<>(adminService.getUserById(userId), HttpStatus.OK);
    }
    @GetMapping("/{userId}/books")
    public ResponseEntity<Set<?>> findBorrowedBooksByUser(@PathVariable int userId){
        return new ResponseEntity<>(commonService.borrowedBooksByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<Set<?>> findCurrentBorrowedBooksByUser(@PathVariable int userId){
        return new ResponseEntity<>(commonService.currentlyBorrowedBooks(userId),HttpStatus.OK);
    }
    @GetMapping("/{userId}/history")
    public ResponseEntity<List<Borrowed>> findUserHistory(@PathVariable int userId){
        return new ResponseEntity<>(userService.borrowHistory(userId), HttpStatus.OK);
    }
//    @GetMapping("/all")
//    public ResponseEntity<List<Borrowed>> findAllBorrowed(){
//        return new ResponseEntity<>(commonService.getAllBorrowed(),HttpStatus.OK);
//    }
}
