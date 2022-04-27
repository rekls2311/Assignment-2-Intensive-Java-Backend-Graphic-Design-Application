package com.furthurprogramming.assignment2.service;

import com.furthurprogramming.assignment2.service.element.CanvasElement;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Canvas extends AnimationTimer {

    Pane rootPane;
    javafx.scene.canvas.Canvas canvas;

    List<CanvasElement> elementList;

    public Canvas(Pane root , int width, int height)
    {
        // Set panels
        this.rootPane = root;
        rootPane.setMaxSize(width, height);
        rootPane.setMinSize(width, height);
        rootPane.setPrefSize(width, height);

        // Create canvas
        canvas = new javafx.scene.canvas.Canvas(width, height);
        root.getChildren().add(canvas);

        // Create element list
        elementList = new ArrayList<>();

        this.start();
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

        canvas.setWidth(newWidth);
        canvas.setHeight(newHeight);
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


    @Override
    public void handle(long l) {
        //draw();
    }

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

    private void draw()
    {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(var elem : elementList){
            elem.draw(gc);
        }
    }
}
