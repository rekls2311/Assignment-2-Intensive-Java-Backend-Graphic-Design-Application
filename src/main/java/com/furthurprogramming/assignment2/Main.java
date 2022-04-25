package com.furthurprogramming.assignment2;

import com.furthurprogramming.assignment2.util.DBUtil;
import java.sql.ResultSet;

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

        if (DBUtil.dbConnect() && DBUtil.dbDisconnect()) {
            ResultSet rs = DBUtil.dbExecuteQuery("SELECT * FROM accounts");
            while(true){
                assert rs != null;
                if (!rs.next()) break;
                String un = rs.getString("username");
                System.out.println(un);
            } DBUtil.dbDisconnect();
            launch();

        }
        else {
            System.out.println("Can't connect to database!");
        }
    }

}