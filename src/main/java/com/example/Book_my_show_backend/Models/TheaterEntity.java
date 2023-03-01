package com.example.Book_my_show_backend.Models;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theater")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(nullable = false)
    private String name;

    private String city;

    private String address;

    //List of shows
    //Assumming theater having only one screen

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<ShowEntity> listOfShows;

    //List of theater seats
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<TheaterSeatEntity> theaterSeatEntityList;


}
