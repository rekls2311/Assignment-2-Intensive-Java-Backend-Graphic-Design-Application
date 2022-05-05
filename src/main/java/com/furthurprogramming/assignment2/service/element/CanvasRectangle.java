package com.furthurprogramming.assignment2.service.element;

import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasRectangle extends CanvasShape {

    protected Rectangle rectangle;


    public CanvasRectangle(Pane propertyPane, double v1, double v2)  {
        super(propertyPane, new Rectangle(v1, v2));

        rectangle = (Rectangle)getNodeObject();

    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }
}
