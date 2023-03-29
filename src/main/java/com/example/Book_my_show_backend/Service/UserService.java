package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Convertors.UserConvertor;
import com.example.Book_my_show_backend.Models.ShowEntity;
import com.example.Book_my_show_backend.Models.TicketEntity;
import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.Repository.TicketRepository;
import com.example.Book_my_show_backend.Repository.UserRepository;
import com.example.Book_my_show_backend.RequestDtos.UserRequestDto;
import com.example.Book_my_show_backend.ResponseDtos.UserBookTicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;



    public void createUser(UserRequestDto userRequestDto)throws Exception{
        UserEntity user= UserConvertor.convertDtoToEntity(userRequestDto);
        userRepository.save(user);
    }

    public List<UserBookTicketDto> getUserBookedTickets(int userId){
        List<TicketEntity> ticketList=ticketRepository.findAll();
        List<UserBookTicketDto> userBookedTicketsDtoList=new ArrayList<>();
        for(TicketEntity ticket:ticketList){
            if(ticket.getUser().getId()==userId){
                ShowEntity show=ticket.getShow();

                UserBookTicketDto userBookedTicketsDto=new UserBookTicketDto();

                userBookedTicketsDto.setTicketId(ticket.getId());
                userBookedTicketsDto.setAllottedSeats(ticket.getAllottedSeat());
                userBookedTicketsDto.setAmount(ticket.getAmount());
                userBookedTicketsDto.setShowId(show.getId());
                userBookedTicketsDto.setMovieName(show.getMovie().getMovieName());
                userBookedTicketsDto.setTheaterName(show.getTheater().getName());
                userBookedTicketsDto.setShowDate(show.getShowDate());
                userBookedTicketsDto.setShowTime(show.getShowTime());

                userBookedTicketsDtoList.add(userBookedTicketsDto);
            }
        }
        return userBookedTicketsDtoList;

    }
}
