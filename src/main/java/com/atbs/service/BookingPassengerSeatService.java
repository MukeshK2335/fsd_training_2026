package com.atbs.service;

import com.atbs.dto.BookingPassengerSeatRepDto;
import com.atbs.mapper.BookingPassengerSeatMapper;
import com.atbs.model.Booking;
import com.atbs.model.BookingPassengerSeat;
import com.atbs.model.Passenger;
import com.atbs.model.Seat;
import com.atbs.repository.BookingPassengerSeatRepository;
import com.atbs.repository.BookingRepository;
import com.atbs.repository.PassengerRepository;
import com.atbs.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingPassengerSeatService {
    private final BookingPassengerSeatRepository bookingPassengerSeatRepository;
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;
    private final BookingPassengerSeatMapper bookingPassengerSeatMapper;



    public void add(int bookingId, int passengerId, int seatId, BookingPassengerSeatRepDto dto) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow();
        Seat seat = seatRepository.findById(seatId).orElseThrow();

        BookingPassengerSeat bps = bookingPassengerSeatMapper.mapDto2Entity(dto);
        bps.setBooking(booking);
        bps.setPassenger(passenger);
        bps.setSeat(seat);

        bookingPassengerSeatRepository.save(bps);


    }

    public void save(BookingPassengerSeat bps) {
        bookingPassengerSeatRepository.save(bps);
    }
}
