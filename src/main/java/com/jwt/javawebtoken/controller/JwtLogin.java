package com.jwt.javawebtoken.controller;

import com.jwt.javawebtoken.domain.User;
import com.jwt.javawebtoken.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtLogin {

    @Autowired
    private GenericService userService;

    @RequestMapping( value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody User user){
        userService.createUser(user);
    }
}
