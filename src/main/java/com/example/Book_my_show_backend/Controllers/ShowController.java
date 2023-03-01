package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.Repository.ShowRepository;

import com.example.Book_my_show_backend.RequestDtos.GetShowRequestDto;
import com.example.Book_my_show_backend.RequestDtos.ShowRequestDto;
import com.example.Book_my_show_backend.ResponseDtos.ShowResponseDto;
import com.example.Book_my_show_backend.Service.ShowService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/show")
@Slf4j
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/create")
    public ResponseEntity<String> createShow(@RequestBody ShowRequestDto showRequestDto){
        try{
            showService.createShow(showRequestDto);
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Show Added Successfully",HttpStatus.ACCEPTED);
    }


    @GetMapping("/get_all_show_between_time")
    public ResponseEntity<List<ShowResponseDto>> getShows(@RequestBody GetShowRequestDto getShowRequestDto){
        try{
            List<ShowResponseDto> showResponseDtoList = showService.getShows(getShowRequestDto);
            return new ResponseEntity<>(showResponseDtoList,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}