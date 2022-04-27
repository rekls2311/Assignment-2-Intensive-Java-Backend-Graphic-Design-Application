package com.furthurprogramming.assignment2.controller;

import java.io.File;
import java.io.IOException;

import com.furthurprogramming.assignment2.model.UserDAO;
import javafx.fxml.FXML;
import com.furthurprogramming.assignment2.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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
    Label labelPromptMessage;
    @FXML
    ImageView imageviewProfileimage;
    @FXML
    Button buttonAddimage;



    /////////////////////////////////////////////////////////////////////
    // Normal fields
    /////////////////////////////////////////////////////////////////////
    String profileImagePath;

    /////////////////////////////////////////////////////////////////////
    // FXML methods
    /////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize() throws IOException {
        Main.getStage().setResizable(false);
        promptUser("Hello, Welcome to us!", "110, 240, 105");

        buttonSignUpClose.setOnAction(action -> closeSignUpWindow());
        textUserName.setOnKeyTyped(keyEvent -> textUserNameKeyTypedHandler());
        buttonCreateUser.setOnAction(actionEvent -> createUser());
        buttonAddimage.setOnAction(actionEvent -> buttonOnImageActionHandler());

    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////
    private void textUserNameKeyTypedHandler() {

        String un = textUserName.getText();

        var user = UserDAO.searchUser(un);

        if (user != null)
        {
            promptUser("%s is already existed!".formatted(un));
        }
        else
        {
            promptUser("");
        }
    }

    private void buttonOnImageActionHandler(){
        var file = chooseImageFile();

        if (file == null)
            return;

        profileImagePath = file.getAbsolutePath();

        Image img = new Image(file.getAbsolutePath());

        setImageviewProfileimage(img);
    }

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////
    private void closeSignUpWindow() {
            try {
                 Main.setRoot("login");
            } catch (IOException e) {
                 System.out.println(e.getMessage());
            }
        }
    private void createUser(){

        String un = textUserName.getText();
        String password = passwordFieldPassword.getText();
        String firstname = textFirstName.getText();
        String lastname = textLastName.getText();

        if (un.isBlank() || password.isBlank() || firstname.isBlank() || lastname.isBlank()){
            promptUser("Please fill out all the form");
            return;
        }

        if (profileImagePath == null)
            profileImagePath = imageviewProfileimage.getImage().getUrl();

        if (UserDAO.createUser(un, password,firstname,lastname, profileImagePath))
        {
            Main.LogIn(un, password);
        }
    }

    // Show prompt message with rgb (ex: '255,255,255')
    private void promptUser(String message, String rgb){
        labelPromptMessage.setText(message);
        labelPromptMessage.setVisible(true);
        labelPromptMessage.setTextFill(Paint.valueOf("rgb(%s)".formatted(rgb)));
    }
    private void promptUser(String message){
        promptUser(message, "0,0,0");
    }

    private File chooseImageFile()
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File chosenFile = fileChooser.showOpenDialog(Main.getStage());
        return chosenFile;
    }

    private void setImageviewProfileimage(Image img)
    {
        imageviewProfileimage.setImage(img);
    }
}
