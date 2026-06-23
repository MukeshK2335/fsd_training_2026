package com.atbs.controller;

import com.atbs.dto.ChartDto;
import com.atbs.dto.CombineStatDto;
import com.atbs.service.AdminService;
import com.atbs.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class AdminController {
    private final BookingService bookingService;
    private final AdminService adminService;
    @GetMapping("/stat")
    public CombineStatDto getCombineStat(){

        return adminService.getCombineStat();
    }
    @GetMapping("/stat/bookings-by-flight-owner")
    public ChartDto getBookingsByFlightOwnerStat() {
        return bookingService.getBookingsByFlightOwnerStat();
    }
}
