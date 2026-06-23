package com.atbs.controller;

import com.atbs.dto.TicketRespDto;
import com.atbs.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/generate")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class TicketController {

    private final TicketService ticketService;
    @GetMapping("/ticket/{bookingId}")
    public TicketRespDto generateTicket(@PathVariable int bookingId, Principal principal){
        String userName= principal.getName();
        return ticketService.generate(bookingId,userName);
    }
}
