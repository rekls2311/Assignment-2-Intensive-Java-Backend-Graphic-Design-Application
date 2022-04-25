package com.furthurprogramming.assignment2.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.furthurprogramming.assignment2.Main;

// Scene controller
public class LoginController {

    /////////////////////////////////////////////////////////////////////
    // FXML fields
    /////////////////////////////////////////////////////////////////////
    @FXML
    TextField textUsername;
    @FXML
    PasswordField passwordPassword;
    @FXML
    TextField textPassword;
    @FXML
    CheckBox checkboxTogglePassword;
    @FXML
    Button buttonLogin;
    @FXML
    Button buttonClose;
    @FXML
    Button buttonCreateNewUser;
    @FXML
    Label labelLoginMessage;

    /////////////////////////////////////////////////////////////////////
    // Normal fields
    /////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    // FXML methods
    /////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize() throws IOException {
        Main.getStage().setResizable(false);

        // Initialize password and textPassword
        TogglePassword(checkboxTogglePassword.isSelected());
        toggleLableLoginMessage(false);

        checkboxTogglePassword
                .setOnMouseReleased(mouseEvent -> checkboxTogglePasswordOnMouseReleaseHandler(mouseEvent));

        passwordPassword.setOnKeyTyped(key -> passwordPasswordOnKeyTypedHandler(key));

        textPassword.setOnKeyTyped(key -> textPasswordOnKeyTypedHandler(key));
        buttonClose.setOnAction(action -> closeLoginWindow());
        buttonLogin.setOnAction(action -> loginButtonOnActionHandler(action));
        buttonCreateNewUser.setOnAction(action -> buttonCreateNewUserOnActionHandler(action));
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    /////////////////////////////////////////////////////////////////////
    private void loginButtonOnActionHandler(ActionEvent actionEvent) {
        String un = textUsername.getText();
        String pw = textPassword.getText();

        if (un.isBlank() || pw.isBlank()) {
            toggleLableLoginMessage(true, "Please enter username and password");
            return;
        }

        if (!checkAuthentication(un, pw)) {
            toggleLableLoginMessage(true, "Incorrect");
            return;
        }

        logIn();
    }

    private void textPasswordOnKeyTypedHandler(KeyEvent keyEvent) {
        passwordPassword.setText(textPassword.getText());
    }

    private void passwordPasswordOnKeyTypedHandler(KeyEvent keyEvent) {
        textPassword.setText(passwordPassword.getText());
    }

    private void checkboxTogglePasswordOnMouseReleaseHandler(MouseEvent mouseEvent) {
        TogglePassword(checkboxTogglePassword.isSelected());
    }

    private void buttonCreateNewUserOnActionHandler(ActionEvent actionEvent) {
        signUp();
    }
    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////
    private void logIn() {
        if (textUsername.getText().isBlank() || passwordPassword.getText().isBlank())
            return;

        try {
            Main.setRoot("welcome");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void TogglePassword(boolean show) {
        passwordPassword.setManaged(!show);
        passwordPassword.setVisible(!show);
        textPassword.setManaged(show);
        textPassword.setVisible(show);
    }

    private void toggleLableLoginMessage(boolean show, String msg) {
        labelLoginMessage.setManaged(show);
        labelLoginMessage.setVisible(show);

        if (show) {
            labelLoginMessage.setText(msg);
        }
    }

    private void toggleLableLoginMessage(boolean show) {
        toggleLableLoginMessage(show, "");
    }

    private void closeLoginWindow() {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    private boolean checkAuthentication(String username, String password) {
        if (username.equals("admin") && password.equals("12345"))
            return true;

        return false;
    }

    private void signUp() {
        try {
            Main.setRoot("signup");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
