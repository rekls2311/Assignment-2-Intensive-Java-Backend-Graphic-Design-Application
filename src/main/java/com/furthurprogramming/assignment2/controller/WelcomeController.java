package com.furthurprogramming.assignment2.controller;

import java.io.IOException;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;

// Second scene controller
public class WelcomeController {

    @FXML
    private void logOut() throws IOException {
        Main.setRoot("login");
    }
}