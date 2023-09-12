package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.BookDto;

import java.util.Optional;

public interface AdminService {
    Optional<User> getUserById(int userId);
    void createBook(Book book);
    void updateBook(BookDto bookDto);
    void deleteBook(BookDto bookDto);
}
