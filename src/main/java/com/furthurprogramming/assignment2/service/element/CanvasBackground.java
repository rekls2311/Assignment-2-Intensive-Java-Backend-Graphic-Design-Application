package com.furthurprogramming.assignment2.service.element;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CanvasBackground extends CanvasShape{

    public CanvasBackground(double w, double h)  {
        super(new Rectangle(w, h));
        IsTransformable = false;
    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }
}
