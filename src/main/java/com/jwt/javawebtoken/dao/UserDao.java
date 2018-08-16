package com.jwt.javawebtoken.dao;

import com.jwt.javawebtoken.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User findByUsername(String username);

    @Query("select u from User u where u.username = ?1")
    User usernameExist(String username);
}
