package com.atbs.mapper;

import com.atbs.dto.BookingPassengerSeatRepDto;
import com.atbs.model.BookingPassengerSeat;
import org.springframework.stereotype.Component;

@Component
public class BookingPassengerSeatMapper {
    public BookingPassengerSeat mapDto2Entity(BookingPassengerSeatRepDto dto){
        BookingPassengerSeat bookingPassengerSeat=new BookingPassengerSeat();
        bookingPassengerSeat.setAge(dto.age());
        return bookingPassengerSeat;
    }



}
