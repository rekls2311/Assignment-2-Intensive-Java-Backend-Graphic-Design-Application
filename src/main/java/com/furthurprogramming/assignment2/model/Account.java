package com.furthurprogramming.assignment2.model;

public class Account {
    private String username;
    private String password;

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
