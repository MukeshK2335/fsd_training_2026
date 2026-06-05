package com.example.test.service;

import com.example.test.dto.ApplicationReqDto;
import com.example.test.dto.ApplicationRespDto;
import com.example.test.exception.JobSeekerNotFoundException;
import com.example.test.mapper.ApplicationMapper;
import com.example.test.model.Application;
import com.example.test.model.Job;
import com.example.test.model.JobSeeker;
import com.example.test.repository.ApplicationRepository;
import com.example.test.repository.JobSeekerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final JobSeekerRepository jobSeekerRepository;
    private final JobService jobService;
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    public void add(@Valid ApplicationReqDto dto, String userName) {
        JobSeeker jobSeeker=jobSeekerRepository.getByUsername(userName).orElseThrow(()->new JobSeekerNotFoundException("Not Found"));
        Job job=jobService.getById(dto.jobId());
        Application application=applicationMapper.mapEntity(jobSeeker,job);
        applicationRepository.save(application);
    }

    public List<ApplicationRespDto> getAll(int page,int size,String userName) {
        Pageable pageable= PageRequest.of(page,size);
        List<Application> applications=applicationRepository.findAll(pageable).getContent();
        List<ApplicationRespDto> dto=applications.stream().map(applicationMapper::mapEntityToDto).toList();
        return dto;

    }
}
