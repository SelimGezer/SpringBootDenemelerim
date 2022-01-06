package com.SelimGezer.myDemo.Dtos;

import lombok.Data;

@Data
public class LikeRequestDto {

    private final Long postId;
    private final Long userId;

}
