package com.prato.onlinebooklibrary.service.impl;

import com.prato.onlinebooklibrary.entity.*;
import com.prato.onlinebooklibrary.exception.IllegalOperationException;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.model.ReviewDto;
import com.prato.onlinebooklibrary.repository.*;
import com.prato.onlinebooklibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReserveRepository reserveRepository;
    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    private Optional<User> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }
    @Override
    public void borrowBook(int bookId){
        if(bookId<0){
            throw new IllegalArgumentException("Book Id");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent())
        {
            if(optionalBook.get().getStatus().equals(Book.Status.Available)) {
                Borrowed borrowed = new Borrowed();
                borrowed.setBook(optionalBook.get());
                borrowed.setUser(optionalUser.get());
                optionalBook.get().setStatus(Book.Status.Borrowed);
                bookRepository.save(optionalBook.get());
                borrowedRepository.save(borrowed);
                return;
            }
            else{
                throw new IllegalOperationException("Error Borrowing! The book is not available");
            }
        }
        if(optionalUser.isEmpty() && optionalBook.isEmpty()){
            throw new EmptyResultDataAccessException("User, Book",1);
        }
        if(optionalUser.isEmpty()){
            throw new EmptyResultDataAccessException("User",1);
        }
        throw new EmptyResultDataAccessException("Book",1);
    }
    @Override
    public void returnBook(int bookId){
        Optional<User> optionalUser=getUser();
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent())
        {
            User user=optionalUser.get();
            Book book=optionalBook.get();
            Optional<Borrowed>borrowedOptional = borrowedRepository.findByUserAndBookAndStatus(user,book,"borrowed");
            if(borrowedOptional.isPresent()) {
                borrowedOptional.get().setStatus("returned");
                borrowedOptional.get().setReturnDate(Date.valueOf(LocalDate.now()));
                book.setStatus(Book.Status.Available);
                bookRepository.save(book);
                borrowedRepository.save(borrowedOptional.get());
            }
        }
    }
    @Override
    public void createReview(ReviewDto reviewDto, int bookId){
        Optional<User> optionalUser=getUser();
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent())
        {
            Review review=new Review();
            review.setBook(optionalBook.get());
            review.setUser(optionalUser.get());
            review.setComment(reviewDto.getComment());
            review.setRating(reviewDto.getRating());
            reviewRepository.save(review);
        }
    }
    @Override
    public void updateReview(ReviewDto reviewDto, int bookId,int reviewId){
        Optional<User> optionalUser=getUser();
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        Optional<Review> optionalReview=reviewRepository.findById(reviewId);
        if(optionalUser.isPresent() && optionalBook.isPresent() && optionalReview.isPresent()){
            if(reviewDto.getComment()!=null){
                optionalReview.get().setComment(reviewDto.getComment());
            }
            if(reviewDto.getRating()!=null){
                optionalReview.get().setRating(reviewDto.getRating());
            }
        }
    }
    @Override
    public void deleteReview(ReviewDto reviewDto, int bookId,int reviewId){
        Optional<User> optionalUser=getUser();
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        Optional<Review> optionalReview=reviewRepository.findById(reviewId);
        if(optionalUser.isPresent() && optionalBook.isPresent() && optionalReview.isPresent()){
            reviewRepository.deleteById(reviewId);
        }
    }
    @Override
    public List<Review> getReviewsByBookId(int bookId){
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        return optionalBook.map(book -> reviewRepository.findByBook(book)).orElse(null);
    }
    @Override
    public void createReservation(int bookId){
        Optional<User> optionalUser=getUser();
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent()){
            if(optionalBook.get().getStatus().equals(Book.Status.Available)){
                throw new IllegalOperationException("Error Renting! The book is available to borrow");
            }
            Reserve reserve=new Reserve();
            reserve.setUser(optionalUser.get());
            reserve.setBook(optionalBook.get());
            reserveRepository.save(reserve);
        }
    }
    @Override
    public void cancelReservation(int bookId){
        Optional<User> optionalUser=getUser();
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent()){
            User user=optionalUser.get();
            Book book=optionalBook.get();
            Optional<Reserve> optionalReserve=reserveRepository.findByUserAndBook(user,book);
            optionalReserve.ifPresent(reserve -> reserve.setReservationStatus(Reserve.ReservationStatus.Cancelled));
        }
    }

    @Override
    public List<Borrowed> borrowHistory(int userId) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        return optionalUser.map(user -> borrowedRepository.findByUser(user)).orElse(null);
    }
}
