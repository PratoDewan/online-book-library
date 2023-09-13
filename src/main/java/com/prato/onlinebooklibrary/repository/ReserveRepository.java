package com.prato.onlinebooklibrary.repository;

import com.prato.onlinebooklibrary.entity.Book;
import com.prato.onlinebooklibrary.entity.Reserve;
import com.prato.onlinebooklibrary.entity.Review;
import com.prato.onlinebooklibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Integer> {
    Optional<Reserve> findByUserAndBook(User user, Book book);
    List<Reserve> findByBookAndBookStatus(Book book, Reserve.BookStatus bookStatus);
}
