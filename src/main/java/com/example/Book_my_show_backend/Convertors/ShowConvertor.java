package com.example.Book_my_show_backend.Convertors;

import com.example.Book_my_show_backend.Models.ShowEntity;
import com.example.Book_my_show_backend.RequestDtos.ShowRequestDto;

public class ShowConvertor {

    public static ShowEntity convertDtoToEntity(ShowRequestDto showRequestDto){
        ShowEntity show=ShowEntity.builder()
                .showDate(showRequestDto.getShowDate())
                .showTime(showRequestDto.getShowTime())
              .multiplier(showRequestDto.getMultiplier()).build();
        return show;
    }
}
