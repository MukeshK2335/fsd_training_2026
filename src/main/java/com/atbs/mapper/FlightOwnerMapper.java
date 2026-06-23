package com.atbs.mapper;

import com.atbs.dto.*;
import com.atbs.model.FlightOwner;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightOwnerMapper {

    public FlightOwnerAllDto mapEntity2DtoAll(FlightOwner flightOwner){
        return new FlightOwnerAllDto(
                flightOwner.getId(),
                flightOwner.getUser().getUsername(),
                flightOwner.getName(),
                flightOwner.getCompanyName(),
                flightOwner.getContactNumber(),
                flightOwner.getAddress(),
                flightOwner.getContactEmail()
        );
    }

    public FlightOwnerGetByIdDto mapEntity2DtoGetById(FlightOwner flightOwner){
        return new FlightOwnerGetByIdDto(
                flightOwner.getId(),
                flightOwner.getUser().getUsername(),
                flightOwner.getName(),
                flightOwner.getCompanyName(),
                flightOwner.getContactNumber(),
                flightOwner.getAddress(),
                flightOwner.getContactEmail()
        );
    }
    public FlightOwner mapDtoToEntity(@Valid FlightOwnerReqDto dto){
        FlightOwner flightOwner=new FlightOwner();
        flightOwner.setCompanyName(dto.companyName());
        flightOwner.setContactNumber(dto.contactNumber());
        flightOwner.setAddress(dto.address());
        flightOwner.setContactEmail(dto.contactEmail());
        return flightOwner;
    }

    public FlightOwnerPageResp mapPageToDto(Page<FlightOwnerAllDto> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<FlightOwnerAllDto> list=pages.getContent();
        return new FlightOwnerPageResp(
                totalElements,
                totalPages,
                list
        );
    }
}
