package com.app.dao;

import com.app.model.Flight;
import com.app.model.FlightOwner;
import com.app.model.User;

import java.util.List;

public interface FlightOwnerDao {
    List<Flight> myFlight(User user);
    void addFlight(Flight flight,User user);
    Flight findByIdAndOwner(int id, User user);
    void deleteFlight(int id, User user);
    FlightOwner findOwnerByUser(User user);
}
