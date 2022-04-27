package com.furthurprogramming.assignment2.service.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public abstract class CanvasShape extends CanvasElement{
    private Shape shape;
    private DragController dragController;


    public CanvasShape(Pane propertyPane, String viewName, Object viewController, Shape shape)  {
        super(propertyPane, viewName,  viewController);
        this.shape = shape;

        dragController = new DragController(shape);

        this.shape.setOnMousePressed(mouseEvent -> {select();});
    }

    @Override
    public Node getNodeObject(){
        return shape;
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

    public void setForegroundColor(){

    }

    public void setBorder() {

    }
}
