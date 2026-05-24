package com.atbs.controller;

import com.atbs.exception.FlightNotFoundException;
import com.atbs.model.Flight;
import com.atbs.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;
    @GetMapping("/api/flight/all")
    public List<Flight> getAll(){
        return flightService.getAll();
    }

    @PostMapping("/api/flight/add")
    public void addFlight(@RequestBody Flight flight){
        flightService.addFlight(flight);
    }

    @GetMapping("/api/flight/getById/{id}")
    public ResponseEntity<Object> getByFlightId(@PathVariable int id){
        try {
            Flight flight=flightService.getByFlightId(id);
            return ResponseEntity.ok(flight);

        }
        catch (FlightNotFoundException f){
            return ResponseEntity.badRequest().body(f.getMessage());
        }
    }

    @DeleteMapping("/api/flight/deleteByID/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id){
        try {
            flightService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (FlightNotFoundException f)
        {
            return ResponseEntity.badRequest().body(f.getMessage());
        }
    }

    @PutMapping("/api/flight/update/{id}")
    public ResponseEntity<Object> updateFlight(@PathVariable int id,@RequestBody Flight flight)
    {
        try {
            flightService.update(id,flight);
            return ResponseEntity.ok().build();
        }
        catch (FlightNotFoundException f){
            return ResponseEntity.badRequest().body(f.getMessage());
        }

    }

}
