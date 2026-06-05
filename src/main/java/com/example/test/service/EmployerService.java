package com.example.test.service;

import com.example.test.dto.JobReqDto;
import com.example.test.exception.EmployerNotFoundException;
import com.example.test.mapper.JobMapper;
import com.example.test.model.Employer;
import com.example.test.model.Job;
import com.example.test.repository.EmployerRepository;
import com.example.test.repository.JobRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final JobMapper jobMapper;
    private final JobRepository jobRepository;
    public void addJob(@Valid JobReqDto dto, String username) {
        Employer employer=employerRepository.getByUsername(username).orElseThrow(()->new EmployerNotFoundException("Not Found"));
        Job job=jobMapper.mapDtoToEntity(dto);
        job.setEmployer(employer);
        jobRepository.save(job);
    }
}
