package com.jwt.javawebtoken.dao;

import com.jwt.javawebtoken.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository <Role, Long> {

    @Query("select r from Role r where r.roleName = ?1")
    Role getRoleByRoleName(String roleName);
}
