package com.atbs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,name = "flight_name")
    private String flightName;

    @Column(nullable = false,unique = true,name = "flight_number")
    private String flightNumber;

    @Column(nullable = false,name = "total_seats")
    private int totalSeats;

    private Double checkInBaggage;

    private Double cabinBaggage;
    @ManyToOne
    private FlightOwner flightOwner;

    private boolean isActive=true;
}
