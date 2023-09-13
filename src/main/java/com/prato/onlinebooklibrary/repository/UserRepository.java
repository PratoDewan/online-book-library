package com.prato.onlinebooklibrary.repository;

import com.prato.onlinebooklibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(Integer userId);

    Optional<User> findByEmail(String email);
}
