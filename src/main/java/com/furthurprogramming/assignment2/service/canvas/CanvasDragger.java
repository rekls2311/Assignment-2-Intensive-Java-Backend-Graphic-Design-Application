package com.furthurprogramming.assignment2.service.canvas;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

class DragEvent extends Event {
    public double dx;
    public double dy;

    public DragEvent(EventType<? extends Event> eventType, double dx, double dy) {
        super(eventType);

        this.dx = dx;
        this.dy = dy;
    }
}

public class CanvasDragger {
    private final Node target;
    private Point2D mousePrevious; // Relative to the scene

    private EventHandler<MouseEvent> setAnchor;
    private EventHandler<MouseEvent> updatePositionOnDrag;
    private EventHandler<DragEvent> onDragging;

    MouseButton dragButton;

    Boolean enabled = false;

    public CanvasDragger(Node target, MouseButton dragButton) {
        this.target = target;
        this.dragButton = dragButton;

        this.mousePrevious = Point2D.ZERO;

        createHandlers();

        target.addEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
        target.addEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);

        enable();
    }

    public CanvasDragger(Node target)  {
        this(target, MouseButton.PRIMARY);
    }

    public void enable(){
        enabled = true;
    }

    public void disable(){
        enabled = false;
    }

    private void createHandlers() {

        setAnchor = event -> {
            if (event.getButton().equals(dragButton)) {
                mousePrevious = new Point2D(event.getSceneX(), event.getSceneY());
                System.out.println(" clicked");
            }
        };

        updatePositionOnDrag = event -> {
            if (enabled && event.getButton().equals(dragButton)) {
                double dx = event.getSceneX() - mousePrevious.getX();
                double dy = event.getSceneY() - mousePrevious.getY();

                mousePrevious = new Point2D(event.getSceneX(), event.getSceneY());

                target.setLayoutX(target.getLayoutX() + dx);
                target.setLayoutY(target.getLayoutY() + dy);

                if (onDragging != null)
                    onDragging.handle(new DragEvent(MouseEvent.MOUSE_DRAGGED, dx, dy));
            }
        };
    }

    public void setOnDragging(EventHandler<DragEvent> eventHandler) {
        onDragging = eventHandler;
    }
}