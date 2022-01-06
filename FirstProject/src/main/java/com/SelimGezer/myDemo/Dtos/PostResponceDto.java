package com.SelimGezer.myDemo.Dtos;

import com.SelimGezer.myDemo.Entities.Comment;
import com.SelimGezer.myDemo.Entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","user"})
public class PostResponceDto {

    Long id;
    User user;
    List<Comment> comment;


}
