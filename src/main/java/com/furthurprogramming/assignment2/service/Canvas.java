package com.furthurprogramming.assignment2.service;

import com.furthurprogramming.assignment2.service.element.CanvasElement;
import com.furthurprogramming.assignment2.service.element.DragController;
import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Canvas {

    Pane rootPane;

    List<CanvasElement> elementList;

    DragController dragController;

    public Canvas(Pane root , int width, int height)
    {
        // Set panels
        this.rootPane = root;
        rootPane.setMaxSize(width, height);
        rootPane.setMinSize(width, height);
        rootPane.setPrefSize(width, height);

        // Create element list
        elementList = new ArrayList<>();

        dragController = new DragController(rootPane, MouseButton.SECONDARY);
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////
    public void resize(int newWidth, int newHeight)
    {
        rootPane.setMaxSize(newWidth, newHeight);
        rootPane.setMinSize(newWidth, newHeight);
        rootPane.setPrefSize(newWidth, newHeight);
    }

    public void setBackgroundColor(Color color)
    {
        rootPane.setStyle("-fx-background-color: #" + color.toString().substring(2));
    }

    public void addElement(CanvasElement elem)
    {
        elementList.add(elem);
        var nodeObject = elem.getNodeObject();
        rootPane.getChildren().add(nodeObject);
    }

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}
