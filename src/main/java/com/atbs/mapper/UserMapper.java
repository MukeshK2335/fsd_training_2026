package com.atbs.mapper;

import com.atbs.dto.AdminReqDto;
import com.atbs.dto.FlightOwnerReqDto;
import com.atbs.dto.PassengerReqDto;
import com.atbs.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapDtoToEntityPass(@Valid PassengerReqDto dro){
        User user=new User();
        user.setUsername(dro.username());
        user.setPassword(dro.password());
        return user;
    }

    public User mapDtoToEntityFlightOwner(@Valid FlightOwnerReqDto dto){
        User user=new User();
        user.setUsername(dto.username());
        return user;
    }

    public User mapDtoToEntityAdmin(@Valid AdminReqDto dto){
        User user=new User();
        user.setUsername(dto.username());
        return user;
    }


}
