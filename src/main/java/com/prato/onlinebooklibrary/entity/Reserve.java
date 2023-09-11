package com.prato.onlinebooklibrary.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "Reserve")
@Component
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Integer reviewId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "reservation_date")
    private Date reservationDate;
    @Column(name = "status")
    private String status;
    @PrePersist
    public void prePersist() {
        LocalDate currentDate = LocalDate.now();
        reservationDate = Date.valueOf(currentDate);
        status = "reserved";
    }
    public Reserve(){

    }

    public Reserve(User user, Book book, Date reservationDate, String status) {
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
        this.status = status;
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
