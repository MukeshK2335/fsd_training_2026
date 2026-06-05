package com.example.test.controller;

import com.example.test.dto.LoginRespDto;
import com.example.test.dto.RegisterReqDto;
import com.example.test.dto.TokenRespDto;
import com.example.test.model.User;
import com.example.test.service.RegisterService;
import com.example.test.service.UserService;
import com.example.test.utility.JwtUtility;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtility jwtUtility;
    private final RegisterService registerService;
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

    @PostMapping("/register")
    public void register(@Valid @RequestBody  RegisterReqDto dto){
        registerService.add(dto);
    }


}
