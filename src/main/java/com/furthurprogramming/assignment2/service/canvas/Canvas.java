package com.furthurprogramming.assignment2.service.canvas;

import com.furthurprogramming.assignment2.service.element.CanvasBackground;
import com.furthurprogramming.assignment2.service.element.CanvasElement;
import com.furthurprogramming.assignment2.service.element.CanvasRectangle;
import javafx.beans.Observable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.tree.FixedHeightLayoutCache;
import java.util.List;
import java.util.ArrayList;

public class Canvas {

    Pane rootPane;
    Pane canvasPane;
    Pane propertyPane;
    List<CanvasElement> elementList;

    CanvasBackground background;

    public Canvas(Pane root, Pane propertyPane, int width, int height)
    {
        // Set panels
        this.rootPane = root;
        this.propertyPane = propertyPane;

        canvasPane = new Pane();
        this.rootPane.getChildren().add(canvasPane);

        canvasPane.setMaxSize(width, height);
        canvasPane.setMinSize(width, height);
        canvasPane.setPrefSize(width, height);


        // Create element list
        elementList = new ArrayList<>();

        createHandlers();

        this.background = new CanvasBackground(width, height);
        addElement(this.background);
        this.background.setLayoutX(width / 2);
        this.background.setLayoutY(height / 2);

        setBackgroundColor(Color.WHITE);
    }

    public void removeCanvas(){
        for(var elem : elementList){
            elem.deselect();
        }

        rootPane.getChildren().remove(canvasPane);
    }

    void createHandlers()
    {
        canvasPane.setOnMousePressed(this::canvasPaneOnMousePressedHandler);
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////

    private void canvasPaneOnMousePressedHandler(MouseEvent e)
    {
//        for(var element : elementList){
//            if (!element.containsPoint(new Point2D(
//                    e.getX() - element.getGroup().getLayoutX(),
//                    e.getY() - element.getGroup().getLayoutY())))
//                element.IsSelected.setValue(false);
//        }
    }

    private void elementIsSelectedListener(Observable observable, boolean oldVal, boolean newVal) {
        if (newVal) {
            for (var element : elementList) {
                if (observable != element.IsSelected)
                    element.IsSelected.setValue(false);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////
    public void resize(int newWidth, int newHeight)
    {
        canvasPane.setMaxSize(newWidth, newHeight);
        canvasPane.setMinSize(newWidth, newHeight);
        canvasPane.setPrefSize(newWidth, newHeight);
    }

    public void setBackgroundColor(Color color)
    {
        background.setBackgroundColor(color);
    }

    public void addElement(CanvasElement elem)
    {
        elem.setPropertyPane(propertyPane);

        elem.setCanvas(this);
        elem.IsSelected.addListener(this::elementIsSelectedListener);

        var nodeObject = elem.getGroup();
        canvasPane.getChildren().add(nodeObject);

        nodeObject.setLayoutX(canvasPane.getWidth() / 2 - nodeObject.getLayoutBounds().getCenterX());
        nodeObject.setLayoutY(canvasPane.getHeight() / 2 - nodeObject.getLayoutBounds().getCenterY());

        elementList.add(elem);
    }

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

}
