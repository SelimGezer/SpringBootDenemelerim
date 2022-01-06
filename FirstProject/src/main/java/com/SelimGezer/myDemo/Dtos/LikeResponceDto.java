package com.SelimGezer.myDemo.Dtos;

import com.SelimGezer.myDemo.Entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeResponceDto {
    User user;
}
