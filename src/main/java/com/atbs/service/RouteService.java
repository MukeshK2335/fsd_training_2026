package com.atbs.service;

import com.atbs.dto.RoutePageResp;
import com.atbs.dto.RouteReqDto;
import com.atbs.enums.RouteStatus;
import com.atbs.exception.RouteNotFoundException;
import com.atbs.mapper.RouteMapper;
import com.atbs.model.Route;
import com.atbs.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private static final String NOT_FOUND = "Not Found";
    public List<Route> getAllActive() {
        return routeRepository.findByRouteStatus(RouteStatus.ACTIVE);

    }
    public RoutePageResp getAll(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Route> dto=routeRepository.findAll(pageable);
        return RouteMapper.mapPageToDto(dto);
    }

    public Route getById(int id) {
        return routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(NOT_FOUND));
    }

    public Route getByOriginAndDestination(String origin, String destination) {
        return routeRepository.getByOriginAndDestination(origin,destination).orElseThrow(()->new RouteNotFoundException(NOT_FOUND));
    }

    public Route add(RouteReqDto dto) {
        if(dto.origin().equals(dto.destination())){
            throw new RouteNotFoundException("Both origin and destination can not be same");
        }
        Optional<Route> existingRoute=routeRepository.getRouteByOriginAndDestination(dto.origin(),dto.destination());
        if(existingRoute.isPresent()){
            throw new RouteNotFoundException("This Route is already Present");
        }
        Route route=RouteMapper.mapDto2Entity(dto);
        route.setRouteStatus(RouteStatus.ACTIVE);
         return routeRepository.save(route);
    }

    public void active(int id) {
        Route route=routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(NOT_FOUND));
        route.setRouteStatus(RouteStatus.ACTIVE);
        routeRepository.save(route);
    }

    public void inactive(int id) {
        Route route=routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(NOT_FOUND));
        route.setRouteStatus(RouteStatus.INACTIVE);
        routeRepository.save(route);
    }

    public Route discontinue(int id) {
        Route route=routeRepository.findById(id).orElseThrow(()->new RouteNotFoundException(NOT_FOUND));
        route.setRouteStatus(RouteStatus.DISCONTINUED);
         return routeRepository.save(route);
    }


}
