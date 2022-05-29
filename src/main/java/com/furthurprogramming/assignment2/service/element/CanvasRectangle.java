package com.furthurprogramming.assignment2.service.element;

import javafx.beans.Observable;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasRectangle extends CanvasShape {

    public CanvasRectangle(double v1, double v2)  {
        super(new Rectangle(v1, v2));

        setBackgroundColor(Color.DARKGREEN);

    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }

    @Override
    public void updateTransform(double x, double y, double width, double height) {

        var rect = (Rectangle)shape;

        rect.setX(x);
        rect.setY(y);
        rect.setWidth(width);
        rect.setHeight(height);
    }
}
