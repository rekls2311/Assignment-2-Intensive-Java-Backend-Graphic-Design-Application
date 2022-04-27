package com.furthurprogramming.assignment2.service;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class Canvas {

    Pane rootPane;
    javafx.scene.canvas.Canvas canvas;

    public Canvas(Pane root, int width, int height)
    {
        this.rootPane = root;
        canvas = new javafx.scene.canvas.Canvas(width, height);
        root.getChildren().add(canvas);

        rootPane.setMaxSize(width, height);
        rootPane.setMinSize(width, height);
        rootPane.setPrefSize(width, height);

        drawShapes(canvas.getGraphicsContext2D());

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

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);


        Rectangle rect = new Rectangle(100, 100);
    }

}
