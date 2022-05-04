package com.furthurprogramming.assignment2.service;

import com.furthurprogramming.assignment2.service.element.CanvasElement;
import com.furthurprogramming.assignment2.service.element.CanvasShape;
import com.furthurprogramming.assignment2.service.element.DragController;
import javafx.animation.AnimationTimer;
import javafx.beans.Observable;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Canvas {

    Pane rootPane;

    Pane canvasPane;

    List<CanvasElement> elementList;

    DragController dragController;

    public Canvas(Pane root , int width, int height)
    {
        // Set panels
        this.rootPane = root;

        canvasPane = new Pane();
        this.rootPane.getChildren().add(canvasPane);

        canvasPane.setMaxSize(width, height);
        canvasPane.setMinSize(width, height);
        canvasPane.setPrefSize(width, height);


        // Create element list
        elementList = new ArrayList<>();

        createHandlers();
    }

    void createHandlers()
    {
        canvasPane.setOnMousePressed(this::canvasPaneOnMousePressedHandler);
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////

    private void canvasPaneOnMousePressedHandler(MouseEvent e)
    {
        for(var element : elementList){
            if (!element.containsPoint(new Point2D(
                    e.getX() - element.getNodeObject().getLayoutX(),
                    e.getY() - element.getNodeObject().getLayoutY())))
                element.IsSelected.setValue(false);
        }
    }
    
    private void elementIsSelectedListener(Observable observable, boolean oldVal, boolean newVal) {
        if (newVal) {
            for (var element : elementList) {
                if (observable != element.IsSelected)
                    element.IsSelected.setValue(false);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////
    public void resize(int newWidth, int newHeight)
    {
        canvasPane.setMaxSize(newWidth, newHeight);
        canvasPane.setMinSize(newWidth, newHeight);
        canvasPane.setPrefSize(newWidth, newHeight);
    }

    public void setBackgroundColor(Color color)
    {
        canvasPane.setStyle("-fx-background-color: #" + color.toString().substring(2));
    }

    public void addElement(CanvasElement elem)
    {
        elem.setCanvas(this);
        elem.IsSelected.addListener(this::elementIsSelectedListener);

        var nodeObject = elem.getNodeObject();
        canvasPane.getChildren().add(nodeObject);

        nodeObject.setLayoutX(canvasPane.getWidth() / 2 - nodeObject.getLayoutBounds().getCenterX());
        nodeObject.setLayoutY(canvasPane.getHeight() / 2 - nodeObject.getLayoutBounds().getCenterY());

        elementList.add(elem);
    }

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}
