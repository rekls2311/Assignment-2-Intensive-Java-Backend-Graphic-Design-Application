package com.furthurprogramming.assignment2.model;
import javafx.beans.property.*;
import javafx.scene.image.Image;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private Image profilePicture;

    public User(String firstName, String lastName, Image profilePicture, String username)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
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

    public Image getProfilePicture() {
        return profilePicture;
    }
}
