package com.atbs.controller;

import com.atbs.dto.RoutePageResp;
import com.atbs.dto.RouteReqDto;
import com.atbs.model.Route;
import com.atbs.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/route")
@AllArgsConstructor
@CrossOrigin(origins ="http://localhost:5173")

public class RouteController {
    private final RouteService routeService;
    @GetMapping("/all/active")
    public List<Route> getAllActive(){
        return routeService.getAllActive();
    }
    @GetMapping("/all")
    public RoutePageResp getAll(@RequestParam(defaultValue = "0",required = false) int page,
                                      @RequestParam(defaultValue = "10",required = false) int size){
        return routeService.getAll(page,size);
    }

    @GetMapping("/getBy-Id/{id}")
    public Route getbyId(@PathVariable int id){
        return routeService.getById(id);
    }
    @GetMapping("/search")
    public Route getByOriginAndDestination(@RequestParam String origin,@RequestParam String destination){
        return routeService.getByOriginAndDestination(origin,destination);
    }

    @PostMapping("/add")
    public void add(@RequestBody RouteReqDto dto){
        routeService.add(dto);
    }

    @PutMapping("/active/{id}")
    public void active(@PathVariable int id){
        routeService.active(id);
    }
    @PutMapping("/inactive/{id}")
    public void inactive(@PathVariable int id){
        routeService.inactive(id);
    }
    @PutMapping("/discontinue/{id}")
    public void discontinue(@PathVariable int id){
        routeService.discontinue(id);
    }


}
