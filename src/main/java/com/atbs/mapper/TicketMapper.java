package com.atbs.mapper;

import com.atbs.dto.TicketRespDto;
import com.atbs.model.Booking;
import com.atbs.model.BookingPassengerSeat;
import com.atbs.repository.BookingPassengerSeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TicketMapper {
    private final BookingPassengerSeatRepository bookingPassengerSeatRepository;
    public TicketRespDto mapEntityToDto(Booking booking){


        List<BookingPassengerSeat> allocation=bookingPassengerSeatRepository.findByBookingId(booking.getId());

        List<String> passengerName=allocation.stream().map(BookingPassengerSeat::getPassengerName).toList();

        List<String> seatNumber=allocation.stream().map(BookingPassengerSeat::getSeatNumber).toList();

        return new TicketRespDto(
                booking.getId(),
                booking.getSchedule().getFlight().getFlightNumber(),
                booking.getSchedule().getFlight().getFlightName(),
                booking.getPassenger().getName(),
                booking.getSchedule().getRoute().getOrigin(),
                booking.getSchedule().getRoute().getDestination(),
                booking.getSchedule().getDepartureTime(),
                booking.getSchedule().getArrivalTime(),
                passengerName,
                seatNumber,
                booking.getTotalAmount(),
                booking.getSchedule().getFlight().getCheckInBaggage(),
                booking.getSchedule().getFlight().getCabinBaggage(),
                booking.getBookingStatus().toString()


        );
    }
}
