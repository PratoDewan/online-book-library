package com.prato.onlinebooklibrary.repository;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.entity.Borrowed;
import com.prato.onlinebooklibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Integer> {
    Optional<Borrowed> findByUserAndBookAndStatus(User user, Book book, String status);

    List<Borrowed> findByUser(User user);

    List<Borrowed> findByUserAndStatus(User user, String status);
}
