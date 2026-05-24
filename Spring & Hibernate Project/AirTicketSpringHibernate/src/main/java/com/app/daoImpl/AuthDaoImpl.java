package com.app.daoImpl;

import com.app.dao.AuthDao;
import com.app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AuthDaoImpl implements AuthDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User login(String username, String password) {
        User user=entityManager.createQuery("select u from User u where u.username=:username and u.password=:password", User.class)
                .setParameter("username",username)
                .setParameter("password",password)
                .getSingleResult();
        return user;
    }
}
