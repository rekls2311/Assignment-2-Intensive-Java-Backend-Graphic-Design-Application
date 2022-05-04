package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasCirclePropertyController;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CanvasCircle extends CanvasShape {
    protected Circle circle;

    CanvasCirclePropertyController viewController;

    public CanvasCircle(Pane propertyPane, double radius){
        super(propertyPane,
                "circle_property",
                new CanvasCirclePropertyController(),
                new Circle(radius));

        viewController = (CanvasCirclePropertyController)getPropertyViewController();
        circle = (Circle) getNodeObject();

        viewController.colorPickerBackGroundCircle.setOnAction(actionEvent ->{
            setForegroundColor(viewController.colorPickerBackGroundCircle.getValue());
        });
    }
}
