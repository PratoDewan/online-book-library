package com.prato.onlinebooklibrary.controller;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.service.AdminService;
import com.prato.onlinebooklibrary.service.CommonService;
import com.prato.onlinebooklibrary.service.UserService;
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
    private UserService userService;
    @Autowired
    private CommonService commonService;
    @PostMapping("/create")
    public ResponseEntity<String> createNewBook(@RequestBody Book book){
        adminService.createBook(book);
        return new ResponseEntity<>("Successfully created!", HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllBooks(){
        return new ResponseEntity<>(commonService.getAllBooks(), HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody BookDto bookDto){
        adminService.updateBook(bookDto);
        return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody BookDto bookDto){
        adminService.deleteBook(bookDto);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
    }

}
