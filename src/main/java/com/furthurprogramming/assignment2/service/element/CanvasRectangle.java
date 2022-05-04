package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasRectanglePropertyController;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.EventDispatchChain;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class CanvasRectangle extends CanvasShape {

    protected Rectangle rectangle;


    CanvasRectanglePropertyController viewController;

    public CanvasRectangle(Pane propertyPane, double v1, double v2)  {
        super(propertyPane,
                "rectangle_property",
                new CanvasRectanglePropertyController(),
                new Rectangle(v1, v2));

        viewController = (CanvasRectanglePropertyController) getPropertyViewController();

        rectangle = (Rectangle)getNodeObject();

        viewController.sliderAngle.valueProperty().addListener(this::sliderAngleValueChangedHandler);

    }

    private void sliderAngleValueChangedHandler(Observable observable, Number oldVal, Number newVal) {
        setRotation((double)newVal);
    }
}
