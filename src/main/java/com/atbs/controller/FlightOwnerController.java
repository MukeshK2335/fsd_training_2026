package com.atbs.controller;

import com.atbs.dto.*;
import com.atbs.model.FlightOwner;

import com.atbs.service.FlightOwnerService;
import com.atbs.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/flight-owner")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class FlightOwnerController {
    private final ScheduleService scheduleService;
    private final FlightOwnerService flightOwnerService;
    @GetMapping("/all")
    public FlightOwnerPageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                      @RequestParam(defaultValue = "10",required = false) int size){
        return flightOwnerService.getAll(page,size);
    }

    @GetMapping("/getby-Id/{id}")
    public FlightOwnerGetByIdDto getByidWithOwnerCheck(@PathVariable int id, Principal principal){
        String userName=principal.getName();
        return flightOwnerService.getByIdWithOwnerCheck(id,userName);
    }

    @GetMapping("/stat")
    public CombineStatDto getStat(Principal principal){
        String username= principal.getName();
         return  flightOwnerService.getStat(username);
    }
    @GetMapping("/stat/schedule-status")
    public ChartDto getScheduleStatusStat(Principal principal) {

        String username = principal.getName();

        return scheduleService.getScheduleStatusStat(username);
    }

    @GetMapping("/profile")
    public FlightOwner getDetails(Principal principal){
        String username= principal.getName();

        return flightOwnerService.getDetails(username);
    }

    @PostMapping("/reset/password")
    public void resetPassword(@RequestBody PasswordsReqDto passwordsReqDto, Principal principal){
        String username= principal.getName();
        flightOwnerService.resetPassword(passwordsReqDto,username);
    }

}
