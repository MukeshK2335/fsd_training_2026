package com.atbs.repository;

import com.atbs.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findByScheduleIdAndIsAvailableTrue(Integer scheduleId);
}
