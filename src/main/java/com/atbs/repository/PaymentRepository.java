package com.atbs.repository;

import com.atbs.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {


    @Query("""
                   select p from Payment p
                   where p.booking.id=?1
                   """)
    Optional<Payment> getPaymentByBookingId(int bookingId);

    @Query(""" 
                select p  from Payment p
                where p.id=?1
                """)
    Optional<Payment> getByPaymentByPaymentId(int paymentId);

    Page<Payment> findByBookingScheduleFlightFlightOwnerUserUsername(String username, Pageable pageable);
}
