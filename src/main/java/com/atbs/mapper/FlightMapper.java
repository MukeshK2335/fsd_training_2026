package com.atbs.mapper;

import com.atbs.dto.FlightReqDto;
import com.atbs.dto.FlightRespDto;
import com.atbs.dto.FlightResponseDto;
import com.atbs.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightMapper {

    public static Flight mapDto2Flight(FlightReqDto dto){
        Flight flight=new Flight();
        flight.setFlightName(dto.flightName());
        flight.setFlightNumber(dto.flightNumber());
        flight.setTotalSeats(dto.totalSeats());
        flight.setCabinBaggage(dto.cabinBaggage());
        flight.setCheckInBaggage(dto.checkInBaggage());
        return flight;
    }

    public FlightResponseDto mapEntity2Dto(Page<Flight> dto){
        long totalElements=dto.getTotalElements();
        int totalPages=dto.getTotalPages();
        List<Flight> flightList=dto.getContent();
        return new FlightResponseDto(
                totalElements,
                totalPages,
                flightList
        );
    }

    public FlightResponseDto mapEntity2DtoByName(Page<Flight> flights){
        long totalContent=flights.getTotalElements();
        int totalPages=flights.getTotalPages();
        List<Flight> flightList=flights.getContent();
        return new FlightResponseDto(
                totalContent,
                totalPages,
                flightList
        );

    }

    public FlightRespDto mapEntitytoDtoByid(Flight flight){
        return new FlightRespDto(
                flight.getId(),
                flight.getFlightName(),
                flight.getFlightNumber(),
                flight.getTotalSeats(),
                flight.getCheckInBaggage(),
                flight.getCabinBaggage()
        );
    }

}
