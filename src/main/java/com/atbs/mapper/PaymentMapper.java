package com.atbs.mapper;

import com.atbs.dto.PaymentPageResp;
import com.atbs.dto.PaymentRespDto;
import com.atbs.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {

    public PaymentRespDto mapEntityToDto(Payment payment){
        return new PaymentRespDto(
                payment.getId(),
                payment.getTransactionId(),
                payment.getAmount(),
                payment.getPaymentMethod().toString(),
                payment.getPaymentStatus().toString(),
                payment.getPaymentTime(),
                payment.getBooking().getId(),
                payment.getBooking().getBookingDate(),
                payment.getBooking().getBookingStatus().toString(),
                payment.getBooking().getPassenger().getName(),
                payment.getBooking().getSchedule().getFlight().getFlightName(),
                payment.getBooking().getSchedule().getFlight().getFlightNumber(),
                payment.getBooking().getSchedule().getRoute().getOrigin(),
                payment.getBooking().getSchedule().getRoute().getDestination(),
                payment.getBooking().getSchedule().getDepartureTime(),
                payment.getBooking().getSchedule().getArrivalTime()
        );
    }

    public PaymentPageResp mapPageToDto(Page<PaymentRespDto> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<PaymentRespDto> list=pages.getContent();
        return new PaymentPageResp(
                totalElements,
                totalPages,
                list
        );

    }
}
