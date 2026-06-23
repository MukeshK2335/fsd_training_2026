package com.atbs.repository;

import com.atbs.model.BookingPassengerSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPassengerSeatRepository extends JpaRepository<BookingPassengerSeat,Integer> {
    List<BookingPassengerSeat> findByBookingId(int id);
}
