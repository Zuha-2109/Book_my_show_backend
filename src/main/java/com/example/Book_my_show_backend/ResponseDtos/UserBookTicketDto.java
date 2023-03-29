package com.example.Book_my_show_backend.ResponseDtos;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UserBookTicketDto {


    private int ticketId;

    private String allottedSeats;

    private int amount;

    private int showId;

    private String movieName;

    private String theatreName;

    private LocalDate showDate;

    private LocalTime showTime;
}
