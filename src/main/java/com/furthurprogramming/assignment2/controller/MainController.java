package com.furthurprogramming.assignment2.controller;

import java.io.IOException;

import com.furthurprogramming.assignment2.service.Canvas;
import com.furthurprogramming.assignment2.service.element.ElementRectangle;
import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

// Second scene controller
public class MainController {

    /////////////////////////////////////////////////////////////////////
    // FXML fields
    /////////////////////////////////////////////////////////////////////
    @FXML
    Pane paneCanvas;

    @FXML
    AnchorPane anchorPaneProperties;

    /////////////////////////////////////////////////////////////////////
    // Normal fields
    /////////////////////////////////////////////////////////////////////
    Canvas mainCanvas;


    /////////////////////////////////////////////////////////////////////
    // FXML methods
    /////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize() throws IOException {
        Main.getStage().setResizable(true);


        mainCanvas = new Canvas(paneCanvas, 400, 400);
        mainCanvas.setBackgroundColor(Color.WHITE);



        ElementRectangle rect = null;
        try {
            rect = new ElementRectangle(anchorPaneProperties,100, 100);
            rect.setPosition(40, 40);
            rect.select();
            mainCanvas.addElement(rect);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainCanvas.draw();
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}