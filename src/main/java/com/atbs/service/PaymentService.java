package com.atbs.service;

import com.atbs.dto.PaymentPageResp;
import com.atbs.dto.PaymentReqDto;
import com.atbs.dto.PaymentRespDto;
import com.atbs.enums.BookingStatus;
import com.atbs.enums.PaymentMethod;
import com.atbs.enums.PaymentStatus;
import com.atbs.exception.BookingNotFoundException;
import com.atbs.exception.PassengerNotFoundException;
import com.atbs.exception.PaymentNotFoundException;
import com.atbs.exception.UnAuthorizedAccessException;
import com.atbs.mapper.PaymentMapper;
import com.atbs.model.Booking;
import com.atbs.model.BookingPassengerSeat;
import com.atbs.model.Passenger;
import com.atbs.model.Payment;
import com.atbs.repository.BookingPassengerSeatRepository;
import com.atbs.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PassengerService passengerService;
    private final BookingService bookingService;
    private final PaymentRepository paymentRepository;
    private final BookingPassengerSeatRepository bookingPassengerSeatRepository;
    private final PaymentMapper paymentMapper;

    public void add(int bookingId, String userName, PaymentReqDto dto) {

        Passenger passenger=passengerService.getByUserName(userName);
        Booking booking=bookingService.getById(bookingId);
        List<BookingPassengerSeat> bookingPassengerSeatList=bookingPassengerSeatRepository.findByBookingId(bookingId);

        if(!(booking.getPassenger().getUser().getUsername().equals(passenger.getUser().getUsername()))){
            throw new PassengerNotFoundException("Invalid Passenger");
        }

        Payment payment=new Payment();
        payment.setPaymentMethod(PaymentMethod.valueOf(dto.paymentMethod()));
        payment.setAmount(booking.getTotalAmount());
        payment.setTransactionId("TX"+booking.getBookedAt());
        payment.setBooking(booking);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);


        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingService.save(booking);

        for(BookingPassengerSeat book:bookingPassengerSeatList){
            book.setBookingStatus(BookingStatus.CONFIRMED);
        }
        bookingPassengerSeatRepository.saveAll(bookingPassengerSeatList);




    }

    public PaymentRespDto getPaymentByBookingId(int bookingId, String userName) {
        Passenger passenger=passengerService.getByUserName(userName);
        Booking booking=bookingService.getById(bookingId);
        if(booking.getPassenger().getId()!=passenger.getId()){
            throw new UnAuthorizedAccessException("Access Denied");
        }
        Payment payment=paymentRepository.getPaymentByBookingId(bookingId).orElseThrow(()->new BookingNotFoundException("No Booking Found"));

        return paymentMapper.mapEntityToDto(payment);

    }

    public PaymentRespDto getPaymentByPaymentId(int paymentId) {
        Payment payment=paymentRepository.getByPaymentByPaymentId(paymentId).orElseThrow(()->new PaymentNotFoundException("Payment Not Found"));
        return paymentMapper.mapEntityToDto(payment);

    }

    public PaymentPageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Payment> payments=paymentRepository.findAll(pageable);
        Page<PaymentRespDto> dto=payments.map(paymentMapper::mapEntityToDto);
        return paymentMapper.mapPageToDto(dto);

    }

    public PaymentPageResp getAllFlightOwner(int page, int size, String username) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Payment> payments=paymentRepository.findByBookingScheduleFlightFlightOwnerUserUsername(username,pageable);
        Page<PaymentRespDto> dto=payments.map(paymentMapper::mapEntityToDto);
        return paymentMapper.mapPageToDto(dto);
    }
}
