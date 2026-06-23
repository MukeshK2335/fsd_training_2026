package com.atbs.service;

import com.atbs.dto.FlightReqDto;
import com.atbs.dto.FlightRespDto;
import com.atbs.dto.FlightResponseDto;
import com.atbs.enums.RoleType;
import com.atbs.exception.FlightNotFoundException;
import com.atbs.exception.FlightOwnerNotFoundException;
import com.atbs.mapper.FlightMapper;
import com.atbs.model.Flight;
import com.atbs.model.FlightOwner;
import com.atbs.model.User;
import com.atbs.repository.FlightRepository;
import com.atbs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final FlightOwnerService ownerService;
    private  final UserRepository userRepository;
    private final FlightOwnerService flightOwnerService;

    public List<Flight> getAll() {
         return flightRepository.findAll();
    }

    public void addFlight(FlightReqDto flight) {
        Flight flight1=FlightMapper.mapDto2Flight(flight);
        flightRepository.save(flight1);
    }

    public Flight getByFlightId(int id) {
        return flightRepository.findById(id).orElseThrow(()->new FlightNotFoundException("Invalid Flight ID"));

    }

    public void deleteById(int id) {
        getByFlightId(id);

        flightRepository.deleteById(id);
    }

    public void update(int id, FlightReqDto dto) {
        Flight existingFlight=getByFlightId(id);
        existingFlight.setFlightName(dto.flightName());
        existingFlight.setFlightNumber(dto.flightNumber());
        existingFlight.setTotalSeats(dto.totalSeats());
        existingFlight.setCheckInBaggage(dto.checkInBaggage());
        existingFlight.setCabinBaggage(dto.cabinBaggage());
        flightRepository.save(existingFlight);

    }

    public FlightResponseDto getAllWithPagenation(int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Flight> flights=flightRepository.findByIsActiveTrue(pageable);
        return flightMapper.mapEntity2Dto(flights);
    }

    public List<Flight> getByFlightName(String flightName) {
        return flightRepository.findByFlightName(flightName);
    }

    public FlightResponseDto getByFlightNamev2(int page, int size, String flightName) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Flight> flights=flightRepository.findByFlightName(flightName,pageable);
        return flightMapper.mapEntity2DtoByName(flights);


    }

    public void addFlightWithOwner(FlightReqDto dto, int id) {
        FlightOwner flightOwner=ownerService.getById(id);

        Flight flight1=FlightMapper.mapDto2Flight(dto);
        flight1.setFlightOwner(flightOwner);
        flightRepository.save(flight1);
    }

    public FlightRespDto getById(int id, String userName) {

        User user = (User) userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Access denied"));

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Access denied"));

        if (user.getRole() == RoleType.ADMIN) {
            return flightMapper.mapEntitytoDtoByid(flight);
        }

        if (user.getRole() == RoleType.FLIGHT_OWNER) {
            if (!flight.getFlightOwner().getUser().getUsername().equals(userName)) {
                throw new AccessDeniedException("Access denied");
            }
            return flightMapper.mapEntitytoDtoByid(flight);
        }

        throw new AccessDeniedException("Access denied");
    }

    public Flight add(FlightReqDto dto, String name) {
        FlightOwner flightOwner=flightOwnerService.getByName(name);
        Flight flight=FlightMapper.mapDto2Flight(dto);
        flight.setFlightOwner(flightOwner);
         return  flightRepository.save(flight);
    }

    public FlightResponseDto getFlightByFlightOwner(int page,int size,String username) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Flight> flights=flightRepository.findByFlightOwnerUserUsernameAndIsActiveTrue( username,pageable);
        return flightMapper.mapEntity2DtoByName(flights);
    }

    public void softDelete(int id) {
        Flight flight=flightRepository.findById(id).orElseThrow(()->new FlightNotFoundException("Flight Not Found"));
        flight.setActive(false);
        flightRepository.save(flight);
    }
}
