package com.prato.onlinebooklibrary.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Entity
@Table(name="review")
@Component
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "rating")
    private float rating;
    @Column(name = "comment")
    private String comment;
    public Review(){

    }

    public Review(User user, Book book, float rating, String comment) {
        this.user = user;
        this.book = book;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
