package com.example.Book_my_show_backend.Controllers;


import com.example.Book_my_show_backend.RequestDtos.TicketRequestDto;
import com.example.Book_my_show_backend.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            ticketService.bookTicket(ticketRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Tickets Are Booked",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelTicket(@RequestParam int ticketId){
        try{
            int refund=ticketService.cancelTicket(ticketId);
            return new ResponseEntity<>(refund+" is Returned",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
