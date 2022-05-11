package com.furthurprogramming.assignment2.service.element;

import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasRectangle extends CanvasShape {

    public CanvasRectangle(double v1, double v2)  {
        super(new Rectangle(v1, v2));

    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        Rectangle bounds = new Rectangle();
        var rect = (Rectangle)shape;

        bounds.setRotate(rect.getRotate());
        bounds.setX(rect.getX());
        bounds.setY(rect.getY());
        bounds.setWidth(rect.getWidth());
        bounds.setHeight(rect.getHeight());

        return bounds;
    }

    @Override
    public void updateTransform(Rectangle bounds) {
        var rect = (Rectangle)shape;

        rect.setX(bounds.getX());
        rect.setY(bounds.getY());
        rect.setWidth(bounds.getWidth());
        rect.setHeight(bounds.getHeight());
        rect.setRotate(bounds.getRotate());
    }
}
