package io.loli.askloli.service;

import io.loli.askloli.entity.User;

public interface UserService {

    void save(User user);

    User findByUsernameAndPassword(String username, String password);

    boolean isExistByUsername(String username);

    boolean isExistByEmail(String email);

    User findById(int id);
}