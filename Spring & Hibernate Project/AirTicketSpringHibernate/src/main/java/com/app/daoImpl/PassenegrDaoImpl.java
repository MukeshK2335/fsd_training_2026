package com.app.daoImpl;

import com.app.dao.PassengerDao;
import com.app.exception.PassengerNotFoundException;
import com.app.model.Passenger;
import com.app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
@Component
@Transactional
public class PassenegrDaoImpl implements PassengerDao {
    @PersistenceContext
    private EntityManager en;
    @Override
    public Passenger viewProfile(User user) {
        Passenger passenger=en.createQuery("select p from Passenger p where p.user.id=:id", Passenger.class)
                .setParameter("id",user.getId())
                .getSingleResult();
        if(passenger==null){
            throw new PassengerNotFoundException("Invalid User");
        }
        return passenger;
    }

    @Override
    public void updateEmail(String email, User user) {
        Passenger passenger=viewProfile(user);
        passenger.setEmail(email);
        en.merge(passenger);
    }
}
