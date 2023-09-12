package com.prato.onlinebooklibrary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Book")
@Component
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "title", nullable = false)
    @NotEmpty
    private String title;
    @Column(name = "author", nullable = false)
    @NotEmpty
    private String author;
    @Column(name = "isbn", nullable = false, unique = true)
    @NotEmpty
    private String isbn;
    public enum Status {
        Available,
        Borrowed,
        Deleted
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    public Book(){

    }

    public Book(Integer bookId,String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
