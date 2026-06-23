package com.atbs.repository;

import com.atbs.dto.LabelCountDto;
import com.atbs.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {

    @Query("""
            SELECT s FROM Schedule s
            WHERE s.route.origin = ?1
            AND s.route.destination = ?2
            AND s.scheduledDate= ?3
            """)
    List<Schedule> getBySearch(String origin, String destination, LocalDate date);

    @Query("""
                 select s from Schedule s
                 where s.flight.flightOwner.user.username=?1
                 """)
    Page<Schedule> getAllFlightOwner(String userName, Pageable pageable);

    List<Schedule> findByFlightFlightOwnerUserUsername(String username);

    @Query("""
             select new com.atbs.dto.LabelCountDto(s.scheduleStatus ,count(s.id))
             from Schedule s
             join s.flight f
             join f.flightOwner fo
             join fo.user u
             where u.username = ?1
             group by s.scheduleStatus
             """)
    List<LabelCountDto> getScheduleStatusStat(String username);
}
