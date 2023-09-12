package com.prato.onlinebooklibrary.service;

import com.prato.onlinebooklibrary.entity.User;
import com.prato.onlinebooklibrary.model.ResponseDto;
import com.prato.onlinebooklibrary.model.UserDto;

import java.util.List;

public interface UserAuthService {
    ResponseDto createUser(UserDto user) throws Exception;
    UserDto getUser(String email);

    UserDto getUserByUserId(Integer id) throws Exception;
    List<User> findAllUsers();
}
