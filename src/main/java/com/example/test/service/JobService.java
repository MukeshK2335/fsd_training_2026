package com.example.test.service;

import com.example.test.dto.JobRespDto;
import com.example.test.exception.JobNotFound;
import com.example.test.mapper.JobMapper;
import com.example.test.model.Job;
import com.example.test.repository.JobRepository;
import com.example.test.repository.JobSeekerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    public List<JobRespDto> getClass(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        List<Job> jobs=jobRepository.findAll(pageable).getContent();
        List<JobRespDto> dto=jobs.stream().map(jobMapper::mapEntityToDto).toList();
        return dto;
    }

    public Job getById(int i) {
        Job job=jobRepository.findById(i).orElseThrow(()->new JobNotFound("Job Not Found"));
        return job;
    }
}
