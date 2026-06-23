package com.atbs.controller;

import com.atbs.dto.FlightReqDto;
import com.atbs.dto.FlightRespDto;
import com.atbs.dto.FlightResponseDto;
import com.atbs.model.Flight;
import com.atbs.service.FlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")
public class FlightController {

    private final FlightService flightService;
    @GetMapping("/all")
    public List<Flight> getAll(){
        return flightService.getAll();
    }
    @GetMapping("/all/v2")
    public FlightResponseDto getAllv2(@RequestParam int page, @RequestParam int size){
        return flightService.getAllWithPagenation(page,size);
    }

    @GetMapping("/getByName")
    public List<Flight> getByFlighName(@RequestParam String flightName){
        return flightService.getByFlightName(flightName);
    }
    @GetMapping("/v2/getByName")
    public FlightResponseDto getByFlighNamev2(@RequestParam int page,@RequestParam int size,@RequestParam String flightName){
        return flightService.getByFlightNamev2(page,size,flightName);
    }
    @PostMapping("/v2/add")
    public void addV2(@Valid @RequestBody FlightReqDto dto){
        flightService.addFlight(dto);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getByFlightId(@PathVariable int id){
        return ResponseEntity.ok( flightService.getByFlightId(id));
    }

    @DeleteMapping("/deleteByID/{id}")
    public void deleteById(@PathVariable int id){
            flightService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateFlight(@PathVariable int id,@RequestBody FlightReqDto dto)
    {
            flightService.update(id,dto);
    }

    @GetMapping("/getby-Id/{id}")
    public FlightRespDto getById(@PathVariable int id, Principal principal){
        String userName= principal.getName();
        return flightService.getById(id,userName);
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody FlightReqDto dto,Principal principal){
        flightService.add(dto,principal.getName());
    }

    @GetMapping("/flight-owner")
    public FlightResponseDto getFlightByFlightOwner(@RequestParam(defaultValue = "0",required = false) int page,
                                               @RequestParam(defaultValue = "10",required = false) int size,Principal principal){
        String username= principal.getName();
        return flightService.getFlightByFlightOwner(page,size,username);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        flightService.softDelete(id);
    }
}
