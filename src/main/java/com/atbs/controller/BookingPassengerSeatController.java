package com.atbs.controller;

import com.atbs.dto.BookingPassengerSeatRepDto;
import com.atbs.service.BookingPassengerSeatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking-pass-seat")
@AllArgsConstructor
public class BookingPassengerSeatController {

    private final BookingPassengerSeatService bookingPassengerSeatService;
    @PostMapping("/add/{bookingId}/{passengerId}/{seatId}")
    public void addBookingPassengerSeat(@PathVariable int bookingId, @PathVariable int passengerId,
                                        @PathVariable int seatId, @RequestBody BookingPassengerSeatRepDto dto){

         bookingPassengerSeatService.add(bookingId,passengerId,seatId,dto);


    }
}
