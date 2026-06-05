package com.example.test.controller;

import com.example.test.dto.ApplicationRespDto;
import com.example.test.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/jobseeker")
@AllArgsConstructor
public class JobSeekerController {
    private final ApplicationService applicationService;
    @GetMapping("/my-application")
    public List<ApplicationRespDto> getMyApplication(@RequestParam int page,@RequestParam int size, Principal principal){
        String userName=principal.getName();
        return applicationService.getAll(page,size,userName);
    }
}
