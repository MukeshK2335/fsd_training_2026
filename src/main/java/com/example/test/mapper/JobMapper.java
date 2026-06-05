package com.example.test.mapper;

import com.example.test.dto.JobReqDto;
import com.example.test.dto.JobRespDto;
import com.example.test.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public Job mapDtoToEntity(JobReqDto dto){
        Job job=new Job();
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setSalary(dto.salary());
        job.setLocation(dto.location());
        return job;
    }

    public JobRespDto mapEntityToDto(Job job){
        return new JobRespDto(
                job.getId(),
                job.getTitle(),
                job.getLocation(),
                job.getSalary(),
                job.getEmployer().getCompanyName()
        );
    }
}
