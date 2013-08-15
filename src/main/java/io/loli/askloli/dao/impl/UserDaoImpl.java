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

    public User findByName(String name) {
        return em.createNamedQuery("User.findByName", User.class)
                .setParameter("username", name).getSingleResult();
    }

    public User findByEmail(String email) {
        return em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email).getSingleResult();
    }

    public User findByNameAndPassword(String name, String password) {
        return em.createNamedQuery("User.findByNameAndPassword", User.class)
                .setParameter("username", name)
                .setParameter("password", password).getSingleResult();

    }
}