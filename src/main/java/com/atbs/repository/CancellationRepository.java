package com.atbs.repository;

import com.atbs.enums.CancellationStatus;
import com.atbs.model.Cancellation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

    @Query("""  
                
                                select c from Cancellation c
                where c.booking.passenger.user.username=?1
                """)
    Page<Cancellation> getByPassenger(Pageable pageable, String userName);

    @Query("""
             select c from Cancellation c
             where c.booking.schedule.flight.flightOwner.user.username=?1 and c.cancellationStatus<>?2
             """)
    Page<Cancellation> getByFlightOwner(String userName, CancellationStatus requested,Pageable pageable);

    @Query("""
             select c from Cancellation c
             where c.booking.schedule.flight.flightOwner.user.username=?1 and c.cancellationStatus=?2
             """)
    Page<Cancellation> getByFlightOwnerReq(String username, CancellationStatus cancellationStatus, Pageable pageable);
}
