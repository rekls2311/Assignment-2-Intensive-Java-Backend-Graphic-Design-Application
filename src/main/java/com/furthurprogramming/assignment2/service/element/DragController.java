package com.furthurprogramming.assignment2.service.element;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DragController {
    private final Node target;
    private Point2D mouseOriginal; // Relative to the scene
    private Point2D targetOriginal; // Relative to the scene

    private EventHandler<MouseEvent> setAnchor;
    private EventHandler<MouseEvent> updatePositionOnDrag;
    private EventHandler<MouseEvent> commitPositionOnRelease;

    private final int ACTIVE = 1;
    private final int INACTIVE = 0;
    private int cycleStatus = INACTIVE;

    MouseButton dragButton;

    public DragController(Node target, MouseButton dragButton) {
        this.target = target;
        this.dragButton = dragButton;

        createHandlers();
        createEvents();
    }

    public DragController(Node target)  {
        this(target, MouseButton.PRIMARY);
    }

    private void createHandlers() {
        setAnchor = event -> {
            if (event.getButton().equals(dragButton)) {
                cycleStatus = ACTIVE;
                mouseOriginal = new Point2D(event.getSceneX(), event.getSceneY());
                targetOriginal = new Point2D(target.getLayoutX(), target.getLayoutY());
            }
        };
        updatePositionOnDrag = event -> {
            if (event.getButton().equals(dragButton) && cycleStatus != INACTIVE) {
                target.setTranslateX(event.getSceneX() / target.getScaleX() - mouseOriginal.getX());
                target.setTranslateY(event.getSceneY() / target.getScaleY() - mouseOriginal.getY());

                event.consume();
            }
        };
        commitPositionOnRelease = event -> {
            if (event.getButton().equals(dragButton) && cycleStatus != INACTIVE) {
                //commit changes to LayoutX and LayoutY
                target.setLayoutX(targetOriginal.getX() + event.getSceneX() - mouseOriginal.getX());
                target.setLayoutY(targetOriginal.getY() + event.getSceneY() - mouseOriginal.getY());
                //clear changes from TranslateX and TranslateY
                target.setTranslateX(0);
                target.setTranslateY(0);
            }
        };
    }

    private void createEvents(){

        target.addEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
        target.addEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
        target.addEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);

    }
}