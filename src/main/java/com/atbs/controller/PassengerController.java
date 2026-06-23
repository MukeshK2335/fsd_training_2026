package com.atbs.controller;

import com.atbs.dto.*;

import com.atbs.model.Passenger;
import com.atbs.service.BookingService;
import com.atbs.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@RestController
@RequestMapping("/api/passenger")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PassengerController {
    private final BookingService bookingService;
    private final PassengerService passengerService;
    @GetMapping("/all")
    public PassengerPageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                    @RequestParam(defaultValue = "10",required = false) int size){
        return passengerService.getAll(page,size);
    }

    @GetMapping("/getby-Id/{id}")
    public PassenegrGetDto getPassengerBy(@PathVariable int id, Principal principal){
        String userName= principal.getName();
        return passengerService.getByIdOwnerCheck(id,userName);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        passengerService.delete(id);
    }

    @PostMapping("/id/upload")
    public void upload(Principal principal, @RequestParam("file") MultipartFile file) throws IOException {
        String userName= principal.getName();
        passengerService.upload(userName,file);
    }



    @GetMapping("/profile")
    public Passenger getDetail(Principal principal){
        String username= principal.getName();
        return passengerService.getDetail(username);
    }

    @GetMapping("/stat/booking-status")
    public ChartDto getBookingStatusStat(Principal principal) {
        String username = principal.getName();

        return bookingService.getBookingStatusStat(username);
    }

    @GetMapping("/stat")
    public CombineStatDto getStat(Principal principal){
        String username= principal.getName();
        return  passengerService.getStat(username);
    }

    @PostMapping("/reset/password")
    public void resetPassword(@RequestBody PasswordsReqDto passwordsReqDto, Principal principal){
        String username= principal.getName();
        passengerService.resetPassword(passwordsReqDto,username);
    }

}
