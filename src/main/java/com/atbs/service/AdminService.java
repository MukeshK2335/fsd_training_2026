package com.atbs.service;

import com.atbs.dto.AdminReqDto;
import com.atbs.dto.CombineStatDto;
import com.atbs.enums.RoleType;
import com.atbs.exception.UserAlreadyExistException;
import com.atbs.mapper.AdminMapper;
import com.atbs.mapper.UserMapper;
import com.atbs.model.*;
import com.atbs.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private  final FlightRepository flightRepository;
    private  final FlightOwnerRepository flightOwnerRepository;

    @Value("${admin.password.temp}")
    private String adminTempPassword;
    public void add(@Valid AdminReqDto dto) {
        Optional<?> existUser =userRepository.findByUsername(dto.username());
        if(existUser.isPresent()){
            throw new UserAlreadyExistException("Username already taken");
        }
        Admin admin=adminMapper.mapDtoToEntity(dto);
        User user=userMapper.mapDtoToEntityAdmin(dto);
        user.setPassword(passwordEncoder.encode(adminTempPassword));
        user.setRole(RoleType.ADMIN);
        user=userService.save(user);
        admin.setUser(user);
        adminRepository.save(admin);

    }

    public CombineStatDto getCombineStat() {
        List<Booking> bookingList=bookingRepository.findAll();

        List<Flight> flightList=flightRepository.findAll();

        List<FlightOwner> flightOwnerList=flightOwnerRepository.findAll();

        List<String> label=List.of("Airlines","Flights","Bookings");
        List<Long> count=List.of((long) flightOwnerList.size(),(long) flightList.size(),(long) bookingList.size());

        return new CombineStatDto(label,count);
    }
}
