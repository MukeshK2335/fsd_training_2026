package com.atbs.repository;

import com.atbs.enums.RouteStatus;
import com.atbs.model.Route;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer> {

    @Query("""
                    select r from Route r
                    where r.origin=?1 AND r.destination=?2
                    """)
    Optional<Route> getByOriginAndDestination(String origin, String destination);

    @Query("""
               select  r from Route r
               where r.origin=?1 AND r.destination=?2
               """)
    Optional<Route> getRouteByOriginAndDestination(@NotNull @NotBlank String origin, @NotNull @NotBlank String destination);

    List<Route> findByRouteStatus(RouteStatus routeStatus);
}
