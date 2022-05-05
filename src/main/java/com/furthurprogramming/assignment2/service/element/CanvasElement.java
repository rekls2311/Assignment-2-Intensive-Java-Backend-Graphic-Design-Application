package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.service.canvas.Canvas;
import com.furthurprogramming.assignment2.service.canvas.CanvasTransformer;
import com.furthurprogramming.assignment2.service.canvas.ICanvasTransformable;
import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class CanvasElement implements ICanvasTransformable {
    private final Parent fxmlProperties;
    private final Pane propertyPane;
    private Object propertyViewController;
    private Canvas canvas;
    private Group group;
    private Node node;
    public BooleanProperty IsSelected;
    private CanvasTransformer transformController;

    /////////////////////////////////////////////////////////////////////
    // Abstract methods
    /////////////////////////////////////////////////////////////////////
    // This is overridden from subclasses to draw itself

    public CanvasElement(@NotNull Pane propertyPane,
                         @NotNull String propertyViewName,
                         @NotNull Object propertyViewController,
                         @NotNull Node nodeObject)  {
        this.node = nodeObject;

        this.group = new Group();
        this.group.getChildren().add(nodeObject);

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
        this.transformController = new CanvasTransformer(this);
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
    public Group getGroup()
    {
        return group;
    }

    // Occur when user click to select the element
    protected void select() {
        if (!propertyPane.getChildren().contains(fxmlProperties)) {
            propertyPane.getChildren().add(fxmlProperties);
        }

        transformController.startTransform();
    }

    protected void deselect() {
        propertyPane.getChildren().remove(fxmlProperties);

        transformController.stopTransform();
    }

    public Group getParentGroup(){
        return group;
    }
    public Rectangle getBoundingRectangle() {
        return new Rectangle();
    }
    public void updateTransform(Rectangle bounds) {

    }

    public Node getMainNode(){
        return node;
    }
    /////////////////////////////////////////////////////////////////////
    // Private methods
    /////////////////////////////////////////////////////////////////////
}

