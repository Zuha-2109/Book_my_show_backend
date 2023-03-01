package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.RequestDtos.UserRequestDto;
import com.example.Book_my_show_backend.ResponseDtos.UserBookTicketDto;
import com.example.Book_my_show_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            userService.createUser(userRequestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User Added Successfully", HttpStatus.ACCEPTED);
    }


    @GetMapping("/get_user_booked_tickets")
    public ResponseEntity<List<UserBookTicketDto>> getUserBookedTickets(@RequestParam int userId) {
        try {
            List<UserBookTicketDto> userBookedTicketsDtoList = userService.getUserBookedTickets(userId);
            return new ResponseEntity<>(userBookedTicketsDtoList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
