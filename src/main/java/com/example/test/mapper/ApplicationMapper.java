package com.example.test.mapper;

import com.example.test.dto.ApplicationRespDto;
import com.example.test.model.Application;
import com.example.test.model.Job;
import com.example.test.model.JobSeeker;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public Application mapEntity(JobSeeker jobSeeker, Job job){
        Application application=new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        return application;
    }

    public ApplicationRespDto mapEntityToDto(Application application){
        return new ApplicationRespDto(
                application.getId(),
                application.getCreatedAt(),
                application.getJob().getTitle(),
                application.getJob().getEmployer().getCompanyName()
        );
    }
}
