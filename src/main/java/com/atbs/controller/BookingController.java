package com.atbs.controller;

import com.atbs.dto.BookingPageResp;
import com.atbs.dto.BookingReqDto;
import com.atbs.dto.BookingRespDto;
import com.atbs.model.Booking;
import com.atbs.service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/all")
    public BookingPageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                  @RequestParam(defaultValue = "10",required = false) int size){
        return bookingService.getAll(page,size);
    }

    @GetMapping("/flight-owner")
    public BookingPageResp getByFlightOwner(@RequestParam(defaultValue = "0",required = false) int page,
                                            @RequestParam(defaultValue = "10",required = false) int size,Principal principal){
        String username= principal.getName();
        return bookingService.getByFlightOwner(page,size,username);
    }

    @GetMapping("/getByPassenger-Id/{id}")
    public List<BookingRespDto> getByPassengerId(@PathVariable int id,Principal principal){
        String userName= principal.getName();
        return bookingService.getByPassengerId(id,userName);
    }

    @PostMapping("/addBooking")
    public Booking add(@Valid @RequestBody BookingReqDto dto, Principal principal){
        String userName= principal.getName();
         return  bookingService.addBooking(dto,userName);
    }
    @GetMapping("/schedule/{id}")
    public List<BookingRespDto> getByScheduleId(@PathVariable int id,Principal principal){
        String userName= principal.getName();
        return bookingService.getByScheduleId(id,userName);
    }

    @GetMapping("/history")
    public BookingPageResp history(@RequestParam(defaultValue = "0",required = false) int page,
                                        @RequestParam(defaultValue = "10",required = false) int size,Principal principal){
        String userName=principal.getName();
        return bookingService.history(page,size,userName);
    }

    @GetMapping("get-by/{id}")
    public BookingRespDto getById(@PathVariable int id){
        return bookingService.getByIdResp(id);
    }

    @GetMapping("/ticket")
    public  List<BookingRespDto> ticket(Principal principal){
        String userName=principal.getName();
        return bookingService.ticket(userName);
    }


}
