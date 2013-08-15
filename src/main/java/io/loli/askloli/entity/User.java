package io.loli.askloli.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 用户信息的实体
 * 
 * @author choco
 * 
 */
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.username=:username"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email=:email"),
        @NamedQuery(name = "User.findByNameAndEmail", query = "SELECT u FROM User u WHERE u.username=:username AND u.password=:password") })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(length = 32, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
