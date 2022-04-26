package com.furthurprogramming.assignment2;

import com.furthurprogramming.assignment2.model.UserDAO;
import com.furthurprogramming.assignment2.util.DBUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;

        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws SQLException {

        if (DBUtil.connect() != null) {
              //UserDAO.createUser("admin", "12345", "admin cha", "admin con", null);
            launch();
        }
        else {
            System.out.println("Can't connect to database!");
        }
    }

}