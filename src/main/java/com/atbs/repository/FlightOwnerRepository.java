package com.atbs.repository;

import com.atbs.model.FlightOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightOwnerRepository extends JpaRepository<FlightOwner,Integer> {
    Optional<FlightOwner> findByUserUsername(String name);
}
