package com.furthurprogramming.assignment2.service.element;

import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasRectangle extends CanvasShape {

    public CanvasRectangle(Pane propertyPane, double v1, double v2)  {
        super(propertyPane, new Rectangle(v1, v2));

    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return (Rectangle)shape;
    }

    @Override
    public void updateTransform(Rectangle bounds) {
        var rect = (Rectangle)shape;

//        System.out.println(bounds);

        rect.setX(bounds.getX());
        rect.setY(bounds.getY());
        rect.setWidth(bounds.getWidth());
        rect.setHeight(bounds.getHeight());
    }
}
