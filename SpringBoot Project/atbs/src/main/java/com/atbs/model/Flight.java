package com.atbs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

@Entity
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String flightName;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private int totalSeats;

    private Double checkInBaggage;

    private Double cabinBaggage;
}
