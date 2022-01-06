package com.SelimGezer.myDemo.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CommentAddDto extends CommentRequestDto{

    private String comment;
    private Date commentDate;
    private Long postId;
    private Long userId;

}
