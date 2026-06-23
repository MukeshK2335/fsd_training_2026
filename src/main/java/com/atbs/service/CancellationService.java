package com.atbs.service;

import com.atbs.dto.CancelationPageResp;
import com.atbs.dto.CancellationReqDto;
import com.atbs.dto.CancellationRespDto;
import com.atbs.enums.BookingStatus;
import com.atbs.enums.CancellationStatus;
import com.atbs.enums.RefundStatus;
import com.atbs.exception.CancellationNotFoundException;
import com.atbs.exception.UnAuthorizedAccessException;
import com.atbs.mapper.CancellationMapper;
import com.atbs.model.*;
import com.atbs.repository.BookingPassengerSeatRepository;
import com.atbs.repository.CancellationRepository;
import com.atbs.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CancellationService {
    private final BookingService bookingService;
    private final CancellationMapper cancellationMapper;
    private final CancellationRepository cancellationRepository;
    private final PassengerService passengerService;
    private final FlightOwnerService flightOwnerService;
    private  final BookingPassengerSeatRepository bookingPassengerSeatRepository;
    private final SeatRepository seatRepository;
    private static final String NOT_FOUND = "Not Found";
    public void add(CancellationReqDto dto, int bookingId, String userName) {
        Passenger passenger=passengerService.getByUserName(userName);
        Booking booking=bookingService.getById(bookingId);
        if(booking.getPassenger().getId()!= passenger.getId()){
            throw new UnAuthorizedAccessException("Access Denied");
        }
        Cancellation cancellation=cancellationMapper.mapDtoToEntity(dto);
        cancellation.setRefundAmount(booking.getTotalAmount()/2);
        cancellation.setBooking(booking);
        cancellationRepository.save(cancellation);

    }

    public CancelationPageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Cancellation> pages=cancellationRepository.findAll(pageable);
        Page<CancellationRespDto> pageDto=pages.map(cancellationMapper::mapEntityToDto);
        return cancellationMapper.mapPageToDto(pageDto);


    }

    public CancelationPageResp getAllPass(int page, int size, String userName) {
        Pageable pageable=PageRequest.of(page,size);
        passengerService.getByUserName(userName);
        Page<Cancellation> cancellations=cancellationRepository.getByPassenger(pageable,userName);
        Page<CancellationRespDto> dto=cancellations.map(cancellationMapper::mapEntityToDto);
        return cancellationMapper.mapPageToDto(dto);

    }

    public CancelationPageResp getAllFlight(int page, int size, String userName) {
        Pageable pageable=PageRequest.of(page,size);
        flightOwnerService.getByName(userName);
        Page<Cancellation> cancellations=cancellationRepository.getByFlightOwner(userName, CancellationStatus.REQUESTED,pageable);
        Page<CancellationRespDto> dto=cancellations.map(cancellationMapper::mapEntityToDto);
        return cancellationMapper.mapPageToDto(dto);
    }

    public CancellationRespDto getById(int cancellationId) {
        Cancellation cancellation=cancellationRepository.findById(cancellationId).orElseThrow(()->new CancellationNotFoundException(NOT_FOUND));
        return cancellationMapper.mapEntityToDto(cancellation);
    }


    public void approve(int id) {
        Cancellation cancellation=cancellationRepository.findById(id).orElseThrow(()->new CancellationNotFoundException(NOT_FOUND));
        cancellation.setCancellationStatus(CancellationStatus.APPROVED);
        cancellation.setRefundStatus(RefundStatus.PROCESSED);
        Booking booking=cancellation.getBooking();
        booking.setBookingStatus(BookingStatus.CANCELLED);
        List<BookingPassengerSeat> bookingPassengerSeat=bookingPassengerSeatRepository.findByBookingId(booking.getId());
        for (BookingPassengerSeat bps : bookingPassengerSeat) {
            bps.setBookingStatus(BookingStatus.CANCELLED);
            bookingPassengerSeatRepository.save(bps);

            Seat seat = bps.getSeat();
            seat.setIsAvailable(true);
            seatRepository.save(seat);
        }
        cancellationRepository.save(cancellation);
    }

    public void reject(int id) {
        Cancellation cancellation=cancellationRepository.findById(id).orElseThrow(()->new CancellationNotFoundException(NOT_FOUND));
        cancellation.setCancellationStatus(CancellationStatus.REJECTED);
        cancellation.setRefundStatus(RefundStatus.FAILED);
        cancellationRepository.save(cancellation);
    }

    public CancelationPageResp getReqCancellation(int page, int size, String username) {
        Pageable pageable=PageRequest.of(page,size);
        flightOwnerService.getByName(username);
        Page<Cancellation> cancellations=cancellationRepository.getByFlightOwnerReq(username, CancellationStatus.REQUESTED,pageable);
        Page<CancellationRespDto> dto=cancellations.map(cancellationMapper::mapEntityToDto);
        return cancellationMapper.mapPageToDto(dto);

    }
}
