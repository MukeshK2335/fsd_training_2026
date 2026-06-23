package com.atbs.mapper;

import com.atbs.dto.PassengerReqDto;
import com.atbs.enums.Gender;
import com.atbs.model.Passenger;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    public Passenger mapDtoToEntity(@Valid PassengerReqDto dro){
        Passenger passenger = new Passenger();
        passenger.setName(dro.name());
        passenger.setGender(Gender.valueOf(dro.gender()));
        passenger.setAddress(dro.address());
        passenger.setEmail(dro.email());
        passenger.setContactNumber(dro.contact_number());
        return passenger;
    }
}
