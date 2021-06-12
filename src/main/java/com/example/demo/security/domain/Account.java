package com.example.demo.security.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    Long id;
    String username;
    String password;
    String email;
    String age;
    String role;

}
