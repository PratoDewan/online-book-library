package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.*;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.model.ReviewDto;
import com.prato.onlinebooklibrary.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public interface UserService {
    List<User> getAllUser();

    void borrowBook(int bookId);

    void returnBook(int bookId);

    void createReview(ReviewDto reviewDto, int bookId);

    void updateReview(ReviewDto reviewDto, int bookId, int reviewId);

    void deleteReview(int bookId, int reviewId);

    List<Review> getReviewsByBookId(int bookId);

    void createReservation(int bookId);

    void cancelReservation(int bookId);

    List<Borrowed> borrowHistory(int userId);
}
