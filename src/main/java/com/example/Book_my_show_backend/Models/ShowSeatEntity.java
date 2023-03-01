package com.example.Book_my_show_backend.Models;

import com.example.Book_my_show_backend.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rate;

    private int seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean booked;


    //when the ticket was actually booked no required of @created annotataion
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;
}
