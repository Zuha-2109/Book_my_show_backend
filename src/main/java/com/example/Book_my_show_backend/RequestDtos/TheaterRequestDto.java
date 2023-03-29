package com.example.Book_my_show_backend.RequestDtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TheaterRequestDto {

    @Column(nullable = false)

    private String name;

    private String city;

    private  String address;
}
