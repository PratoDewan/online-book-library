package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.*;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.model.ReviewDto;
import com.prato.onlinebooklibrary.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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
    @Autowired
    private ReserveRepository reserveRepository;
    public void addNewUser(User user){
        userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    //Borrow Portion
    public void borrowBook(int userId,int bookId){
        if(userId<0 && bookId<0){
            throw new IllegalArgumentException("User Id, Book Id");
        }
        if(userId<0){
            throw new IllegalArgumentException("User Id");
        }
        if(bookId<0){
            throw new IllegalArgumentException("Book Id");
        }
        Optional<User> optionalUser=userRepository.findByUserId(userId);
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
                borrowedOptional.get().setReturnDate(Date.valueOf(LocalDate.now()));
                book.setStatus(Book.Status.Available);
                bookRepository.save(book);
                borrowedRepository.save(borrowedOptional.get());
            }
        }
    }
    public Set<BookDto> borrowedBooksByUser(int userId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Borrowed> borrowedList= borrowedRepository.findByUser(user);
            Set<BookDto> borrowedBookSet=new HashSet<>();
            for(Borrowed borrowedBook:borrowedList)
            {
                Book book=borrowedBook.getBook();
                BookDto bookDto=new BookDto();
                bookDto.setTitle(book.getTitle());
                bookDto.setAuthor(book.getAuthor());
                bookDto.setIsbn(book.getIsbn());
                borrowedBookSet.add(bookDto);
            }
            return borrowedBookSet;
        }
        return null;
    }
    public Set<BookDto> currentlyBorrowedBooks(int userId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Borrowed> borrowedList= borrowedRepository.findByUserAndStatus(user,"borrowed");
            Set<BookDto> borrowedBookSet=new HashSet<>();
            for(Borrowed borrowedBook:borrowedList)
            {
                Book book=borrowedBook.getBook();
                BookDto bookDto=new BookDto();
                bookDto.setTitle(book.getTitle());
                bookDto.setAuthor(book.getAuthor());
                bookDto.setIsbn(book.getIsbn());
                borrowedBookSet.add(bookDto);
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
    public void updateReview(ReviewDto reviewDto, int userId, int bookId,int reviewId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
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
    public void deleteReview(ReviewDto reviewDto, int userId, int bookId,int reviewId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        Optional<Review> optionalReview=reviewRepository.findById(reviewId);
        if(optionalUser.isPresent() && optionalBook.isPresent() && optionalReview.isPresent()){
           reviewRepository.deleteById(reviewId);
        }
    }
    public List<Review> getReviewsByBookId(int bookId){
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        return optionalBook.map(book -> reviewRepository.findByBook(book)).orElse(null);
    }
    // Reservation
    public void createReservation(int userId, int bookId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent()){
            Reserve reserve=new Reserve();
            reserve.setUser(optionalUser.get());
            reserve.setBook(optionalBook.get());
            reserveRepository.save(reserve);
        }
    }
    public void cancelReservation(int userId, int bookId){
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        Optional<Book> optionalBook=bookRepository.findByBookId(bookId);
        if(optionalUser.isPresent() && optionalBook.isPresent()){
            User user=optionalUser.get();
            Book book=optionalBook.get();
            Optional<Reserve> optionalReserve=reserveRepository.findByUserAndBook(user,book);
            optionalReserve.ifPresent(reserve -> reserve.setStatus("cancelled"));
        }
    }
}
