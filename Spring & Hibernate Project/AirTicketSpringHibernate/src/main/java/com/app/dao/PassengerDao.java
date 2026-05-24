package com.app.dao;

import com.app.model.Passenger;
import com.app.model.User;

public interface PassengerDao {
    Passenger viewProfile(User user);
    void updateEmail(String email,User user);
}
