package com.example.Book_my_show_backend.Convertors;

import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.RequestDtos.UserRequestDto;

public class UserConvertor {

    public static UserEntity convertDtoToEntity(UserRequestDto userRequestDto){
        UserEntity user = UserEntity.builder()
                .name(userRequestDto.getName())
                .mobile(userRequestDto.getMobileNo()).build();
        return user;
    }
}
