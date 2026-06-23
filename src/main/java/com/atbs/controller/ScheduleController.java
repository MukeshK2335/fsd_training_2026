package com.atbs.controller;

import com.atbs.dto.SchedulePageResp;
import com.atbs.dto.ScheduleReqDto;
import com.atbs.dto.ScheduleRespDto;

import com.atbs.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class
ScheduleController {
    private final ScheduleService scheduleService;
    @GetMapping("/all")
    public SchedulePageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                   @RequestParam(defaultValue = "10",required = false) int size){
        return scheduleService.getAll(page,size);
    }

    @GetMapping("/getBy-Id/{id}")
    public ScheduleRespDto getById(@PathVariable int id){
        return scheduleService.getById(id);
    }

    @GetMapping("/search")
    public List<ScheduleRespDto> getBySearch(@RequestParam String origin, @RequestParam String destination, @RequestParam LocalDate date){
        return scheduleService.getBySearch(origin,destination,date);
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody ScheduleReqDto dto, Principal principal){
        String userName= principal.getName();
        scheduleService.add(dto,userName);
    }

    @GetMapping("/all/flight-owner")
    public SchedulePageResp getAllFlightOwner(@RequestParam(defaultValue = "0",required = false) int page,
                                                   @RequestParam(defaultValue = "10",required = false) int size,Principal principal){
        String userName= principal.getName();
        return scheduleService.getAllFlightOwner(page,size,userName);
    }



    @PutMapping("/delay/{id}")
    public void delaySchedule(@PathVariable int id){
        scheduleService.delaySchedule(id);
    }

    @PutMapping("/cancel/{id}")
    public void cancelSchedule(@PathVariable int id){
        scheduleService.cancelSchedule(id);
    }

    @PutMapping("/active/{id}")
    public void activeSchedule(@PathVariable int id){
        scheduleService.activeSchedule(id);
    }


}
