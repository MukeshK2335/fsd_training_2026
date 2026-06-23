package com.atbs.service;

import com.atbs.dto.*;
import com.atbs.enums.BookingStatus;
import com.atbs.exception.*;
import com.atbs.mapper.BookingMapper;
import com.atbs.model.*;
import com.atbs.repository.BookingRepository;
import com.atbs.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PassengerService passengerService;
    private final ScheduleService scheduleService;
    private final SeatService seatService;
    private final SeatRepository seatRepository;
    private final FlightOwnerService flightOwnerService;
    private final BookingPassengerSeatService bookingPassengerSeatService;

    public Booking getById(int bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(()-> new FlightNotFoundException("Booing Id Not Found"));

    }


    public BookingPageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Booking> bookings=bookingRepository.findAll(pageable);
        Page<BookingRespDto> bookingRespDtos=bookings.map(bookingMapper::mapEntityToDto);

        return bookingMapper.mapPageToDto(bookingRespDtos);

    }

    public List<BookingRespDto> getByPassengerId(int id,String userName) {


        Passenger passenger=passengerService.getByUserName(userName);
        if(passenger.getId()!=id){
            throw new PassengerNotFoundException("Passenger Id id Different");
        }
        List<Booking> bookings=bookingRepository.getByPassengerId(id);
        return bookings.stream().map(bookingMapper::mapEntityToDto).toList();


    }

    public Booking addBooking(BookingReqDto dto, String userName) {
        Passenger passenger=passengerService.getByUserName(userName);
        Schedule schedule=scheduleService.getByIdWithEntity(dto.scheduleId());
        if (dto.passengerNames().size() != dto.seatIds().size()
                || dto.passengerNames().size() != dto.ages().size()) {
            throw new IllegalArgumentException("Passenger count mismtch");
        }
        double totalAmount = schedule.getFare()*dto.seatIds().size();
        Booking booking =bookingMapper.mapDtoToEntityBook(passenger,schedule,totalAmount);
        bookingRepository.save(booking);
        for (int i=0;i<dto.seatIds().size();i++){
            Seat seat=seatService.getById(dto.seatIds().get(i));
            if (!seat.getIsAvailable()) {
                throw new SeatNotAvailableExcption("Seat is not available");
            }
            seat.setIsAvailable(false);
            seatRepository.save(seat);
            BookingPassengerSeat bps = new BookingPassengerSeat();
            bps.setBooking(booking);
            bps.setPassenger(passenger);
            bps.setSeat(seat);
            bps.setPassengerName(dto.passengerNames().get(i));
            bps.setAge(dto.ages().get(i));
            bps.setSeatNumber(seat.getSeatNumber());
            bps.setSeatClass(seat.getSeatClass());
            bps.setBookingStatus(booking.getBookingStatus());
            bookingPassengerSeatService.save(bps);
        }
        return booking;
    }

    public List<BookingRespDto> getByScheduleId(int id, String userName) {

        FlightOwner flightOwner=flightOwnerService.getByName(userName);
        Schedule schedule=scheduleService.getByIdWithEntity(id);
        if(schedule.getFlight().getFlightOwner().getId()!=flightOwner.getId()){
            throw new FlightOwnerNotFoundException("Invalid Flight Owner");
        }
        List<Booking> bookings=bookingRepository.getByScheduleId(id);
        return bookings.stream().map(bookingMapper::mapEntityToDto).toList();

    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public BookingPageResp history(int page,int size,String userName) {
        Pageable pageable= PageRequest.of(page,size);
        Passenger passenger=passengerService.getByUserName(userName);
        Page<Booking> bookings=bookingRepository.getByPassengerIdHistory(passenger.getId(),BookingStatus.CANCELLED,pageable);
        Page<BookingRespDto> bookingRespDtos=bookings.map(bookingMapper::mapEntityToDto);
        return bookingMapper.mapPageToDto(bookingRespDtos);

    }

    public BookingPageResp getByFlightOwner(int page, int size, String username) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Booking> bookings=bookingRepository.findByScheduleFlightFlightOwnerUserUsername(username,pageable);
        Page<BookingRespDto> bookingRespDtos=bookings.map(bookingMapper::mapEntityToDto);

        return bookingMapper.mapPageToDto(bookingRespDtos);
    }

    public BookingRespDto getByIdResp(int id) {
        Booking booking=bookingRepository.findById(id).orElseThrow(()->new BookingNotFoundException("Not Found"));
        return bookingMapper.mapEntityToDto(booking);

    }

    public List<BookingRespDto> ticket(String userName) {
        Passenger passenger=passengerService.getByUserName(userName);

        List<Booking> bookings=bookingRepository.getByPassengerIdTicket(passenger.getId(),BookingStatus.CONFIRMED);

        return bookings.stream().map(bookingMapper::mapEntityToDto).toList();


    }

    public ChartDto getBookingsByFlightOwnerStat() {
        List<StringLabelCountDto> list =bookingRepository.getBookingsByFlightOwner();
        List<String> labels = list.stream().map(StringLabelCountDto::label).toList();
        List<Long> data = list.stream().map(StringLabelCountDto::count).toList();
        return new ChartDto(
                "Bookings By AirLines",
                labels,
                data
        );
    }

    public ChartDto getBookingStatusStat(String username) {
        List<BookingStatusDto> list =
                bookingRepository.getBookingStatusStat(username);

        List<String> labels = list.stream()
                .map(dto -> dto.label().name())
                .toList();

        List<Long> data = list.stream()
                .map(BookingStatusDto::count)
                .toList();

        return new ChartDto(
                "My Bookings By Status",
                labels,
                data
        );
    }
}
