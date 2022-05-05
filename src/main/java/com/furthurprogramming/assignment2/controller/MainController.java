package com.furthurprogramming.assignment2.controller;

import java.io.IOException;

import com.furthurprogramming.assignment2.service.canvas.Canvas;
import com.furthurprogramming.assignment2.service.element.CanvasCircle;
import com.furthurprogramming.assignment2.service.element.CanvasRectangle;
import com.furthurprogramming.assignment2.service.element.CanvasText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// Second scene controller
public class MainController {

    /////////////////////////////////////////////////////////////////////
    // FXML fields
    /////////////////////////////////////////////////////////////////////
    @FXML
    Pane paneCanvas;

    @FXML
    VBox vBoxProperties;

    @FXML
    Button buttonAddRectangle;

    @FXML
    Button buttonAddCircle;
    @FXML
    Button buttonAddText;


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

        mainCanvas.setBackgroundColor(Color.WHITE);
        createHandlers();
    }

    private void createHandlers()
    {
        buttonAddCircle.setOnAction(this::buttonAddCircleOnActionHandler);
        buttonAddRectangle.setOnAction(this::buttonAddRectangleOnActionHandler);
        buttonAddText.setOnAction(this::buttonAddTextOnActionHandler);
    }

    public MainController()
    {
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////
    private void buttonAddCircleOnActionHandler(ActionEvent e){
        var circleElem = new CanvasCircle(vBoxProperties,100);
        mainCanvas.addElement(circleElem);
        circleElem.IsSelected.set(true);
    }

    private void buttonAddRectangleOnActionHandler(ActionEvent e){
        var rectElem = new CanvasRectangle(vBoxProperties,200, 100);
        mainCanvas.addElement(rectElem);
        rectElem.IsSelected.set(true);
    }
    private void buttonAddTextOnActionHandler(ActionEvent e){
        var textElem = new CanvasText(vBoxProperties, "Text");
        mainCanvas.addElement(textElem);
        textElem.IsSelected.set(true);
    }

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}