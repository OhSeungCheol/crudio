package com.example.demo.dto;

import com.example.demo.entity.Card;
import com.example.demo.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CardResponseDto {
    private Long id;
    private String message;
    private Date date;
    private String writer;
    private List<CommentResponseDto> comments = new ArrayList<>();

    public CardResponseDto(Card entity){
        this.id = entity.getId();
        this.message = entity.getMessage();
        this.date = entity.getDate();
        this.writer = entity.getWriter();

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment commentEntity : entity.getComments()){
            commentResponseDtoList.add(new CommentResponseDto(commentEntity.getId(), commentEntity.getMessage(), commentEntity.getDate(), commentEntity.getWriter()));
        }
        this.comments = commentResponseDtoList;
    }
}
