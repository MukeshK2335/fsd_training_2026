package com.example.test.mapper;

import com.example.test.dto.RegisterReqDto;
import com.example.test.model.Employer;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class EmployerMapper {

    public Employer mapDtoToEntity(RegisterReqDto dto){
        Employer employer=new Employer();
        employer.setCompanyName(dto.companyName());
        return employer;
    }

}
