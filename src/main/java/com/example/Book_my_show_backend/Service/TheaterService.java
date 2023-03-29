package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Convertors.TheaterConvertor;
import com.example.Book_my_show_backend.Enums.SeatType;
import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.Models.ShowEntity;
import com.example.Book_my_show_backend.Models.TheaterEntity;
import com.example.Book_my_show_backend.Models.TheaterSeatEntity;
import com.example.Book_my_show_backend.Repository.ShowRepository;
import com.example.Book_my_show_backend.Repository.TheaterRepository;
import com.example.Book_my_show_backend.Repository.TheaterSeatRepository;
import com.example.Book_my_show_backend.RequestDtos.TheaterRequestDto;
import com.example.Book_my_show_backend.ResponseDtos.TheaterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    ShowRepository showRepository;

    public void createTheater(TheaterRequestDto theaterRequestDto) throws Exception{
        TheaterEntity theater= TheaterConvertor.convertDtoToEntity(theaterRequestDto);

        List<TheaterSeatEntity> theaterSeatList=createTheaterSeat();

        theater.setTheaterSeatEntityList(new List(theaterSeatEntityList));

        for(TheaterSeatEntity theaterSeatEntity: theater.getTheaterSeatEntityList()){
            theaterSeatEntity.setTheater(theaterSeatEntity);
        }

        theaterRepository.save(theater);
    }

    public List<TheaterSeatEntity> createTheaterSeat(){

        List<TheaterSeatEntity> seats=new ArrayList<>();

//        TheatreSeats seats1=new TheatreSeats("A1", SeatType.CLASSIC,100);
//        TheatreSeats seats2=new TheatreSeats("A2",SeatType.CLASSIC,100);
//        TheatreSeats seats3=new TheatreSeats("A3",SeatType.CLASSIC,100);
//        TheatreSeats seats4=new TheatreSeats("A4",SeatType.CLASSIC,100);
//        TheatreSeats seats5=new TheatreSeats("A5",SeatType.CLASSIC,100);
//        TheatreSeats seats6=new TheatreSeats("B1",SeatType.PLATINUM,200);
//        TheatreSeats seats7=new TheatreSeats("B2",SeatType.PLATINUM,200);
//        TheatreSeats seats8=new TheatreSeats("B3",SeatType.PLATINUM,200);
//        TheatreSeats seats9=new TheatreSeats("B4",SeatType.PLATINUM,200);
//        TheatreSeats seats10=new TheatreSeats("B5",SeatType.PLATINUM,200);
//
//        seats.add(seats1);
//        seats.add(seats2);
//        seats.add(seats3);
//        seats.add(seats4);
//        seats.add(seats5);
//        seats.add(seats6);
//        seats.add(seats7);
//        seats.add(seats8);
//        seats.add(seats9);
//        seats.add(seats10);

        for(int i=0;i<5;i++)
        {
            String s1="1"+(char)('A'+i);
            String s2="2"+(char)('A'+i);

            TheaterSeatEntity seats1=new TheaterSeatEntity(s1, SeatType.CLASSIC,100);
            TheaterSeatEntity seats2=new TheaterSeatEntity(s2,SeatType.PLATINUM,200);

            seats.add(seats1);
            seats.add(seats2);

        }
        theaterSeatRepository.saveAll(seats);
        return seats;
    }

    public TheaterResponseDto getTheaterById(int theaterId)throws Exception{
        if(theaterRepository.findById(theaterId).isPresent()){
            TheaterEntity theater=theaterRepository.findById(theaterId).get();
            return TheaterResponseDto.builder().id(theater.getId()).address(theater.getAddress())
                    .city(theater.getCity()).name(theater.getName()).build();

        }
        else throw new Exception("Theater Not Found");
    }


    public List<TheaterResponseDto> getAllTheater() throws Exception{
        List<TheaterEntity> theaterList=theaterRepository.findAll();
        List<TheaterResponseDto> theaterResponseDtoList=new ArrayList<>();
        for(TheaterEntity theater:theaterList){
            TheaterResponseDto theaterResponseDto=TheaterResponseDto.builder().id(theater.getId())
                    .name(theater.getName()).city(theater.getCity()).address(theater.getAddress()).build();
            theaterResponseDtoList.add(theaterResponseDto);
        }
        return theaterResponseDtoList;
    }


    public List<TheaterResponseDto> getAllTheaterByMovie(String movieName){
        List<ShowEntity> showList=showRepository.findAll();
        List<TheaterResponseDto> theaterResponseDtoList=new ArrayList<>();
        for(ShowEntity show:showList){

            if(show.getMovie().getMovieName().equals(movieName)){
                TheaterEntity theater=show.getTheater();
                TheaterResponseDto theaterResponseDto=TheaterResponseDto.builder().id(theater.getId())
                        .name(theater.getName()).city(theater.getCity()).address(theater.getAddress()).build();

                if(!theaterResponseDtoList.contains(theaterResponseDto)){
                    theaterResponseDtoList.add(theaterResponseDto);
                }
            }

        }
        return theaterResponseDtoList;
    }
}
