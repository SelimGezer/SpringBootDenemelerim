package com.SelimGezer.myDemo.Dtos;

import com.SelimGezer.myDemo.Entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponceDto {

    String comment;
    Date commentDate;
    User user;

}
