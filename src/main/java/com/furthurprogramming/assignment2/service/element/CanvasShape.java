package com.furthurprogramming.assignment2.service.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;

public abstract class CanvasShape extends CanvasElement{
    private Shape shape;
    private DragController dragController;


    public CanvasShape(Pane propertyPane, String viewName, Object viewController, Shape shape)  {
        super(propertyPane, viewName,  viewController);
        this.shape = shape;

        dragController = new DragController(shape);

        createHandlers();
    }

    private void createHandlers(){
        this.shape.setOnMousePressed(this::shapeOnMousePressedHandler);
        this.shape.setOnMouseClicked(this::shapeOnMouseClickedHandler);
    }

    private void shapeOnMousePressedHandler(MouseEvent e){
        IsSelected.setValue(true);
    }
    private void shapeOnMouseClickedHandler(MouseEvent e){

    }


    @Override
    public Node getNodeObject(){
        return shape;
    }

    @Override
    public boolean containsPoint(Point2D point) {
        return shape.contains(point);
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

    public void setBackgroundColor(){

    }

    public void setForegroundColor(Color color){
        shape.setFill(color);
    }

    public void setBorder() {

    }
}
