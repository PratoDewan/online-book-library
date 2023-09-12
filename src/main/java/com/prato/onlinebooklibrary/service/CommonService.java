package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.model.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface CommonService {
    Set<?> borrowedBooksByUser(int userId);
    Set<?> currentlyBorrowedBooks(int userId);
    List<?> getAllBooks();
}
