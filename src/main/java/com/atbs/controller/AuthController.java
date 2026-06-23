package com.atbs.controller;

import com.atbs.dto.*;
import com.atbs.model.User;
import com.atbs.service.AdminService;
import com.atbs.service.FlightOwnerService;
import com.atbs.service.PassengerService;
import com.atbs.service.UserService;
import com.atbs.utility.JwtUtility;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")
public class AuthController {
    private final UserService userService;
    private final JwtUtility jwtUtility;
    private final AdminService adminService;
    private final PassengerService passengerService;
    private final FlightOwnerService flightOwnerService;
    @GetMapping("/login")
    public TokenRespDto login(Principal principal){

        String username= principal.getName();
        String token=jwtUtility.generateToken(username);
        return new TokenRespDto(
                username,
                token
        );

    }
    @GetMapping("/get-details")
    public LoginRespDto getDetail(Principal principal){
        String username= principal.getName();
        User user=(User) userService.loadUserByUsername(username);
        return new LoginRespDto(
                user.getId(),
                user.getUsername(),
                user.getRole().toString()
        );
    }
    @PostMapping("/admin/add")
    public void addAdmin(@Valid @RequestBody AdminReqDto dto){
        adminService.add(dto);
    }

    @PostMapping("/passenger/add")
    public void addPassenger( @Valid @RequestBody PassengerReqDto dro){
        passengerService.add(dro);
    }

    @PostMapping("/flight-owner/add")
    public void addFlightOwner(@Valid @RequestBody FlightOwnerReqDto dto){
        flightOwnerService.add(dto);
    }

}
