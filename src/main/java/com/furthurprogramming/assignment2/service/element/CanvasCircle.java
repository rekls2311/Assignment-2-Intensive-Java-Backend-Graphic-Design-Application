package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasShapePropertyController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CanvasCircle extends CanvasShape {

    public CanvasCircle(double radius){
        super(new Circle(radius));

    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }
}
