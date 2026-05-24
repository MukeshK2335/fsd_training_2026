package com.app.dao;

import com.app.model.Flight;
import com.app.model.User;

import java.util.List;

public interface FlightDao {
    List<Flight> getAll();
    Flight getById(String id);

}
