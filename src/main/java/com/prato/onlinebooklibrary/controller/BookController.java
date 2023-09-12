package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.service.AdminService;
import com.prato.onlinebooklibrary.service.UserOnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserOnlyService userService;
    @PostMapping("/create")
    public ResponseEntity<String> createNewBook(@RequestBody Book book){
        adminService.createBook(book);
        return new ResponseEntity<>("Successfully created!", HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(adminService.getAllBooks(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestParam int bookId, @RequestBody BookDto bookDto){
        adminService.updateBook(bookId,bookDto);
        return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam int bookId){
        adminService.deleteBook(bookId);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
    }

}
