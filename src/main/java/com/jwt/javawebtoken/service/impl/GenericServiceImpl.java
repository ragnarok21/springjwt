package com.jwt.javawebtoken.service.impl;

import com.jwt.javawebtoken.dao.UserDao;
import com.jwt.javawebtoken.domain.User;
import com.jwt.javawebtoken.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericServiceImpl implements GenericService{

    @Autowired
    private UserDao userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

}
