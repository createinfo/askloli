package io.loli.askloli.service.impl;

import io.loli.askloli.dao.UserDao;
import io.loli.askloli.entity.User;
import io.loli.askloli.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("userService")
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user) {
        if (isExistByUsername(user.getUsername())
                && isExistByEmail(user.getEmail())) {
            user.setUsername(null);
            user.setPassword(null);
            user.setEmail(null);
        } else {
            userDao.save(user);
        }
    }

    public boolean isExistByUsername(String username) {
        return findByUsername(username) != null;
    }

    public boolean isExistByEmail(String email) {
        return findByEmail(email) != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUsername(String username) {
        User user = null;
        try {
            user = userDao.findByName(username);
        } catch (NoResultException e) {
        }
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByEmail(String email) {
        User user = null;
        try {
            user = userDao.findByEmail(email);
        } catch (NoResultException e) {
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            user = userDao.findByNameAndPassword(username, password);
        } catch (NoResultException e) {
        }
        return user;
    }
    
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findById(int id){
        return userDao.findById(id);
    }
}
