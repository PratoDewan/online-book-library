package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.entity.Review;
import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.ReviewDto;
import com.prato.onlinebooklibrary.repository.BookRepository;
import com.prato.onlinebooklibrary.repository.BorrowedRepository;
import com.prato.onlinebooklibrary.repository.ReviewRepository;
import com.prato.onlinebooklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public void addNewUser(User user){
        userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void borrowBook(int userId,int bookId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent())
        {
            if(optionalBook.get().isAvailable()) {
                Borrowed borrowed = new Borrowed();
                borrowed.setBook(optionalBook.get());
                borrowed.setUser(optionalUser.get());
                optionalBook.get().setAvailable(false);
                bookRepository.save(optionalBook.get());
                borrowedRepository.save(borrowed);
            }
        }
    }
    public void returnBook(int userId,int bookId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent())
        {
            User user=optionalUser.get();
            Book book=optionalBook.get();
            Optional<Borrowed>borrowedOptional = borrowedRepository.findByUserAndBookAndStatus(user,book,"borrowed");
            if(borrowedOptional.isPresent()) {
                borrowedOptional.get().setStatus("returned");
                book.setAvailable(true);
                bookRepository.save(book);
                borrowedRepository.save(borrowedOptional.get());
            }
        }
    }
    public Set<Book> borrowedBooksByUser(int userId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Borrowed> borrowedList= borrowedRepository.findByUser(user);
            Set<Book> borrowedBookSet=new HashSet<>();
            for(Borrowed borrowedBook:borrowedList)
            {
                borrowedBookSet.add(borrowedBook.getBook());
            }
            return borrowedBookSet;
        }
        return null;
    }
    public Set<Book> currentlyBorrowedBooks(int userId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Borrowed> borrowedList= borrowedRepository.findByUserAndStatus(user,"borrowed");
            Set<Book> borrowedBookSet=new HashSet<>();
            for(Borrowed borrowedBook:borrowedList)
            {
                borrowedBookSet.add(borrowedBook.getBook());
            }
            return borrowedBookSet;
        }
        return null;
    }
    // Review Portion
    public void createReview(ReviewDto reviewDto, int userId, int bookId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
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
    public List<Review> getReviewsByBookId(int bookId){
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        return optionalBook.map(book -> reviewRepository.findByBook(book)).orElse(null);
    }
}
