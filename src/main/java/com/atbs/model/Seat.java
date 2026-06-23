package com.atbs.model;

import com.atbs.enums.SeatClass;
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
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "seat_number",nullable = false)
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class",nullable = false)
    private SeatClass seatClass;
    @Column(nullable = false)
    private Boolean isAvailable=true ;

    @ManyToOne
    private Schedule schedule;
}
