package com.example.Book_my_show_backend.Service;


import com.example.Book_my_show_backend.Convertors.ShowConvertor;
import com.example.Book_my_show_backend.Models.*;
import com.example.Book_my_show_backend.Repository.MovieRepository;
import com.example.Book_my_show_backend.Repository.ShowRepository;
import com.example.Book_my_show_backend.Repository.ShowSeatRepository;
import com.example.Book_my_show_backend.Repository.TheaterRepository;
import com.example.Book_my_show_backend.RequestDtos.GetShowRequestDto;
import com.example.Book_my_show_backend.RequestDtos.ShowRequestDto;
import com.example.Book_my_show_backend.ResponseDtos.ShowResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
     ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatsRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theatreRepository;


    public void createShow(ShowRequestDto showRequestDto)throws Exception{
        ShowEntity show= ShowConvertor.convertDtoToEntity(showRequestDto);

        TheaterEntity theatre=theatreRepository.findById(showRequestDto.getTheaterId()).get();
        MovieEntity movie=movieRepository.findByName(showRequestDto.getMovieName());


        show.setMovie(movie);
        show.setTheater(theatre);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        List<ShowSeatEntity> showSeatsList=createShowSeats(theatre.getTheaterSeatEntityList());

        show.setShowSeatList(showSeatList);

        for(ShowSeatEntity showSeats:showSeatList){
            showSeats.setShow(show);
        }


        movieRepository.save(movie);
        theatreRepository.save(theatre);

        //  showRepository.save(show);

    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theatreSeatsList){
        List<ShowSeatEntity> seats=new ArrayList<>();

        for(TheaterSeatEntity theatreSeats:theatreSeatsList){
            ShowSeatEntity showSeats=ShowSeatEntity.builder().seatType(theatreSeats.getSeatType()).
                    seatNo(theatreSeats.getSeatNo()).build();
            seats.add(showSeats);
        }

        showSeatsRepository.saveAll(seats);

        return seats;

    }

    public List<ShowResponseDto> getShows(GetShowRequestDto getShowRequestDto){

        LocalDateTime from=getShowRequestDto.getFrom();
        LocalDateTime to=getShowRequestDto.getTo();



        List<ShowEntity> showList=showRepository.findAll();



        List<ShowResponseDto> showResponseDtoList=new ArrayList<>();


        for(ShowEntity show:showList){
            LocalDateTime showDateTime=LocalDateTime.of(show.getShowDate(),show.getShowTime());
            if(showDateTime.compareTo(from)>=0 && showDateTime.compareTo(to)<=0)
            {
                ShowResponseDto showResponseDto=ShowResponseDto.builder().id(show.getId()).showDate(show.getShowDate())
                        .showTime(show.getShowTime()).movieName(show.getMovie().getName())
                        .theatreId(show.getTheater().getId()).multiplier(show.getMultiplier()).build();
                showResponseDtoList.add(showResponseDto);
            }
        }

        return showResponseDtoList;
    }
}
