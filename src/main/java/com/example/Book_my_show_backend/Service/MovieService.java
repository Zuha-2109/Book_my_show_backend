package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Convertors.MovieConvertor;
import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.Repository.MovieRepository;
import com.example.Book_my_show_backend.RequestDtos.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void createMovie(MovieRequestDto movieRequestDto)throws Exception{
        MovieEntity movie= MovieConvertor.covertDtoToEntity(movieRequestDto);
        movieRepository.save(movie);
    }

    public MovieEntity getMovie(String name)throws Exception{
        MovieEntity movie=movieRepository.findByName(name);
        return movie;
    }
}
