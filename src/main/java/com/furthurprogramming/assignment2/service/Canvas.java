package com.furthurprogramming.assignment2.service;

import com.furthurprogramming.assignment2.service.element.CanvasElement;
import com.furthurprogramming.assignment2.service.element.ElementRectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

public class Canvas {

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
    }

    public void draw()
    {
        var gc = canvas.getGraphicsContext2D();
        for(var elem : elementList){
            elem.draw(gc);
        }
    }

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}
