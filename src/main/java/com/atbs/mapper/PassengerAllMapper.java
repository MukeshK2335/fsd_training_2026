package com.atbs.mapper;

import com.atbs.dto.PassenegrGetDto;
import com.atbs.dto.PassengerAllDto;
import com.atbs.dto.PassengerPageResp;
import com.atbs.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerAllMapper {


    public PassengerAllDto entity2Dto(Passenger passenger){
        return new PassengerAllDto(
                passenger.getId(),
                passenger.getUser().getUsername(),
                passenger.getName(),
                passenger.getGender().toString(),
                passenger.getContactNumber(),
                passenger.getAddress(),
                passenger.getEmail()

        );
    }
    public PassenegrGetDto entity2DtoById(Passenger passenger){
        return new PassenegrGetDto(
                passenger.getId(),
                passenger.getUser().getUsername(),
                passenger.getName(),
                passenger.getGender().toString(),
                passenger.getContactNumber(),
                passenger.getAddress(),
                passenger.getEmail()

        );
    }

    public PassengerPageResp mapPageToDto(Page<PassengerAllDto> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<PassengerAllDto> list=pages.getContent();
        return new PassengerPageResp(
                totalElements,
                totalPages,
                list
        );
    }
}
