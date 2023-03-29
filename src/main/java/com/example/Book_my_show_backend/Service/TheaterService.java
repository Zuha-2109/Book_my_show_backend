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

    public void createTheatre(TheaterRequestDto theatreRequestDto) throws Exception{
        TheaterEntity theatre= TheaterConvertor.convertDtoToEntity(theatreRequestDto);

        List<TheaterSeatEntity> theatreSeatsList=createTheatreSeats();

        theatre.setTheaterSeatEntityList(List(theaterSeatEntityList));

        for(TheaterSeatEntity theatreSeatentity: theatre.getTheaterSeatEntityList()){
            theatreSeatentity.setTheater(theatre);
        }

        theaterRepository.save(theatre);
    }

    public List<TheaterSeatEntity> createTheatreSeats(){

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
            TheaterEntity theatre=theaterRepository.findById(theaterId).get();
            return TheaterResponseDto.builder().id(theatre.getId()).address(theatre.getAddress())
                    .city(theatre.getCity()).name(theatre.getName()).build();

        }
        else throw new Exception("Theatre Not Found");
    }


    public List<TheaterResponseDto> getAllTheater() throws Exception{
        List<TheaterEntity> theatreList=theaterRepository.findAll();
        List<TheaterResponseDto> theatreResponseDtoList=new ArrayList<>();
        for(TheaterEntity theatre:theatreList){
            TheaterResponseDto theatreResponseDto=TheaterResponseDto.builder().id(theatre.getId())
                    .name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();
            theatreResponseDtoList.add(theatreResponseDto);
        }
        return theatreResponseDtoList;
    }


    public List<TheaterResponseDto> getAllTheaterByMovie(String movieName){
        List<ShowEntity> showList=showRepository.findAll();
        List<TheaterResponseDto> theatreResponseDtoList=new ArrayList<>();
        for(ShowEntity show:showList){

            if(show.getMovie().getName().equals(movieName)){
                TheaterEntity theatre=show.getTheater();
                TheaterResponseDto theatreResponseDto=TheaterResponseDto.builder().id(theatre.getId())
                        .name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();

                if(!theatreResponseDtoList.contains(theatreResponseDto)){
                    theatreResponseDtoList.add(theatreResponseDto);
                }
            }

        }
        return theatreResponseDtoList;
    }
}
