package com.atbs.service;

import com.atbs.exception.FlightNotFoundException;
import com.atbs.model.Seat;
import com.atbs.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    public Seat getById(int seatId) {
        return seatRepository.findById(seatId).orElseThrow(()->new FlightNotFoundException("Seat Not Found"));
    }

    public List<Seat> getAvailableSeats(Integer scheduleId) {
        return seatRepository.findByScheduleIdAndIsAvailableTrue(scheduleId);
    }

}
