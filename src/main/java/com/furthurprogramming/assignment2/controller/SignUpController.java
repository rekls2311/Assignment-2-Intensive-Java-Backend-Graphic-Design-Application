package com.furthurprogramming.assignment2.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import com.furthurprogramming.assignment2.Main;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Scene controller
public class SignUpController {

        //////////////////////  ///////////////////////////////////////////////
        // FXML fields
        /////////////////////////////////////////////////////////////////////
        @FXML
        TextField textUserName;
        @FXML
        TextField textFirstName;
        @FXML
        TextField textLastName;
        @FXML
        PasswordField passwordFieldPassword;
        @FXML
        Button buttonCreateUser;
        @FXML
        Button buttonSignUpClose;
        @FXML
        Text textRegisteredmessage;



        /////////////////////////////////////////////////////////////////////
        // Normal fields
        /////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////
        // FXML methods
        /////////////////////////////////////////////////////////////////////
        @FXML
        private void initialize() throws IOException {
            Main.getStage().setResizable(false);

            buttonSignUpClose.setOnAction(action -> closeSignUpWindow());

        /////////////////////////////////////////////////////////////////////
        // Event handler
        ////////////////////////////////////////////////////////////////////
        }
        private void closeSignUpWindow() {
            try {
                 Main.setRoot("login");
            } catch (IOException e) {
                 System.out.println(e.getMessage());
            }
        }
        private void CreateUser(){
            String un = textUserName.getText();
        }


}
