package com.atbs.controller;

import com.atbs.model.Seat;
import com.atbs.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")
public class
SeatController {
    private  final SeatService seatService;
    @GetMapping("/available/{scheduleId}")
    public List<Seat> getAvailableSeats(
            @PathVariable Integer scheduleId){
        return seatService.getAvailableSeats(scheduleId);
    }
}
