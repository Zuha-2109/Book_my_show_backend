package com.example.Book_my_show_backend.Convertors;

import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.RequestDtos.MovieRequestDto;

public class MovieConvertor {

    public static MovieEntity covertDtoToEntity(MovieRequestDto movieRequestDto){
        MovieEntity movie=MovieEntity.builder().name(movieRequestDto.getName()).
                duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        return movie;
    }
}
