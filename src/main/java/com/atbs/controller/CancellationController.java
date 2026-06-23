package com.atbs.controller;

import com.atbs.dto.CancelationPageResp;
import com.atbs.dto.CancellationReqDto;
import com.atbs.dto.CancellationRespDto;
import com.atbs.service.CancellationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cancellation")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class CancellationController {
    private final CancellationService cancellationService;
    @PostMapping("/add/{bookingId}")
    public void add(@RequestBody CancellationReqDto dto, @PathVariable int bookingId, Principal principal){
        String userName= principal.getName();
        cancellationService.add(dto,bookingId,userName);
    }

    @GetMapping("/all")
    public CancelationPageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                      @RequestParam(defaultValue = "10",required = false) int size ){
        return cancellationService.getAll(page,size);
    }

    @GetMapping("/passenger")
    public CancelationPageResp getAllPass(@RequestParam(defaultValue = "0",required = false) int page,
                                                @RequestParam(defaultValue = "10",required = false) int size,
                                                Principal principal){
        String userName= principal.getName();
        return cancellationService.getAllPass(page,size,userName);
    }

    @GetMapping("/flight-owner")
    public CancelationPageResp getAllFlight(@RequestParam(defaultValue = "0",required = false) int page,
                                                @RequestParam(defaultValue = "10",required = false) int size,
                                                Principal principal){
        String userName= principal.getName();
        return cancellationService.getAllFlight(page,size,userName);
    }

    @GetMapping("/{cancellationId}")
    public CancellationRespDto getById(@PathVariable int cancellationId){
        return cancellationService.getById(cancellationId);
    }
    @PutMapping("/approve/{id}")
    public void approve(@PathVariable int id){
        cancellationService.approve(id);
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable int id){
        cancellationService.reject(id);
    }

    @GetMapping("/request/flight-owner")
    public CancelationPageResp getReqCancellation(@RequestParam(defaultValue = "0",required = false) int page,
                                                  @RequestParam(defaultValue = "10",required = false) int size,
                                                  Principal principal){
        String username= principal.getName();
        return cancellationService.getReqCancellation(page,size,username);

    }




}
