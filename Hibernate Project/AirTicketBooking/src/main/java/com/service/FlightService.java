package com.service;

import com.exception.UserNotFoundException;
import com.model.Flight;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FlightService {
    private Session session;

    public FlightService(Session session) {
        this.session = session;
    }



    public void addFlight(Flight flight) {
        Transaction tx = session.beginTransaction();
        if (flight == null) {
            throw new UserNotFoundException("Flight Details Not Found");
        }
        session.persist(flight);
        tx.commit();
    }


    public List<Flight> getFlightsByOwner(int ownerId) {
        List<Flight> flights = session.createQuery(
                        "FROM Flight WHERE flightOwner.id = :ownerId", Flight.class)
                .setParameter("ownerId", ownerId)
                .list();
        if (flights.isEmpty()) {
            throw new UserNotFoundException("No Flights Found for Owner ID: " + ownerId);
        }
        return flights;
    }


    public void deleteFlight(int id) {
        Transaction tx = session.beginTransaction();
        Flight flight = session.find(Flight.class, id);
        if (flight == null) {
            throw new UserNotFoundException("Flight Not Found for ID: " + id);
        }
        session.remove(flight);
        tx.commit();
    }

    public Flight getFlightById(int id) {
        Flight flight = session.find(Flight.class, id);
        if (flight == null) {
            throw new UserNotFoundException("Flight Not Found for ID: " + id);
        }
        return flight;
    }
}