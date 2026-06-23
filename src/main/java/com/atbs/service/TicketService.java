package com.atbs.service;

import com.atbs.dto.TicketRespDto;
import com.atbs.exception.UnAuthorizedAccessException;
import com.atbs.mapper.TicketMapper;
import com.atbs.model.Booking;
import com.atbs.model.Passenger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {
    private final PassengerService passengerService;
    private final BookingService bookingService;
    private final TicketMapper ticketMapper;
    public TicketRespDto generate(int bookingId, String userName) {

        Passenger passenger=passengerService.getByUserName(userName);
        Booking booking=bookingService.getById(bookingId);
        if(booking.getPassenger().getId()!=passenger.getId()){
            throw new UnAuthorizedAccessException("Access Denied");
        }
        return ticketMapper.mapEntityToDto(booking);
    }
}
