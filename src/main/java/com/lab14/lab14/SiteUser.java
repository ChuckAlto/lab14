package com.lab14.lab14;


import javax.persistence.*;

@Entity
public class SiteUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;

    public SiteUser() {
    }

    public SiteUser(String username, String password) {
        this.username = username;
        this.password = password;
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
}
