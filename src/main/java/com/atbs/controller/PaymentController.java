package com.atbs.controller;

import com.atbs.dto.PaymentPageResp;
import com.atbs.dto.PaymentReqDto;
import com.atbs.dto.PaymentRespDto;
import com.atbs.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/add/{bookingId}")
    public void add(@PathVariable int bookingId, @RequestBody PaymentReqDto dto, Principal principal){
        String userName=principal.getName();
        paymentService.add(bookingId,userName,dto);
    }
    @GetMapping("/booking/{bookingId}")
    public PaymentRespDto getPaymentByBookingId(@PathVariable int bookingId, Principal principal){
        String userName=principal.getName();
        return paymentService.getPaymentByBookingId(bookingId,userName);
    }

    @GetMapping("/{paymentId}")
    public PaymentRespDto getPaymentByPaymentId(@PathVariable int paymentId){
        return paymentService.getPaymentByPaymentId(paymentId);
    }

    @GetMapping("/all")
    public PaymentPageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                  @RequestParam(defaultValue = "10",required = false) int size){
        return paymentService.getAll(page,size);
    }

    @GetMapping("/all/flight-owner")
    public PaymentPageResp getAllFlightOwner(@RequestParam(defaultValue = "0",required = false) int page,
                                             @RequestParam(defaultValue = "10",required = false) int size,Principal principal){
        String username= principal.getName();
        return paymentService.getAllFlightOwner(page,size,username);
    }
}
