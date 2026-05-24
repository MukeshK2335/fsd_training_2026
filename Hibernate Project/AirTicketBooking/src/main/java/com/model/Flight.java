package com.model;

import jakarta.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String flightName;
    @Column(nullable = false,unique = true)
    private String flightNumber;
    @Column(nullable = false)
    private int totalSeats;

    @ManyToOne
    private FlightOwner flightOwner;

    private Double checkInBaggage;
    private Double cabinBaggage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public FlightOwner getFlightOwner() {
        return flightOwner;
    }

    public void setFlightOwner(FlightOwner flightOwner) {
        this.flightOwner = flightOwner;
    }

    public Double getCheckInBaggage() {
        return checkInBaggage;
    }

    public void setCheckInBaggage(Double checkInBaggage) {
        this.checkInBaggage = checkInBaggage;
    }

    public Double getCabinBaggage() {
        return cabinBaggage;
    }

    public void setCabinBaggage(Double cabinBaggage) {
        this.cabinBaggage = cabinBaggage;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "id=" + id +
                ", flightName='" + flightName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", totalSeats=" + totalSeats +
                ", flightOwner=" + flightOwner +
                ", checkInBaggage=" + checkInBaggage +
                ", cabinBaggage=" + cabinBaggage +
                '}';
    }


}
