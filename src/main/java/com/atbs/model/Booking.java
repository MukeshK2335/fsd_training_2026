package com.atbs.model;

import com.atbs.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    @Column(nullable = false,name = "total_amount")
    private Double totalAmount;
    @CreationTimestamp
    @Column(name = "booked_at")
    private Instant bookedAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "booking_status")
    private BookingStatus bookingStatus=BookingStatus.PENDING;
    @ManyToOne
    private Passenger passenger;
    @ManyToOne
    private Schedule schedule;

}
