package com.example.Book_my_show_backend.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRequestDto {

    private String name;

    private String mobileNo;
}
