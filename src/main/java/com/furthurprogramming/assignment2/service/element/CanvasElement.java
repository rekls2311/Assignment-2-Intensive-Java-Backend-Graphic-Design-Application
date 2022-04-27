package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.service.Transform2D;
import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.transform.NonInvertibleTransformException;

import java.io.IOException;

public abstract class CanvasElement {
    private final Parent fxmlProperties;

    private final Pane propertyPane;

    private Transform2D transform;

    private Object propertyViewController;

    /////////////////////////////////////////////////////////////////////
    // Abstract methods
    /////////////////////////////////////////////////////////////////////
    // This is overridden from subclasses to draw itself
    public abstract void drawSelf(GraphicsContext gc);
    public abstract Node getNodeObject();


    public CanvasElement(Pane propertyPane, String propertyViewName, Object propertyViewController) throws IOException {
        this.propertyPane = propertyPane;
        fxmlProperties = JavaFXUtil.loadFXML("element_property/" + propertyViewName, propertyViewController);
        this.propertyViewController = propertyViewController;
        transform = new Transform2D();
    }



    public Object getPropertyViewController()
    {
        return propertyViewController;
    }

    /////////////////////////////////////////////////////////////////////
    // Public methods
    /////////////////////////////////////////////////////////////////////

    // Draw to canvas
    public final void draw(GraphicsContext gc)
    {
        try {
            gc.transform(transform);
            drawSelf(gc);
            gc.transform(transform.createInverse());
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }


    // Occur when user click to select the element
    public void select() {
        propertyPane.getChildren().add(fxmlProperties);
    }

    public void deselect() {
        propertyPane.getChildren().remove(fxmlProperties);
    }

    // Transform
    public Transform2D getTransform() {
        return transform;
    }

    public CanvasElement setX(double x) {
        transform.setPosition(x, getY());
        return this;
    }

    public CanvasElement setY(double y){
        transform.setPosition(getX(), y);
        return this;
    }

    public CanvasElement setRotation(double degree)
    {
        transform.setRotatioṇ(degree);
        return this;
    }

    public CanvasElement setAnchor(Point2D anchor){
        transform.setAnchor(anchor);
        return this;
    }

    public CanvasElement setScale(double factor)
    {
        return this;
    }

    public double getX() {return transform.getTx();}
    public double getY() {return transform.getTy();}
    public double getRotation() {return transform.getRotationDegree();}
    public Point2D getScale() {return transform.getScale();}


    /////////////////////////////////////////////////////////////////////
    // Private methods
    /////////////////////////////////////////////////////////////////////
}

