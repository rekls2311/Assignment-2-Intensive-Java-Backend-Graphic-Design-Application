package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.io.IOException;

public abstract class CanvasElement {
    private final Parent fxmlProperties;
    private final Pane propertyPane;

    protected Shape shape;

    /////////////////////////////////////////////////////////////////////
    // Abstract methods
    /////////////////////////////////////////////////////////////////////
    // Draw to canvas
    public abstract void draw(GraphicsContext gc);

    public CanvasElement(Pane propertyPane, String viewName) throws IOException {
        this.propertyPane = propertyPane;
        fxmlProperties = JavaFXUtil.loadFXML("element_property/" + viewName);
    }

    /////////////////////////////////////////////////////////////////////
    // Public methods
    /////////////////////////////////////////////////////////////////////
    // Occur when user click to select the element
    public void select(){
        propertyPane.getChildren().add(fxmlProperties);
    }

    public void deselect(){
        propertyPane.getChildren().remove(fxmlProperties);
    }


    /////////////////////////////////////////////////////////////////////
    // Private methods
    /////////////////////////////////////////////////////////////////////
}
