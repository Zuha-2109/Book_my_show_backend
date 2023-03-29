package com.example.Book_my_show_backend.RequestDtos;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GetShowRequestDto {

    private LocalDateTime from;

    private LocalDateTime to;
}
