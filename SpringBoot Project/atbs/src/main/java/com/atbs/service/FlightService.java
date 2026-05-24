package com.atbs.service;

import com.atbs.exception.FlightNotFoundException;
import com.atbs.model.Flight;
import com.atbs.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAll() {
         return flightRepository.findAll();
    }

    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public Flight getByFlightId(int id) {
        return flightRepository.findById(id).orElseThrow(()->new FlightNotFoundException("Invalid Flight ID"));

    }

    public void deleteById(int id) {
        getByFlightId(id);

        flightRepository.deleteById(id);
    }

    public void update(int id, Flight flight) {
        Flight existingFlight=getByFlightId(id);
        existingFlight.setFlightName(flight.getFlightName());
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setTotalSeats(flight.getTotalSeats());
        existingFlight.setCheckInBaggage(flight.getCheckInBaggage());
        existingFlight.setCabinBaggage(flight.getCabinBaggage());
        flightRepository.save(existingFlight);

    }
}
