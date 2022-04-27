package com.furthurprogramming.assignment2.service.element;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public abstract class CanvasShape extends CanvasElement{
    protected Shape shape;

    public CanvasShape(Pane propertyPane, String viewName, Object viewController) throws IOException {
        super(propertyPane, viewName,  viewController);


    }

    @Override
    public Node getNodeObject(){
        return shape;
    }

    @Override
    public CanvasElement setRotation(double degree)
    {
        shape.setRotate(degree);
        return this;
    }

    public void setBackgroundColor(){

    }

    public void setForegroundColor(){

    }

    public void setBorder() {

    }
}
