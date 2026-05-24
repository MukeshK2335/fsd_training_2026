package com.service;

import com.enums.RoleType;
import com.exception.UserNotFoundException;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserService {
    private Session session;
    public UserService(Session session) {
        this.session=session;
    }


    public void addUser(User user) {
        Transaction tx=session.beginTransaction();
        if(user==null){
            throw new UserNotFoundException("User credentials Noit found");
        }
        user.setRole(RoleType.PASSENGER);
        session.persist(user);
        tx.commit();
    }

    public User authunticateUser(String userName, String password) {
        Transaction tx=session.beginTransaction();
        User user=session.createQuery("from User where username=:username and password=:password", User.class)
                .setParameter("username",userName)
                .setParameter("password",password)
                .uniqueResult();
        if(user==null){
            throw new UserNotFoundException("Invalid Credentials");
        }
        tx.commit();
        return user;

    }
}
