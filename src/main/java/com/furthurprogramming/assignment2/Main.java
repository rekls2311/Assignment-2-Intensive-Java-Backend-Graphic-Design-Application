package com.furthurprogramming.assignment2;

import com.furthurprogramming.assignment2.model.AccountDAO;
import com.furthurprogramming.assignment2.util.DBUtil;

import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

// Inhirit Application class to create a stage
public class Main extends Application {

    private static Scene scene;
    private static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;

        Parent fxml = JavaFXUtil.loadFXML("main");
        scene = new Scene(fxml);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
        var newFxml = JavaFXUtil.loadFXML(fxml);
        scene.setRoot(newFxml);
        stage.sizeToScene();
    }

    public static boolean logIn(String un, String password)
    {
        if (checkAuthentication(un, password)) {
            try {
                setRoot("main");
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