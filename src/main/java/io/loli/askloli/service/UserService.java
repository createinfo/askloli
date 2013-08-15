package io.loli.askloli.service;

import io.loli.askloli.entity.User;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    void save(User user);

    User findByUsernameAndPassword(String username, String password);

    boolean isExistByUsername(String username);

    boolean isExistByEmail(String email);
}