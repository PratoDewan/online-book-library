package com.prato.onlinebooklibrary.service.impl;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.BookAdminResponseDto;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.repository.BookRepository;
import com.prato.onlinebooklibrary.repository.BorrowedRepository;
import com.prato.onlinebooklibrary.repository.UserRepository;
import com.prato.onlinebooklibrary.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Set<?> borrowedBooksByUser(int userId) {
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Borrowed> borrowedList = borrowedRepository.findByUser(user);
            if(user.getRole().equals(User.Role.CUSTOMER)) {
                Set<BookDto> borrowedBookSet = new HashSet<>();
                for (Borrowed borrowedBook : borrowedList) {
                    Book book = borrowedBook.getBook();
                    BookDto bookDto = new BookDto();
                    bookDto.setTitle(book.getTitle());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setIsbn(book.getIsbn());
                    borrowedBookSet.add(bookDto);
                }
                return borrowedBookSet;
            }
            else{
                Set<BookAdminResponseDto> borrowedBookSet = new HashSet<>();
                for (Borrowed borrowedBook : borrowedList) {
                    Book book = borrowedBook.getBook();
                    BookAdminResponseDto bookAdminResponseDto = new BookAdminResponseDto();
                    bookAdminResponseDto.setTitle(book.getTitle());
                    bookAdminResponseDto.setAuthor(book.getAuthor());
                    bookAdminResponseDto.setIsbn(book.getIsbn());
                    bookAdminResponseDto.setStatus(book.getStatus());
                    borrowedBookSet.add(bookAdminResponseDto);
                }
                return borrowedBookSet;
            }
        }
        return null;
    }

    @Override
    public Set<?> currentlyBorrowedBooks(int userId) {
        Optional<User> optionalUser=userRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            List<Borrowed> borrowedList = borrowedRepository.findByUserAndStatus(user,"Borrowed");
            if(user.getRole().equals(User.Role.CUSTOMER)) {
                Set<BookDto> borrowedBookSet = new HashSet<>();
                for (Borrowed borrowedBook : borrowedList) {
                    Book book = borrowedBook.getBook();
                    BookDto bookDto = new BookDto();
                    bookDto.setTitle(book.getTitle());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setIsbn(book.getIsbn());
                    borrowedBookSet.add(bookDto);
                }
                return borrowedBookSet;
            }
            else{
                Set<BookAdminResponseDto> borrowedBookSet = new HashSet<>();
                for (Borrowed borrowedBook : borrowedList) {
                    Book book = borrowedBook.getBook();
                    BookAdminResponseDto bookAdminResponseDto = new BookAdminResponseDto();
                    bookAdminResponseDto.setTitle(book.getTitle());
                    bookAdminResponseDto.setAuthor(book.getAuthor());
                    bookAdminResponseDto.setIsbn(book.getIsbn());
                    bookAdminResponseDto.setStatus(book.getStatus());
                    borrowedBookSet.add(bookAdminResponseDto);
                }
                return borrowedBookSet;
            }
        }
        return null;
    }
    private Optional<User> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return userRepository.findByEmail(authentication.getName());
    }
    @Override
    public List<?> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        Optional<User> optionalUser = getUser();
        if(optionalUser.isPresent())
        {
            User user=optionalUser.get();
            if(user.getRole().equals(User.Role.CUSTOMER)) {
                List<BookDto> bookDtoList = new ArrayList<>();
                for (Book book : bookList) {
                    BookDto bookDto = new BookDto();
                    bookDto.setBookId(book.getBookId());
                    bookDto.setTitle(book.getTitle());
                    bookDto.setAuthor(book.getAuthor());
                    bookDto.setIsbn(book.getIsbn());
                    bookDtoList.add(bookDto);
                }
                return bookDtoList;
            }
            else{
                List<BookAdminResponseDto> bookAdminResponseDtoList = new ArrayList<>();
                for (Book book : bookList) {
                    BookAdminResponseDto bookAdminResponseDto = new BookAdminResponseDto();
                    bookAdminResponseDto.setBookId(book.getBookId());
                    bookAdminResponseDto.setTitle(book.getTitle());
                    bookAdminResponseDto.setAuthor(book.getAuthor());
                    bookAdminResponseDto.setIsbn(book.getIsbn());
                    bookAdminResponseDto.setStatus(book.getStatus());
                    bookAdminResponseDtoList.add(bookAdminResponseDto);
                }
                return bookAdminResponseDtoList;
            }
        }
        return null;
    }
}
