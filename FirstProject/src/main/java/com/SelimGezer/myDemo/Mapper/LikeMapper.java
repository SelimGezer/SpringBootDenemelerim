package com.SelimGezer.myDemo.Mapper;

import com.SelimGezer.myDemo.Dtos.LikeResponceDto;
import com.SelimGezer.myDemo.Entities.Like;

public class LikeMapper {

    public static LikeResponceDto toDto(Like like){
        LikeResponceDto likeResponceDto=new LikeResponceDto(like.getUser());
        return likeResponceDto;
    }

}
