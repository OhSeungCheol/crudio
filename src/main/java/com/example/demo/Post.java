package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date date;
    private String writer;

    public Post(String title, String content, Date date, String writer){
        this.title = title;
        this.content = content;
        this.date = date;
        this.writer = writer;
    }

    public Post(){}
}
