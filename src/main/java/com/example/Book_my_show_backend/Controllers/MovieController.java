package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.RequestDtos.MovieRequestDto;
import com.example.Book_my_show_backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<String> createMovie(@RequestBody MovieRequestDto movieRequestDto){
        try{
            movieService.createMovie(movieRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Movie Added Successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_movie")
    public ResponseEntity<MovieEntity> getMovie(@RequestParam()String name){
        try{
            MovieEntity movie=movieService.getMovie(name);
            return new ResponseEntity<>(movie, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }


}
