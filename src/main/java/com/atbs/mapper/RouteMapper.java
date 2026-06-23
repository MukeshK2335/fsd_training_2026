package com.atbs.mapper;


import com.atbs.dto.RoutePageResp;
import com.atbs.dto.RouteReqDto;
import com.atbs.model.Route;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;



public class RouteMapper {
    private RouteMapper(){}

    public static Route mapDto2Entity(RouteReqDto dto){
        Route route=new Route();
        route.setDestination(dto.destination());
        route.setOrigin(dto.origin());
        return route;
    }
    public static RoutePageResp mapPageToDto(Page<Route> pages){
        long totalElements =  pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<Route> list=pages.getContent();
        return new RoutePageResp(
                totalElements,
                totalPages,
                list
        );
    }
}
