package com.jwt.javawebtoken.service.impl;

import com.google.common.hash.Hashing;
import com.jwt.javawebtoken.dao.UserDao;
import com.jwt.javawebtoken.domain.Role;
import com.jwt.javawebtoken.domain.User;
import com.jwt.javawebtoken.exception.UsernameException;
import com.jwt.javawebtoken.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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

    @Override
    public void createUser(User user) {

        User userExist = userRepository.usernameExist(user.getUsername());

        if(userExist != null){
            throw new UsernameException("Ya existe un usuario con ese username");
        }

        encryptPasswordSha(user);
        Role role = new Role();
        role.setDescription("Standard Role");
        role.setRoleName("STANDARD_USER");
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    private void encryptPasswordSha(User user){
       String sha256 = Hashing.sha256()
                          .hashString(user.getPassword(), StandardCharsets.UTF_8)
                          .toString();
       user.setPassword(sha256);
    }
}
