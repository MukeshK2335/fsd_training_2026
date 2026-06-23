package com.atbs.repository;


import com.atbs.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger,Integer> {



    Optional<Passenger> findByUserUsername(String userName);

    Page<Passenger> findByUser_ActiveTrue(Pageable pageable);
}
