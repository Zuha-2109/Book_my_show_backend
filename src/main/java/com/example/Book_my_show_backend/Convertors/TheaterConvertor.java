package com.example.Book_my_show_backend.Convertors;

import com.example.Book_my_show_backend.Models.TheaterEntity;
import com.example.Book_my_show_backend.RequestDtos.TheaterRequestDto;

public class TheaterConvertor {

    public static TheaterEntity convertDtoToEntity(TheaterRequestDto theatreRequestDto) {
        TheaterEntity theatre = TheaterEntity.builder()
                .name(theatreRequestDto.getName())
                .address(theatreRequestDto.getAddress())
                .city(theatreRequestDto.getCity()).build();

        return theatre;
    }
}
