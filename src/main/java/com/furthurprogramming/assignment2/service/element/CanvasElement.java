package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract class CanvasElement {
    private final Parent fxmlProperties;

    private final Pane propertyPane;

    private Object propertyViewController;

    /////////////////////////////////////////////////////////////////////
    // Abstract methods
    /////////////////////////////////////////////////////////////////////
    // This is overridden from subclasses to draw itself
    public abstract Node getNodeObject();

    public CanvasElement(Pane propertyPane, String propertyViewName, Object propertyViewController)  {
        try {
            fxmlProperties = JavaFXUtil.loadFXML("element_property/" + propertyViewName, propertyViewController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.propertyPane = propertyPane;
        this.propertyViewController = propertyViewController;
    }

    public Object getPropertyViewController()
    {
        return propertyViewController;
    }

    /////////////////////////////////////////////////////////////////////
    // Public methods
    /////////////////////////////////////////////////////////////////////

    // Occur when user click to select the element
    public void select() {
        if (!propertyPane.getChildren().contains(fxmlProperties)) {
            propertyPane.getChildren().add(fxmlProperties);
        }
    }

    public void deselect() {
        propertyPane.getChildren().remove(fxmlProperties);
    }


    /////////////////////////////////////////////////////////////////////
    // Private methods
    /////////////////////////////////////////////////////////////////////
}

