package com.example.test.mapper;

import com.example.test.dto.RegisterReqDto;
import com.example.test.model.JobSeeker;
import org.springframework.stereotype.Component;

@Component
public class JobSeekerMapper {

    public JobSeeker mapDtoToEntity(RegisterReqDto dto){
        JobSeeker jobSeeker=new JobSeeker();
        jobSeeker.setName(dto.name());
        jobSeeker.setResumeSummary(dto.resumeSummary());
        return jobSeeker;
    }
}
