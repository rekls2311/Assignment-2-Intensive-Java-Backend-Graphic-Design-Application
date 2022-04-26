package com.furthurprogramming.assignment2.controller;

import java.io.IOException;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;
import javafx.scene.image.ImageView;

// Second scene controller
public class WelcomeController {

    @FXML
    ImageView imageViewProfilePicture;
    @FXML
    private void initialize() throws IOException {
        Main.getStage().setResizable(false);

    }

    @FXML
    private void logOut() throws IOException {
        Main.setRoot("login");
    }
}