package io.loli.askloli.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
        @NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.username=:username AND u.password=:password") })
@XmlRootElement
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

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlAttribute
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
