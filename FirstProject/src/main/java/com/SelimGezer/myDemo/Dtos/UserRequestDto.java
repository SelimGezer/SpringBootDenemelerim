package com.SelimGezer.myDemo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {

    String name;
    String surname;
    String email;

}
