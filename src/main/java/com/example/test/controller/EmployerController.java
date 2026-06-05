package com.example.test.controller;

import com.example.test.dto.JobReqDto;
import com.example.test.service.EmployerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.security.Principal;

@RestController
@RequestMapping("/api/employer")
@AllArgsConstructor
public class EmployerController {
    private final EmployerService employerService;

    @PostMapping("/add-job")
    public void addJobPost(@Valid @RequestBody JobReqDto dto, Principal principal){
        String username= principal.getName();
        employerService.addJob(dto,username);
    }
}
