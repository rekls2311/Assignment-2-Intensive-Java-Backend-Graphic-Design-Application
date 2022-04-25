package com.furthurprogramming.assignment2.model;
import javafx.beans.property.*;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String profilePicturePath;

    public User(String firstName, String lastName, String profilePicturePath, String username)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicturePath = profilePicturePath;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
