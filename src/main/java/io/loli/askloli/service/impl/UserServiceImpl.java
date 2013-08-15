package io.loli.askloli.service.impl;

import io.loli.askloli.dao.UserDao;
import io.loli.askloli.entity.User;

import javax.inject.Inject;
import javax.inject.Named;

@Named("userService")
public class UserServiceImpl {
    @Inject
    private UserDao userDao;
    
    public void save(User user){
        userDao.save(user);
    }
    
    public void exisit(String email_username){
        userDao.findByEmail(email_username);
        userDao.findByName(email_username);
    }
}
