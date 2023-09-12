package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.service.UserOnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/{bookId}")
public class BorrowController {
    @Autowired
    private UserOnlyService userService;
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
    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBook(@PathVariable int bookId, @RequestParam int userId) {
        userService.createReservation(userId, bookId);
        return new ResponseEntity<>("Book reserved successfully!", HttpStatus.OK);
    }
    @PutMapping("/cancel-reservation")
    public ResponseEntity<String> cancelBookReservation(@PathVariable int bookId, @RequestParam int userId) {
        userService.cancelReservation(userId, bookId);
        return new ResponseEntity<>("Book reservation cancelled successfully!", HttpStatus.OK);
    }
}
