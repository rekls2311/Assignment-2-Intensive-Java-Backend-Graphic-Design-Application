package com.furthurprogramming.assignment2.controller;

import java.io.IOException;

import com.furthurprogramming.assignment2.service.Canvas;
import com.furthurprogramming.assignment2.service.element.CanvasRectangle;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
        CanvasRectangle rect = null;
        try {
            rect = new CanvasRectangle(anchorPaneProperties,100, 100);
            mainCanvas.addElement(rect);
            rect.setY(10).setX(40);
            rect.setRotation(30);
            rect.select();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainController()
    {
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