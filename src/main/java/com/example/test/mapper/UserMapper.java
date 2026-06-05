package com.example.test.mapper;

import com.example.test.dto.RegisterReqDto;
import com.example.test.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapDtoToEntity(RegisterReqDto dto){
        User user=new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        return user;
    }
}
