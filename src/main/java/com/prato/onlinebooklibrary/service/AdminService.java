package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.BookDto;
import com.prato.onlinebooklibrary.repository.BookRepository;
import com.prato.onlinebooklibrary.repository.BorrowedRepository;
import com.prato.onlinebooklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }
    public List<Borrowed> getAllBorrowed(){
        return borrowedRepository.findAll();
    }
    public void createBook(Book book){
        book.setAvailable(true);
        bookRepository.save(book);
    }
    public void updateBook(int id, BookDto bookDto){
        Optional<Book> optionalBook=bookRepository.findById(id);
        if(optionalBook.isPresent())
        {
            Book book=optionalBook.get();
            if(bookDto.getTitle()!=null){
                book.setTitle(bookDto.getTitle());
            }
            if(bookDto.getAuthor()!=null){
                book.setAuthor(bookDto.getAuthor());
            }
            if(bookDto.getIsbn()!=null){
                book.setIsbn(bookDto.getIsbn());
            }
            bookRepository.save(book);
        }
    }
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
}
