package com.furthurprogramming.assignment2;

import com.furthurprogramming.assignment2.model.AccountDAO;
import com.furthurprogramming.assignment2.model.UserDAO;
import com.furthurprogramming.assignment2.util.DBUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

// Inhirit Application class to create a stage
public class Main extends Application {

    private static Scene scene;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
        var newFxml = loadFXML(fxml);
        scene.setRoot(newFxml);
        stage.sizeToScene();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static boolean LogIn(String un, String password)
    {
        if (checkAuthentication(un, password)) {
            try {
                setRoot("welcome");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean checkAuthentication(String username, String password) {
        return AccountDAO.isAccountExists(username, password);
    }


    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;

        Parent fxml = loadFXML("login");
        scene = new Scene(fxml);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
        DBUtil.connect();
        if (DBUtil.getConnection() != null) {
            launch();
        }
        else {
            System.out.println("Can't connect to database!");
        }

        DBUtil.disconnect();
    }

}