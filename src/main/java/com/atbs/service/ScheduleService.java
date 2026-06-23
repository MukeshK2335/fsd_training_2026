package com.atbs.service;

import com.atbs.dto.*;
import com.atbs.enums.RouteStatus;
import com.atbs.enums.ScheduleStatus;
import com.atbs.enums.SeatClass;
import com.atbs.exception.FlightNotFoundException;
import com.atbs.exception.RouteNotFoundException;
import com.atbs.exception.ScheduleNotFound;
import com.atbs.mapper.ScheduleMapper;
import com.atbs.model.*;
import com.atbs.repository.ScheduleRepository;
import com.atbs.repository.SeatRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final FlightService flightService;
    private final RouteService routeService;
    private final FlightOwnerService flightOwnerService;
    private final SeatRepository seatRepository;
    private static final String NOT_FOUND = "Not Found";


    public SchedulePageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Schedule> schedules=scheduleRepository.findAll(pageable);
        Page<ScheduleRespDto> dtos=schedules.map(scheduleMapper::mapEntityToDtoAll);
        return scheduleMapper.mapPageToDto(dtos);
    }

    public ScheduleRespDto getById(int id) {
        Schedule schedule=scheduleRepository.findById(id).orElseThrow(()->new ScheduleNotFound(NOT_FOUND));
        return scheduleMapper.mapEntityToDtoById(schedule);
    }

    public Schedule getByIdWithEntity(@NotBlank @NotBlank int i) {
        return scheduleRepository.findById(i).orElseThrow(()->new ScheduleNotFound(NOT_FOUND));
    }

    public List<ScheduleRespDto> getBySearch(String origin, String destination, LocalDate date) {
        if(origin.equals(destination)){
            throw new ScheduleNotFound("Both Origin and Destination cannot be same");
        }
        List<Schedule> schedules=scheduleRepository.getBySearch(origin,destination,date);

        return  schedules.stream().map(scheduleMapper::mapEntityToDtoAll).toList();

    }

    public void add(@Valid ScheduleReqDto dto, String userName) {
        FlightOwner flightOwner=flightOwnerService.getByName(userName);
        Flight flight=flightService.getByFlightId(dto.flightId());
        if(flight.getFlightOwner().getId()!=flightOwner.getId()){
            throw new FlightNotFoundException("Flight Not Belogs to This Flight Company");
        }
        Route route=routeService.getById(dto.routeId());
        if(route.getRouteStatus().toString().equals(String.valueOf(RouteStatus.INACTIVE))){
            throw new RouteNotFoundException("This Route is InActive");
        }

        Schedule schedule=scheduleMapper.mapDtoToEntity(dto);
        schedule.setFlight(flight);
        schedule.setRoute(route);
        schedule.setScheduleStatus(ScheduleStatus.ACTIVE);
        scheduleRepository.save(schedule);
        generateSeats(schedule,flight.getTotalSeats());
    }
    private void generateSeats(Schedule schedule, int totalSeat) {

        char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F'};

        int generatedSeats = 0;
        int rowNumber = 1;
        while (generatedSeats < totalSeat) {
            for (char letter : seatLetters) {
                if (generatedSeats >= totalSeat) {
                    break;
                }
                Seat seat = new Seat();
                seat.setSeatNumber(rowNumber + String.valueOf(letter));
                seat.setSeatClass(SeatClass.ECONOMY);
                seat.setIsAvailable(true);
                seat.setSchedule(schedule);
                seatRepository.save(seat);
                generatedSeats++;
            }
            rowNumber++;
        }
    }

    public SchedulePageResp getAllFlightOwner( int page,int size,String userName) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Schedule> schedules=scheduleRepository.getAllFlightOwner(userName,pageable);
        Page<ScheduleRespDto> dto=schedules.map(scheduleMapper::mapEntityToDtoAll);
        return scheduleMapper.mapPageToDto(dto);

    }

    public void delaySchedule(int scheduleId) {
        Schedule schedule=scheduleRepository.findById(scheduleId).orElseThrow(()->new ScheduleNotFound(NOT_FOUND));
        schedule.setScheduleStatus(ScheduleStatus.DELAYED);
        scheduleRepository.save(schedule);
    }

    public void cancelSchedule(int scheduleId) {
        Schedule schedule=scheduleRepository.findById(scheduleId).orElseThrow(()->new ScheduleNotFound(NOT_FOUND));
        schedule.setScheduleStatus(ScheduleStatus.CANCELLED);
        scheduleRepository.save(schedule);
    }

    public void activeSchedule(int id) {
        Schedule schedule=scheduleRepository.findById(id).orElseThrow(()->new ScheduleNotFound(NOT_FOUND));
        schedule.setScheduleStatus(ScheduleStatus.ACTIVE);
        scheduleRepository.save(schedule);
    }

    public ChartDto getScheduleStatusStat(String username) {
        List<LabelCountDto> list =
                scheduleRepository.getScheduleStatusStat(username);

        List<String> labels = list.stream()
                .map(dto -> dto.label().toString())
                .toList();

        List<Long> data = list.stream()
                .map(LabelCountDto::count)
                .toList();

        return new ChartDto(
                "Schedule Status",
                labels,
                data
        );
    }
}
