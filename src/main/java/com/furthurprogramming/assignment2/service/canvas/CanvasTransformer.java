package com.furthurprogramming.assignment2.service.canvas;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;

import java.util.ArrayList;
import java.util.List;

public class CanvasTransformer extends AnimationTimer {

    private static final int ROTATE = 0;
    private static final int TOPLEFT = 1;
    private static final int TOPRIGHT= 2;
    private static final int BOTLEFT = 3;
    private static final int BOTRIGHT = 4;

    // rotate, topleft, topright, botright, botleft
    List<Circle> controlPoints;
    Group controlGroup;
    List<CanvasDragger> dragControllers;
    ICanvasTransformable transformable;

    CanvasDragger groupDragController;

    Group group;

    Boolean isTransforming = false;

    public CanvasTransformer(ICanvasTransformable transformable)
    {
        this.transformable = transformable;

        group = transformable.getParentGroup();
        controlGroup = new Group();

        controlPoints = new ArrayList<>(5);
        dragControllers = new ArrayList<>(5);

        groupDragController = new CanvasDragger(transformable.getMainNode());
        groupDragController.setOnDragging(this::mainNodeOnDraggedHandler);
        groupDragController.disable();

        for(int i = 0; i < 5; ++i) {
            var newControl = new Circle(10);

            controlGroup.getChildren().add(newControl);
            controlPoints.add(newControl);
            dragControllers.add(new CanvasDragger(newControl));
        }

        controlPoints.get(ROTATE   ).setOnMouseDragged(this::controlPointRotateOnMouseDraggedHandler);
        controlPoints.get(TOPLEFT  ).setOnMouseDragged(this::controlPointTopLeftOnMouseDraggedHandler);
        controlPoints.get(TOPRIGHT ).setOnMouseDragged(this::controlPointTopRightOnMouseDraggedHandler);
        controlPoints.get(BOTRIGHT ).setOnMouseDragged(this::controlPointBotRightOnMouseDraggedHandler);
        controlPoints.get(BOTLEFT  ).setOnMouseDragged(this::controlPointBotLeftOnMouseDraggedHandler);

        start();
    }

    @Override
    public void handle(long now) {
        refreshControlPoints();
    }

    public Boolean isTransforming(){
        return this.isTransforming;
    }

    public void startTransform(){
        isTransforming = true;

        refreshControlPoints();

        group.getChildren().add(controlGroup);
        groupDragController.enable();
    }

    public void stopTransform(){
        group.getChildren().remove(controlGroup);

        isTransforming = false;

        groupDragController.disable();
    }

    private void refreshControlPoints(){
        var bounds = transformable.getBoundingRectangle();
        var w = bounds.getWidth();
        var h = bounds.getHeight();

        controlPoints.get(ROTATE  ).setLayoutX(w / 2);
        controlPoints.get(TOPLEFT ).setLayoutX(0);
        controlPoints.get(TOPRIGHT).setLayoutX(w);
        controlPoints.get(BOTRIGHT).setLayoutX(w);
        controlPoints.get(BOTLEFT ).setLayoutX(0);
        controlPoints.get(ROTATE  ).setLayoutY(0);
        controlPoints.get(TOPLEFT ).setLayoutY(0);
        controlPoints.get(TOPRIGHT).setLayoutY(0);
        controlPoints.get(BOTRIGHT).setLayoutY(h);
        controlPoints.get(BOTLEFT ).setLayoutY(h);

        for(var controlPoint : controlPoints){
            controlPoint.setLayoutX(controlPoint.getLayoutX() + bounds.getX());
            controlPoint.setLayoutY(controlPoint.getLayoutY() + bounds.getY());
        }

    }

    private void controlPointRotateOnMouseDraggedHandler(MouseEvent mouseEvent){
        var newBounds = transformable.getBoundingRectangle();

        double cx = newBounds.getX() + newBounds.getWidth() / 2;
        double cy = newBounds.getY() + newBounds.getHeight() / 2;

        double x = controlPoints.get(ROTATE).getLayoutX() - cx;
        double y = controlPoints.get(ROTATE).getLayoutY() - cy;


        if (y == 0)
            return;

        double angle = Math.toDegrees(Math.atan(-x / y));

        newBounds.setRotate(angle);

        //transformable.updateTransform(newBounds);
        //refreshControlPoints();
    }
    private void controlPointTopLeftOnMouseDraggedHandler(MouseEvent mouseEvent){
        var newBounds = transformable.getBoundingRectangle();

        newBounds.setWidth(Math.abs(controlPoints.get(TOPRIGHT).getLayoutX() - controlPoints.get(TOPLEFT).getLayoutX()));
        newBounds.setHeight(Math.abs(controlPoints.get(TOPLEFT).getLayoutY() - controlPoints.get(BOTLEFT).getLayoutY()));
        newBounds.setY(controlPoints.get(TOPLEFT).getLayoutY());
        newBounds.setX(controlPoints.get(TOPLEFT).getLayoutX());

        transformable.updateTransform(newBounds);
    }
    private void controlPointTopRightOnMouseDraggedHandler(MouseEvent mouseEvent){
        var newBounds = transformable.getBoundingRectangle();

        newBounds.setWidth(Math.abs(controlPoints.get(TOPRIGHT).getLayoutX() - controlPoints.get(TOPLEFT).getLayoutX()));
        newBounds.setHeight(Math.abs(controlPoints.get(TOPRIGHT).getLayoutY() - controlPoints.get(BOTRIGHT).getLayoutY()));
        newBounds.setY(controlPoints.get(TOPRIGHT).getLayoutY());

        transformable.updateTransform(newBounds);
    }
    private void controlPointBotRightOnMouseDraggedHandler(MouseEvent mouseEvent){
        var newBounds = transformable.getBoundingRectangle();

        newBounds.setHeight(Math.abs(controlPoints.get(BOTRIGHT).getLayoutY() - controlPoints.get(TOPRIGHT).getLayoutY()));
        newBounds.setWidth(Math.abs(controlPoints.get(BOTRIGHT).getLayoutX() - controlPoints.get(BOTLEFT).getLayoutX()));

        transformable.updateTransform(newBounds);
    }
    private void controlPointBotLeftOnMouseDraggedHandler(MouseEvent mouseEvent){
        var newBounds = transformable.getBoundingRectangle();

        newBounds.setWidth(Math.abs(controlPoints.get(BOTLEFT).getLayoutX() - controlPoints.get(BOTRIGHT).getLayoutX()));
        newBounds.setHeight(Math.abs(controlPoints.get(BOTLEFT).getLayoutY() - controlPoints.get(TOPLEFT).getLayoutY()));
        newBounds.setX(controlPoints.get(BOTLEFT).getLayoutX());

        transformable.updateTransform(newBounds);
    }

    private void mainNodeOnDraggedHandler(DragEvent dragEvent){
        controlGroup.setLayoutX(controlGroup.getLayoutX() + dragEvent.dx);
        controlGroup.setLayoutY(controlGroup.getLayoutY() + dragEvent.dy);
    }
}

