package io.loli.askloli.dao.impl;

import io.loli.askloli.dao.UserDao;
import io.loli.askloli.entity.User;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("userDao")
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }
}