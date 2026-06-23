package com.atbs.service;

import com.atbs.dto.*;
import com.atbs.enums.RoleType;
import com.atbs.exception.FlightNotFoundException;
import com.atbs.exception.FlightOwnerNotFoundException;
import com.atbs.exception.UserAlreadyExistException;
import com.atbs.mapper.FlightOwnerMapper;
import com.atbs.mapper.UserMapper;
import com.atbs.model.*;
import com.atbs.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightOwnerService {
    private final FlightOwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final FlightOwnerMapper flightOwnerMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private  final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final ScheduleRepository scheduleRepository;
    private static final String NOT_FOUND = "Not Found";

    @Value("${airlines.password.temp}")
    private String airlineTempPassword;

    public FlightOwner getById(int id) {
        return ownerRepository.findById(id)
                .orElseThrow(()->new FlightNotFoundException(NOT_FOUND));
    }

    public void add(@Valid FlightOwnerReqDto dto) {
        Optional<?> existUser =userRepository.findByUsername(dto.username());
        if(existUser.isPresent()){
            throw new UserAlreadyExistException("Username already taken");
        }
        FlightOwner flightOwner=flightOwnerMapper.mapDtoToEntity(dto);
        User user=userMapper.mapDtoToEntityFlightOwner(dto);
        user.setPassword(passwordEncoder.encode(airlineTempPassword));
        user.setRole(RoleType.FLIGHT_OWNER);
        user=userService.save(user);
        flightOwner.setUser(user);
        ownerRepository.save(flightOwner);
    }

    public FlightOwnerPageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<FlightOwner> owners=ownerRepository.findAll(pageable);
        Page<FlightOwnerAllDto> allDtos=owners.map(flightOwnerMapper::mapEntity2DtoAll);
        return flightOwnerMapper.mapPageToDto(allDtos);
    }

    public FlightOwnerGetByIdDto getByIdWithOwnerCheck(int id, String userName) {

        User user = (User) userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Access denied"));

        FlightOwner flightOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new FlightOwnerNotFoundException("Access denied"));

        if (user.getRole() == RoleType.ADMIN) {
            return flightOwnerMapper.mapEntity2DtoGetById(flightOwner);
        }

        if (user.getRole() == RoleType.FLIGHT_OWNER) {
            if (!flightOwner.getUser().getUsername().equals(userName)) {
                throw new AccessDeniedException("Access denied"); // same message
            }
            return flightOwnerMapper.mapEntity2DtoGetById(flightOwner);
        }

        throw new AccessDeniedException("Access denied");
    }

    public FlightOwner getByName(String name) {
        return ownerRepository.findByUserUsername(name).orElseThrow(()->new FlightOwnerNotFoundException(NOT_FOUND));
    }

    public CombineStatDto getStat(String username) {
        List<Booking> bookingList=bookingRepository.findByScheduleFlightFlightOwnerUserUsername(username);
        List<Flight> flightList=flightRepository.findByFlightOwnerUserUsernameAndIsActiveTrue(username);
        List<Schedule> scheduleList=scheduleRepository.findByFlightFlightOwnerUserUsername(username);

        List<String> label=List.of("Schedules","Flights","Bookings");
        List<Long> count=List.of((long) scheduleList.size(),(long) flightList.size(),(long) bookingList.size());
        return new CombineStatDto(label,count);
    }

    public FlightOwner getDetails(String username) {
        return ownerRepository.findByUserUsername(username).orElseThrow(()->new FlightOwnerNotFoundException(NOT_FOUND));

    }

    public void resetPassword(PasswordsReqDto passwordsReqDto, String username) {
        User user=(User)userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(NOT_FOUND));
        ownerRepository.findByUserUsername(username).orElseThrow(()->new FlightOwnerNotFoundException(NOT_FOUND));
        user.setPassword(passwordEncoder.encode(passwordsReqDto.password()));
        userRepository.save(user);

    }
}
