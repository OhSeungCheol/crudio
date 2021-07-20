package com.example.demo.ticket;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date date;
    private String writer;

    public Ticket(String title, String content, Date date, String writer){
        this.title = title;
        this.content = content;
        this.date = date;
        this.writer = writer;
    }

    public Ticket(){}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public String getWriter() {
        return writer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void throwException(Boolean isException) {
        if(isException){
            throw new IllegalArgumentException("test exception");
        }
    }
}
