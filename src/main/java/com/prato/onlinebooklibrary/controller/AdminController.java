package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.service.AdminService;
import com.prato.onlinebooklibrary.service.UserOnlyService;
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
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserOnlyService userService;
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable int id){
        return new ResponseEntity<>(adminService.getUserById(id), HttpStatus.OK);
    }
    @GetMapping("/{userId}/books")
    public ResponseEntity<Set<BookDto>> findBorrowedBooksByUser(@PathVariable int userId){
        return new ResponseEntity<>(userService.borrowedBooksByUser(userId),HttpStatus.OK);
    }
    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<Set<BookDto>> findCurrentBorrowedBooksByUser(@PathVariable int userId){
        return new ResponseEntity<>(userService.currentlyBorrowedBooks(userId),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Borrowed>> findAllBorrowed(){
        return new ResponseEntity<>(adminService.getAllBorrowed(),HttpStatus.OK);
    }
}
