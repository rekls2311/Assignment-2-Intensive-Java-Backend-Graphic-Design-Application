package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasShapePropertyController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;

public abstract class CanvasShape extends CanvasElement{
    protected Shape shape;
    CanvasShapePropertyController viewController;

    public CanvasShape(Pane propertyPane, Shape shape)  {
        super(propertyPane,
                shape);

        this.loadFxml("shape_property",
                new CanvasShapePropertyController());

        this.shape = shape;

        viewController = (CanvasShapePropertyController)getPropertyViewController();

        createHandlers();
    }

    private void createHandlers(){
        viewController.colorPickerBackGroundCircle.setOnAction(actionEvent ->{
            setBackgroundColor(viewController.colorPickerBackGroundCircle.getValue());
        });
    }

    public CanvasShape setRotation(double degree)
    {
        shape.setRotate(degree);
        return this;
    }

    public CanvasShape setX(double x){
        shape.setTranslateX(x);
        return this;
    }

    public CanvasShape setY(double y){
        shape.setTranslateY(y);
        return this;
    }

    public CanvasShape setScale(double factor)
    {
        return this;
    }

    public double getX() {return shape.getTranslateX();}
    public double getY() {return shape.getTranslateY();}
    public double getRotation() {return shape.getRotate();}
    public Point2D getScale() {return new Point2D(shape.getScaleX(), shape.getScaleY());}

    public void setBackgroundColor(Color color){
        shape.setStyle("-fx-background-color: #" + color.toString().substring(2));
    }

    public void setForegroundColor(Color color){
        shape.setFill(color);
    }

    public void setBorder() {

    }
}
