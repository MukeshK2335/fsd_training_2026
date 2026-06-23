package com.atbs.mapper;

import com.atbs.dto.CancelationPageResp;
import com.atbs.dto.CancellationReqDto;
import com.atbs.dto.CancellationRespDto;
import com.atbs.model.Booking;
import com.atbs.model.Cancellation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CancellationMapper {

    public Cancellation mapDtoToEntity(CancellationReqDto dto){
        Cancellation cancellation=new Cancellation();
        cancellation.setReason(dto.reason());
        return cancellation;
    }

    public CancellationRespDto mapEntityToDto(Cancellation cancellation){
        Booking booking=cancellation.getBooking();
        return new CancellationRespDto(
                cancellation.getId(),
                cancellation.getCancelationDate(),
                cancellation.getReason(),
                cancellation.getRefundAmount(),
                cancellation.getRefundStatus().toString(),
                cancellation.getCancellationStatus().toString(),
                booking.getId(),
                booking.getBookingDate(),
                booking.getBookingStatus().name(),
                booking.getPassenger().getName(),
                booking.getSchedule().getFlight().getFlightNumber(),
                booking.getSchedule().getFlight().getFlightName(),
                booking.getSchedule().getRoute().getOrigin(),
                booking.getSchedule().getRoute().getDestination(),
                booking.getSchedule().getScheduledDate()

        );
    }
    public CancelationPageResp mapPageToDto(Page<CancellationRespDto> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<CancellationRespDto> list=pages.getContent();

        return new CancelationPageResp(
                totalElements,
                 totalPages,
                 list
        );
    }
}
