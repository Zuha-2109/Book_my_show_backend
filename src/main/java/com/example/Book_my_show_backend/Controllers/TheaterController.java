package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.ResponseDtos.TheaterResponseDto;
import com.example.Book_my_show_backend.Service.TheaterService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/create")
    public ResponseEntity<String> createTheatre(@RequestBody TheaterResponseDto theatreRequestDto){
        try{
            theaterService.createTheater(theatreRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Theater Added Successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("get_by_id")
    public ResponseEntity<TheaterResponseDto> getTheatreById(@RequestParam int theaterId){
        try{
            TheaterResponseDto theater=theaterService.getTheaterById(theaterId);
            return new ResponseEntity<>(theater,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get_all_theater")
    public ResponseEntity<List<TheaterResponseDto>> getAllTheatre(){
        try {
            return new ResponseEntity<>(theaterService.getAllTheater(),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/get_all_theatres_by_movie_name")
    public ResponseEntity<List<TheaterResponseDto>> getAllTheatreByMovie(@RequestParam String movieName){
        try{
            List<TheaterResponseDto> theatreResponseDtoList=theaterService.getAllTheaterByMovie(movieName);
            return new ResponseEntity<>(theatreResponseDtoList,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}
