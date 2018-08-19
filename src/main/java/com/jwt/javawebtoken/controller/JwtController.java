package com.jwt.javawebtoken.controller;

import com.jwt.javawebtoken.domain.User;
import com.jwt.javawebtoken.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class JwtController {

    @Autowired
    private GenericService userService;

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }

    @RequestMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Hello World",HttpStatus.OK);
    }
}