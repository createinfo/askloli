package io.loli.askloli.dao;

import io.loli.askloli.entity.User;

public interface UserDao {

    void save(User user);

    User findByName(String name);

    User findByEmail(String email);
}
