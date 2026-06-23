package com.atbs.mapper;

import com.atbs.dto.BookingPageResp;
import com.atbs.dto.BookingReqDto;
import com.atbs.dto.BookingRespDto;
import com.atbs.model.Booking;
import com.atbs.model.BookingPassengerSeat;
import com.atbs.model.Passenger;
import com.atbs.model.Schedule;
import com.atbs.repository.BookingPassengerSeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BookingMapper {

    private final BookingPassengerSeatRepository bookingPassengerSeatRepository;

    public Booking mapDtoToEntity(){
        return new Booking();

    }

    public BookingRespDto mapEntityToDto(Booking booking){

        List<BookingPassengerSeat> allocations = bookingPassengerSeatRepository.findByBookingId(booking.getId());

        List<String> passengerNames = allocations.stream()
                        .map(BookingPassengerSeat::getPassengerName)
                        .toList();

        List<String> seatNumbers = allocations.stream()
                        .map(BookingPassengerSeat::getSeatNumber)
                        .toList();



        return new BookingRespDto(
                booking.getId(),
                booking.getPassenger().getName(),
                booking.getSchedule().getFlight().getFlightNumber(),
                booking.getSchedule().getFlight().getFlightName(),
                booking.getSchedule().getRoute().getOrigin(),
                booking.getSchedule().getRoute().getDestination(),
                booking.getSchedule().getDepartureTime(),
                booking.getSchedule().getArrivalTime(),
                passengerNames,
                seatNumbers,
                booking.getTotalAmount(),
                booking.getBookingStatus().toString(),
                booking.getSchedule().getFlight().getCheckInBaggage(),
                booking.getSchedule().getFlight().getCabinBaggage()


        );
    }

    public Booking mapDtoToEntityBook( Passenger passenger, Schedule schedule, double totalAmount){
        Booking booking=new Booking();

        booking.setTotalAmount(totalAmount);
        booking.setPassenger(passenger);
        booking.setSchedule(schedule);
        return booking;

    }

    public BookingPageResp mapPageToDto(Page<BookingRespDto> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<BookingRespDto> list=pages.getContent();

        return new BookingPageResp(
                totalElements,
                totalPages,
                list
        );
    }
}
