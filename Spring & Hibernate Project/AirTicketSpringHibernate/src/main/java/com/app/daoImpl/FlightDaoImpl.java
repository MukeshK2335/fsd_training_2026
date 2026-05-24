package com.app.daoImpl;

import com.app.dao.FlightDao;
import com.app.exception.FlightNotFoundException;
import com.app.model.Flight;
import com.app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FlightDaoImpl implements FlightDao {

    @PersistenceContext
    private EntityManager en;
    @Override
    public List<Flight> getAll() {
        List<Flight> flights=en.createQuery("select f from Flight f",Flight.class)
                .getResultList();

        return flights;
    }

    @Override
    public Flight getById(String id) {
        Flight flight=en.createQuery("select f from Flight f where f.flightNumber=:flightnumber",Flight.class)
                .setParameter("flightnumber",id)
                .getSingleResult();

        return flight;

    }


}
