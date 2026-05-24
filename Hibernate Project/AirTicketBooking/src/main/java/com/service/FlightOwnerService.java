package com.service;

import com.exception.UserNotFoundException;
import com.model.FlightOwner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FlightOwnerService {
    private Session session;

    public FlightOwnerService(Session session) {
        this.session = session;
    }

    public void addFlightOwner(FlightOwner flightOwner) {
        Transaction tx = session.beginTransaction();
        if (flightOwner == null) {
            throw new UserNotFoundException("FlightOwner Details Not Found");
        }
        session.persist(flightOwner);
        tx.commit();
    }

    public FlightOwner getFlightOwnerByUserId(int userId) {
        FlightOwner flightOwner = session.createQuery(
                 "FROM FlightOwner WHERE user.id = :userId", FlightOwner.class)
                .setParameter("userId", userId)
                .uniqueResult();
        if (flightOwner == null) {
            throw new UserNotFoundException("FlightOwner not found for User ID: " + userId);
        }
        return flightOwner;
    }

    public void updateFlightOwner(FlightOwner flightOwner) {
        Transaction tx = session.beginTransaction();
        if (flightOwner == null) {
            throw new UserNotFoundException("FlightOwner Not Found");
        }
        session.merge(flightOwner);
        tx.commit();
    }
}