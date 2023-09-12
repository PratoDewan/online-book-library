package com.prato.onlinebooklibrary.model;

import com.prato.onlinebooklibrary.entity.Book;

public class BookAdminResponseDto {
    private Integer bookId;
    private String title;
    private String author;
    private String isbn;
    private Book.Status status;
    public BookAdminResponseDto(){

    }

    public BookAdminResponseDto(Integer bookId, String title, String author, String isbn, Book.Status status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book.Status getStatus() {
        return status;
    }

    public void setStatus(Book.Status status) {
        this.status = status;
    }
}
