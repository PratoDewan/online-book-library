package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.Review;
import com.prato.onlinebooklibrary.model.ReviewDto;
import com.prato.onlinebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/{bookId}/reviews")
public class ReviewController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto,
                                               @PathVariable int bookId, @RequestParam int userId){
        userService.createReview(reviewDto,userId,bookId);
        return new ResponseEntity<>("Review created successfully!", HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<Review>> getReviewByBookId(@PathVariable int bookId){
        return new ResponseEntity<>(userService.getReviewsByBookId(bookId),HttpStatus.OK);
    }
}