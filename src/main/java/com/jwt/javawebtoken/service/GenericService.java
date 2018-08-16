package com.jwt.javawebtoken.service;

import com.jwt.javawebtoken.domain.User;

import java.util.List;

public interface GenericService {

    User findByUsername(String username);

    List<User> findAllUsers();
}
