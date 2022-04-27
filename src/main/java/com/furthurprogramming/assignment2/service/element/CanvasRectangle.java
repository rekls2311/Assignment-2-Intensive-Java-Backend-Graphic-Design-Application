package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasRectanglePropertyController;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
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

    CanvasRectanglePropertyController viewController;

    public CanvasRectangle(Pane propertyPane, double v1, double v2) throws IOException {
        super(propertyPane, "rectangle_property", new CanvasRectanglePropertyController());

        viewController = (CanvasRectanglePropertyController) getPropertyViewController();

        var rect = new Rectangle(v1, v2);
        shape = rect;

        setAnchor(new Point2D(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2));

        viewController.sliderAngle.valueProperty().addListener(this::sliderAngleValueChangedHandler);
    }

    private void sliderAngleValueChangedHandler(Observable observable, Number oldVal, Number newVal) {
        setRotation((double)newVal);
        System.out.println(newVal);
    }


    @Override
    public void drawSelf(GraphicsContext gc) {

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
