package com.app.Dao;

import com.app.exception.PassengerNotFoundException;
import com.app.model.Passenger;

import java.util.List;

public interface PassengerDao {
    void addPassenger(Passenger passenger);
    void deletePassengerById(int id) throws PassengerNotFoundException;
    void updatePassenger(Passenger passenger);
    Passenger getPassengerById(int id) throws PassengerNotFoundException;
    List<Passenger> getAllPassenger();
}
