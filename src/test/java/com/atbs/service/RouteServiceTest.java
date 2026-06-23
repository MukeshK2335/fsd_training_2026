package com.atbs.service;

import com.atbs.dto.RoutePageResp;
import com.atbs.dto.RouteReqDto;
import com.atbs.enums.RouteStatus;
import com.atbs.exception.RouteNotFoundException;
import com.atbs.model.Route;
import com.atbs.repository.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
 class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteService routeService;

    private Route route;
    private Route route1;
    private Route route2;

    @BeforeEach
     void sampleData() {
        route = new Route();
        route.setId(1);
        route.setOrigin("Chennai");
        route.setDestination("Mumbai");
        route.setRouteStatus(RouteStatus.ACTIVE);

        route1 = new Route();
        route1.setId(2);
        route1.setOrigin("Delhi");
        route1.setDestination("Bangalore");
        route1.setRouteStatus(RouteStatus.INACTIVE);

        route2 = new Route();
        route2.setId(3);
        route2.setOrigin("Coimbatore");
        route2.setDestination("Bangalore");
        route2.setRouteStatus(RouteStatus.DISCONTINUED);
    }

    @Test
     void addRoute(){
        when(routeRepository.save(any(Route.class))).thenReturn(route);
        RouteReqDto dto=new RouteReqDto("Chennai", "Mumbai");
        Route actualRoute=routeService.add(dto);
        assertThat(actualRoute).isEqualTo(route);
        verify(routeRepository,times(1)).save(any(Route.class));
    }

    @Test
     void getById_WithExist(){
        when(routeRepository.findById(1)).thenReturn(Optional.of(route));
        when(routeRepository.findById(2)).thenReturn(Optional.of(route1));

        assertThat(routeService.getById(1)).isEqualTo(route);
        assertThat(routeService.getById(2)).isEqualTo(route1);
    }

    @Test
     void getById_WithNoExist(){
        when(routeRepository.findById(100)).thenReturn(Optional.empty());
        when(routeRepository.findById(101)).thenReturn(Optional.empty());

        assertThatThrownBy(()->routeService.getById(100)).isInstanceOf(RouteNotFoundException.class)
                .hasMessage("Not Found");
        assertThatThrownBy(()->routeService.getById(101)).isInstanceOf(RouteNotFoundException.class)
                .hasMessage("Not Found");
    }

    @Test
     void getAllRoute_WithExistActive(){
        when(routeRepository.findByRouteStatus(RouteStatus.ACTIVE)).thenReturn(List.of(route,route1));

        assertThat(routeService.getAllActive()).hasSize(2);

    }
    @Test
     void getAllRoute_WithNoExistActive(){
        when(routeRepository.findByRouteStatus(RouteStatus.ACTIVE)).thenReturn(List.of());

        assertThat(routeService.getAllActive()).isEmpty();
        verify(routeRepository,times(1)).findByRouteStatus(RouteStatus.ACTIVE);
    }

    @Test
    void searchWithOriginAndDestination(){
        when(routeRepository.getByOriginAndDestination("Chennai","Mumbai")).thenReturn(Optional.of(route));

        assertThat(routeService.getByOriginAndDestination("Chennai","Mumbai")).isEqualTo(route);
        verify(routeRepository,times(1)).getByOriginAndDestination("Chennai","Mumbai");
    }

    @Test
     void searchWithOriginAndDestinationNotExist(){
        when(routeRepository.getByOriginAndDestination("Delhi","Mumbai")).thenReturn(Optional.empty());

        assertThatThrownBy(()->routeService.getByOriginAndDestination("Delhi","Mumbai")).isInstanceOf(RouteNotFoundException.class)
                        .hasMessage("Not Found");
        verify(routeRepository,times(1)).getByOriginAndDestination("Delhi","Mumbai");
    }

    @Test
    void cancelRoute(){
        when(routeRepository.findById(3)).thenReturn(Optional.of(route2));
        when(routeRepository.save(any(Route.class))).thenReturn(route2);
        Route actualRoute = routeService.discontinue(3);


        assertThat(actualRoute).isEqualTo(route2);
        assertThat(actualRoute.getOrigin()).isEqualTo("Coimbatore");
        assertThat(actualRoute.getDestination()).isEqualTo("Bangalore");
        assertThat(actualRoute.getRouteStatus()).isEqualTo(RouteStatus.DISCONTINUED);

    }

    @Test
     void getAll_WithPage(){
        Page<Route> page = new PageImpl<>(List.of(route));
        when(routeRepository.findAll(any(Pageable.class))).thenReturn(page);
        RoutePageResp actualCall = routeService.getAll(1, 2);
        assertThat(actualCall.data().getFirst().getId()).isEqualTo(1);
        assertThat(actualCall.data().getFirst().getOrigin()).isEqualTo("Chennai");
        assertThat(actualCall.data().getFirst().getDestination()).isEqualTo("Mumbai");
        assertThat(actualCall.data().getFirst().getRouteStatus()).isEqualTo(RouteStatus.ACTIVE);

    }








}
