package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasShapePropertyController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CanvasCircle extends CanvasShape {

    public CanvasCircle(Pane propertyPane, double radius){
        super(propertyPane, new Circle(radius));

    }


    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }
}
