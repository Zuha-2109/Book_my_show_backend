package com.example.Book_my_show_backend.Models;

import com.example.Book_my_show_backend.Enums.SeatType;
import jakarta.persistence.*;

@Entity
@Table(name = "theater_seats")
public class TheaterSeatEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;

    @Column(unique = true,columnDefinition = "seat_no", nullable = false)
   private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;
}
