package com.prato.onlinebooklibrary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="user")
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "first_name",nullable = false)
    @NotEmpty
    private String firstName;
    @Column(name = "last_name",nullable = false)
    @NotEmpty
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    @Email
    private String email;
    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;
    @Column(name = "address", nullable = false)
    @NotEmpty
    private String address;
    public User(){

    }

    public User(String firstName, String lastName, String email, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
