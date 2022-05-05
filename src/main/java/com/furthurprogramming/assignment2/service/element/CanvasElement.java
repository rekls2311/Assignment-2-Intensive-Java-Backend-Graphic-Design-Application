package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.service.Canvas;
import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract class CanvasElement {
    private final Parent fxmlProperties;

    private final Pane propertyPane;

    private Object propertyViewController;

    private Canvas canvas;

    public BooleanProperty IsSelected;

    /////////////////////////////////////////////////////////////////////
    // Abstract methods
    /////////////////////////////////////////////////////////////////////
    // This is overridden from subclasses to draw itself
    public abstract Node getNodeObject();
    public abstract boolean containsPoint(Point2D point);

    public CanvasElement(Pane propertyPane, String propertyViewName, Object propertyViewController)  {
        try {
            fxmlProperties = JavaFXUtil.loadFXML("element_property/" + propertyViewName, propertyViewController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IsSelected = new SimpleBooleanProperty();

        IsSelected.addListener((observable, oldVal, newVal) -> {
            if (newVal){
                select();
            }
            else {
                deselect();
            }
        });

        this.propertyPane = propertyPane;
        this.propertyViewController = propertyViewController;
    }

    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    public Object getPropertyViewController()
    {
        return propertyViewController;
    }

    public Pane getPropertyPane(){
        return propertyPane;
    }

    public Parent getView(){
        return fxmlProperties;
    }

    /////////////////////////////////////////////////////////////////////
    // Public methods
    /////////////////////////////////////////////////////////////////////

    // Occur when user click to select the element
    protected void select() {
        if (!propertyPane.getChildren().contains(fxmlProperties)) {
            propertyPane.getChildren().add(fxmlProperties);
        }
    }

    protected void deselect() {
        propertyPane.getChildren().remove(fxmlProperties);
    }



    /////////////////////////////////////////////////////////////////////
    // Private methods
    /////////////////////////////////////////////////////////////////////
}

