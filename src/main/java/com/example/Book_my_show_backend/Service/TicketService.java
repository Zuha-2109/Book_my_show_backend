package com.example.Book_my_show_backend.Service;
import com.example.Book_my_show_backend.Models.ShowEntity;
import com.example.Book_my_show_backend.Models.ShowSeatEntity;
import com.example.Book_my_show_backend.Models.TicketEntity;
import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.Repository.ShowRepository;
import com.example.Book_my_show_backend.Repository.TicketRepository;
import com.example.Book_my_show_backend.Repository.UserRepository;
import com.example.Book_my_show_backend.RequestDtos.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public void bookTicket(TicketRequestDto ticketRequestDto)throws Exception{

        ShowEntity show=showRepository.findById(ticketRequestDto.getShowId()).get();
        UserEntity user=userRepository.findById(ticketRequestDto.getUserId()).get();

        List<String> requestSeats=ticketRequestDto.getAllottedSeats();

        List<ShowSeatEntity> showSeatEntity=show.getListOfSeats();

        List<ShowSeatEntity> bookedSeats=new ArrayList<>();

        for(ShowSeatEntity showSeat: show.getListOfSeats()){
            String seatNo = showSeat.getSeatNo();
            if(!showSeat.isBooked() && requestSeats.contains(seatNo))
            {
                bookedSeats.add(showSeat);
            }
        }

        if(bookedSeats.size()!=requestSeats.size()){
            throw  new Exception("Seats Not Available");
        }


        TicketEntity ticket=new TicketEntity();


        String allottedSeats="";
        int rate=0;
        double amount=0;

        for(ShowSeatEntity showSeat:bookedSeats){
            String seatNo= String.valueOf(showSeat.getSeatNo());
            allottedSeats=allottedSeats+seatNo+",";

            if(seatNo.charAt(0)=='1')
            {
                rate=100;
            }
            else rate=200;

            amount+=rate*show.getMultiplier();

            showSeat.setBooked(true);
            showSeat.setBookedAt(new Date());
            showSeat.setTicket(ticket);
            showSeat.setShow(show);
        }

        ticket.setBookedSeats(bookedSeats);
        ticket.setBookedAt(new Date());
        ticket.setShow(show);
        ticket.setAllottedSeats(allottedSeats);
        ticket.setAmount((int)amount);
        ticket.setUser(user);



        //Bidirectional Relations
//       show.getTicketList().add(ticket);
//       user.getTicketList().add(ticket);



        //  showRepository.save(show);
        //   userRepository.save(user);


        ticketRepository.save(ticket);




    }


    public int cancelTicket(int ticketId)throws Exception{
        TicketEntity ticket=ticketRepository.findById(ticketId).get();

        String allottedSeats=ticket.getAllottedSeats();
        int refund=ticket.getAmount();

        String[] seats=allottedSeats.split(",");

        List<String> seatList=new ArrayList<>(List.of(seats));

        ShowEntity show=ticket.getShow();

        List<ShowSeatEntity> showSeats=show.getShowSeatList();

        for(ShowSeatEntity seats1:showSeats){
            if(seatList.contains(seats1.getSeatNo())){
                seats1.setBookedAt(null);
                seats1.setBooked(false);
                seats1.setTicket(null);
            }
        }

        ticketRepository.delete(ticket);

        return refund;


    }
}
