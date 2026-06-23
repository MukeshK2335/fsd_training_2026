package com.atbs.repository;

import com.atbs.dto.BookingStatusDto;

import com.atbs.dto.StringLabelCountDto;
import com.atbs.enums.BookingStatus;
import com.atbs.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {


    @Query("""
                   select b from Booking b
                   where b.passenger.id=?1
                   """)
    List<Booking> getByPassengerId(int id);

    @Query("""
              select b from Booking b
              where b.schedule.id=?1 
              
              """)
    List<Booking> getByScheduleId(int id);

    List<Booking> findByScheduleFlightFlightOwnerUserUsername(String username);
    Page<Booking> findByScheduleFlightFlightOwnerUserUsername(String username, Pageable pageable);


    @Query("""
                   select b from Booking b
                   where b.passenger.id=?1 and b.bookingStatus<>?2
                   """)
    Page<Booking> getByPassengerIdHistory(int id, BookingStatus bookingStatus,Pageable pageable);


    @Query("""
                   select b from Booking b
                   where b.passenger.id=?1 and b.bookingStatus=?2
                   """)
    List<Booking> getByPassengerIdTicket(int id, BookingStatus bookingStatus);

    @Query("""
             select new com.atbs.dto.StringLabelCountDto(fo.companyName,count(b.id))
             from Booking b
             join b.schedule s
             join s.flight f
             join f.flightOwner fo
             group by fo.companyName
            """)
    List<StringLabelCountDto> getBookingsByFlightOwner();

    @Query("""
select new com.atbs.dto.BookingStatusDto(b.bookingStatus, count(b.id))
from Booking b
join b.passenger p
join p.user u
where u.username = ?1
group by b.bookingStatus
""")

    List<BookingStatusDto> getBookingStatusStat(String username);

    List<Booking> findByPassengerUserUsername(String username);
}
