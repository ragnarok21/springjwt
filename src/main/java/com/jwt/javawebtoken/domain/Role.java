package com.jwt.javawebtoken.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;
}