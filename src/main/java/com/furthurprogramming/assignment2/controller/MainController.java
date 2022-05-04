package com.furthurprogramming.assignment2.controller;

import java.io.IOException;

import com.furthurprogramming.assignment2.service.Canvas;
import com.furthurprogramming.assignment2.service.element.CanvasCircle;
import com.furthurprogramming.assignment2.service.element.CanvasRectangle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
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

    @FXML
    Button buttonAddRectangle;

    @FXML
    Button buttonAddCircle;


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

        paneCanvas.setStyle("-fx-background-color: #" + Color.DARKGRAY.toString().substring(2));

        mainCanvas.setBackgroundColor(Color.MEDIUMVIOLETRED);
        createHandlers();
    }

    private void createHandlers()
    {
        buttonAddCircle.setOnAction(this::buttonAddCircleOnActionHandler);
        buttonAddRectangle.setOnAction(this::buttonAddRectangleOnActionHandler);
    }

    public MainController()
    {
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////
    private void buttonAddCircleOnActionHandler(ActionEvent e){
        mainCanvas.addElement(new CanvasCircle(anchorPaneProperties,100));
    }

    private void buttonAddRectangleOnActionHandler(ActionEvent e){
        mainCanvas.addElement(new CanvasRectangle(anchorPaneProperties,200, 100));
    }

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}