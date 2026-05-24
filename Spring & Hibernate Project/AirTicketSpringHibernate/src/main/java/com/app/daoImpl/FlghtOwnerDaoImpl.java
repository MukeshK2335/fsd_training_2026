package com.app.daoImpl;

import com.app.dao.FlightOwnerDao;
import com.app.exception.FlightNotFoundException;
import com.app.model.Flight;
import com.app.model.FlightOwner;
import com.app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
@Transactional
public class FlghtOwnerDaoImpl implements FlightOwnerDao {
    @PersistenceContext
    private EntityManager en;

    @Override
    public List<Flight> myFlight(User user) {
        List<Flight> flights=en.createQuery("select f from Flight f where f.flightOwner.user.id=:id",Flight.class)
                .setParameter("id",user.getId())
                .getResultList();
        return flights;
    }

    @Override
    public void addFlight(Flight flight,User user) {
        FlightOwner owner =findOwnerByUser(user);
        flight.setFlightOwner(owner);
    }

    @Override
    public Flight findByIdAndOwner(int id, User user) {
        List<Flight> result = en.createQuery(
                        "select f from Flight f where f.id = :id and f.flightOwner.user.id = :userId", Flight.class)
                .setParameter("id", id)
                .setParameter("userId", user.getId())
                .getResultList();

        if (result.isEmpty()) {
           throw new FlightNotFoundException("Flight not found or does not belong to you");
        }
        return result.getFirst();
    }

    @Override
    public void deleteFlight(int id, User user) {
        Flight flight = findByIdAndOwner(id, user);  // reuses the ownership check
        if (flight == null) {
           throw new FlightNotFoundException("Flight Not found Or not Belongs to you");
        }
        en.remove(flight);
    }

    public FlightOwner findOwnerByUser(User user) {
        return en.createQuery(
                        "select fo from FlightOwner fo where fo.user.id = :userId", FlightOwner.class)
                .setParameter("userId", user.getId())
                .getSingleResult();
    }
}
