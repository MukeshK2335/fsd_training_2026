package com.atbs.service;


import com.atbs.dto.FlightReqDto;
import com.atbs.exception.FlightNotFoundException;
import com.atbs.model.Flight;
import com.atbs.model.FlightOwner;
import com.atbs.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightOwnerService flightOwnerService;



    @InjectMocks
    private FlightService flightService;


    private Flight flight;
    private Flight flight1;
    private FlightOwner flightOwner;


    @BeforeEach
    void sampleData(){
        flightOwner = new FlightOwner();
        flightOwner.setId(1);
        flightOwner.setName("owner1");

        flight=new Flight();
        flight.setId(1);
        flight.setFlightName("IndiGo");
        flight.setFlightNumber("1234");
        flight.setTotalSeats(180);
        flight.setCabinBaggage(8.0);
        flight.setCheckInBaggage(10.0);

        flight1 = new Flight();
        flight1.setId(2);
        flight1.setFlightName("IndiGo");
        flight1.setFlightNumber("6E-101");
        flight1.setTotalSeats(180);
        flight1.setCheckInBaggage(15.0);
        flight1.setCabinBaggage(7.0);
        flight1.setFlightOwner(flightOwner);

    }



    @Test
    void getFlight_ByIdExist() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(flightRepository.findById(2)).thenReturn(Optional.of(flight1));

        assertThat(flightService.getByFlightId(1)).isEqualTo(flight);
        assertThat(flightService.getByFlightId(2)).isEqualTo(flight1);
    }

    @Test
    void getFlight_ByIdNotExist() {
        when(flightRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> flightService.getByFlightId(99))
                .isInstanceOf(FlightNotFoundException.class)
                .hasMessage("Invalid Flight ID");
    }

    @Test
    void softDeleteFlight_mustSetInactiveAndSave() {
        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        flightService.softDelete(1);

        assertThat(flight.isActive()).isFalse();
        verify(flightRepository, times(1)).findById(1);
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    void softDeleteFlight_whenNotFound_mustThrow() {
        when(flightRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> flightService.softDelete(99))
                .isInstanceOf(FlightNotFoundException.class)
                .hasMessage("Flight Not Found");

        verify(flightRepository, never()).save(any(Flight.class));
    }

    @Test
     void getAllFlight_WithReturnSomething(){
        when(flightRepository.findAll()).thenReturn(List.of(flight,flight1));

        assertThat(flightService.getAll()).hasSize(2);
        assertThat(flightService.getAll().getFirst().getFlightName()).isEqualTo("IndiGo");
    }

    @Test


     void getAllFlight_WithNothing(){
        when(flightRepository.findAll()).thenReturn(List.of());

        assertThat(flightService.getAll()).isEmpty();
    }

    @Test
    void addFlight(){
        when(flightOwnerService.getByName("owner1")).thenReturn(flightOwner);
        when(flightRepository.save(any(Flight.class))).thenReturn(flight1);
        FlightReqDto dto = new FlightReqDto("IndiGo", "6E-101", 180, 15.0, 7.0);
        Flight actualFlight = flightService.add(dto, flightOwner.getName());
        assertThat(actualFlight.getFlightName()).isEqualTo(flight1.getFlightName());
        assertThat(actualFlight.getFlightOwner()).isEqualTo(flightOwner);
        verify(flightRepository, times(1)).save(any(Flight.class));

    }




}
