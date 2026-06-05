package com.example.test.controller;

import com.example.test.dto.JobRespDto;
import com.example.test.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
public class JobsController {
    private  final JobService jobService;
    @GetMapping("/get-all")
    public List<JobRespDto> getAll(@RequestParam int page, @RequestParam int size){
        return  jobService.getClass(page,size);
    }
}
