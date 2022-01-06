package com.SelimGezer.myDemo.Mapper;

import com.SelimGezer.myDemo.Dtos.CommentAddDto;
import com.SelimGezer.myDemo.Dtos.CommentRequestDto;
import com.SelimGezer.myDemo.Dtos.CommentResponceDto;
import com.SelimGezer.myDemo.Entities.Comment;

public class CommentMapper {

    public static CommentResponceDto toDto(Comment comment){
        CommentResponceDto commentResponceDto=new CommentResponceDto();
        commentResponceDto.setComment(comment.getComment());
        commentResponceDto.setCommentDate(comment.getCommentDate());
        commentResponceDto.setUser(comment.getUser());
        return commentResponceDto;
    }

    public static Comment toEntity(CommentRequestDto commentRequestDto) {
        Comment comment=new Comment();
        comment.setComment(commentRequestDto.getComment());
        return comment;
    }
   /* public  static Comment toEntity(CommentAddDto commentAddDto){
        Comment comment=new Comment();
        comment.setComment(commentAddDto.getComment());
        comment.setCommentDate(commentAddDto.getCommentDate());
        comment.setUser(commentAddDto.getUser());
    }*/
}
