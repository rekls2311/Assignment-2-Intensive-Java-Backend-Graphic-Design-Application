package com.furthurprogramming.assignment2.service.element;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Transform;

import java.io.IOException;

public class ElementRectangle extends CanvasElement {
    public ElementRectangle(Pane propertyPane, double v1, double v2) throws IOException {
        super(propertyPane, "rectangle_property");

        shape = new Rectangle(v1, v2);
    }

    public void setPosition(double x, double y)
    {
        // @TODO: set position

    }

    @Override
    public void draw(GraphicsContext gc) {

        // Cast to rectangle
        Rectangle rect = (Rectangle)shape;

        gc.setFill(Color.BLACK);
        gc.fillRect(rect.getX(),
                rect.getY(),
                rect.getWidth(),
                rect.getHeight());
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
    }
}
