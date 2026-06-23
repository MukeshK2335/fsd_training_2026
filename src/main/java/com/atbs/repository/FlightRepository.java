package com.atbs.repository;


import com.atbs.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {

    List<Flight> findByFlightName(String flightName);
    Page<Flight> findByFlightName(String flightName, Pageable pageable);

    List<Flight> findByFlightOwnerUserUsername(String username);

    Page<Flight> findByFlightOwnerUserUsername(String username,Pageable pageable);

    Page<Flight> findByIsActiveTrue(Pageable pageable);

    Page<Flight> findByFlightOwnerUserUsernameAndIsActiveTrue(String username, Pageable pageable);
    List<Flight> findByFlightOwnerUserUsernameAndIsActiveTrue(String username);
}
