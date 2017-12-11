package org.launchcode.cheesemvc.models;

import java.util.Date;

public class User {

    private int userId;
    private String username;
    private String email;
    private String password;
    private final Date createDate;
    private static int nextId = 1;

    public User() {
        userId = nextId;
        createDate = new Date();
        nextId++;
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
