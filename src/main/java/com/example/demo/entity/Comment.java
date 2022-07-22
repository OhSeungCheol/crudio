package com.example.demo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CARD_ID")
    private Card card;
    private String message;
    private Date date;
    private String writer;
}
