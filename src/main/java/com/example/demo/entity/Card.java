package com.example.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private Date date;
    private String writer;
    @OneToMany(mappedBy = "card")
    private final List<Comment> comments = new ArrayList<>();

}
