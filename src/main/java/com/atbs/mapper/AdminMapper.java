package com.atbs.mapper;

import com.atbs.dto.AdminReqDto;
import com.atbs.model.Admin;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin mapDtoToEntity(@Valid AdminReqDto dto){
        Admin admin=new Admin();
        admin.setName(dto.name());
        admin.setEmail(dto.email());
        admin.setContactNumber(dto.contact_number());
        return admin;
    }
}
