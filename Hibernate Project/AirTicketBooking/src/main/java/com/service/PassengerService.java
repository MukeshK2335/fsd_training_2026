package com.service;

import com.exception.UserNotFoundException;
import com.model.Passenger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PassengerService {
    private Session session;
    public PassengerService(Session session) {
        this.session=session;
    }

    public void addPassenger(Passenger passenger) {
        Transaction tx=session.beginTransaction();
        if(passenger==null){
            throw new UserNotFoundException("Passenger Details Not Found");
        }
        session.persist(passenger);
        tx.commit();
    }

    public Passenger viewPassenger(int id) {
        Transaction tx=session.beginTransaction();
        Passenger passenger=session.createQuery("select p from Passenger p where user.id=:id", Passenger.class)
                .setParameter("id",id)
                .uniqueResult();
        if(passenger==null){
            throw new UserNotFoundException("Invalid Passenger");
        }
        tx.commit();
        return passenger;
    }

    public void updatePassenger(Passenger passenger) {
        Transaction tx=session.beginTransaction();
        if(passenger==null){
            throw new UserNotFoundException("Passenegr Not Found");
        }
        session.merge(passenger);
        tx.commit();
    }
}
