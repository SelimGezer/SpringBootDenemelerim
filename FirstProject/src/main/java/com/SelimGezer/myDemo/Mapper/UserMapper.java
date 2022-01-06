package com.SelimGezer.myDemo.Mapper;


import com.SelimGezer.myDemo.Dtos.UserRequestDto;
import com.SelimGezer.myDemo.Dtos.UserResponceDto;
import com.SelimGezer.myDemo.Entities.User;

public class UserMapper {

    public static User toEntity(UserRequestDto userRequestDto){
        User user=new User();
        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setEmail(userRequestDto.getEmail());
        return  user;
    }

    public static UserResponceDto toDto(User user){
        UserResponceDto userResponceDto=new UserResponceDto(user.getName(),user.getSurname(),user.getEmail());
        return userResponceDto;
    }

}
