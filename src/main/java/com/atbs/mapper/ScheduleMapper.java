package com.atbs.mapper;

import com.atbs.dto.SchedulePageResp;
import com.atbs.dto.ScheduleReqDto;
import com.atbs.dto.ScheduleRespDto;
import com.atbs.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleMapper {

    public ScheduleRespDto mapEntityToDtoAll(Schedule schedule){
        return new ScheduleRespDto(
                schedule.getId(),
                schedule.getRoute().getOrigin(),
                schedule.getRoute().getDestination(),
                schedule.getArrivalTime(),
                schedule.getDepartureTime(),
                schedule.getFlight().getFlightName(),
                schedule.getFlight().getFlightNumber(),
                schedule.getFlight().getTotalSeats(),
                schedule.getFlight().getCheckInBaggage(),
                schedule.getFlight().getCabinBaggage(),
                schedule.getFlight().getFlightOwner().getCompanyName(),
                schedule.getFare(),
                schedule.getScheduleStatus().toString()


        );
    }

    public ScheduleRespDto mapEntityToDtoById(Schedule schedule) {
        return new ScheduleRespDto(
                schedule.getId(),
                schedule.getRoute().getOrigin(),
                schedule.getRoute().getDestination(),
                schedule.getArrivalTime(),
                schedule.getDepartureTime(),
                schedule.getFlight().getFlightName(),
                schedule.getFlight().getFlightNumber(),
                schedule.getFlight().getTotalSeats(),
                schedule.getFlight().getCheckInBaggage(),
                schedule.getFlight().getCabinBaggage(),
                schedule.getFlight().getFlightOwner().getCompanyName(),
                schedule.getFare(),
                schedule.getScheduleStatus().toString()


        );
    }
    public Schedule mapDtoToEntity(ScheduleReqDto dto){
        Schedule schedule=new Schedule();
        schedule.setFare(dto.fare());
        schedule.setArrivalTime(dto.arrivalTime());
        schedule.setDepartureTime(dto.departureTime());
        schedule.setScheduledDate(dto.scheduleDate());
        return schedule;
    }

    public SchedulePageResp mapPageToDto(Page<ScheduleRespDto> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<ScheduleRespDto> list=pages.getContent();
        return new SchedulePageResp(
                totalElements,
                totalPages,
                list
        );
    }
}
