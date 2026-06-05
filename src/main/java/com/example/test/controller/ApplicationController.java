package com.example.test.controller;

import com.example.test.dto.ApplicationReqDto;
import com.example.test.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/add")
    public void addApplication(@Valid @RequestBody ApplicationReqDto dto, Principal principal){
        String userName= principal.getName();
        applicationService.add(dto,userName);
    }
}
