package com.example.demo.dto;

import com.example.demo.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String message;
    private Date date;
    private String writer;
}
