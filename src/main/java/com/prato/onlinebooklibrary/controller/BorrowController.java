package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/{bookId}")
public class BorrowController {
    @Autowired
    private UserService userService;
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable int bookId,@RequestParam int userId){
        userService.borrowBook(userId, bookId);
        return new ResponseEntity<>("Successfully borrowed!", HttpStatus.OK);
    }
    @PutMapping("/return")
    public ResponseEntity<String> returnBorrowedBook(@PathVariable int bookId, @RequestParam int userId){
        userService.returnBook(userId,bookId);
        return new ResponseEntity<>("Book returned successfully!", HttpStatus.OK);
    }
}
