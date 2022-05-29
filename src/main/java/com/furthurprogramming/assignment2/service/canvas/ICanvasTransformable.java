package com.furthurprogramming.assignment2.service.canvas;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public interface ICanvasTransformable {
    Bounds getBoundingRectangle(); // return a bounding rectangle
    void updateTransform(double x, double y, double width, double height); // fire each time a control moves
    Group getParentGroup(); // return a parent for controls to attach to
    Node getMainNode(); // return the main child node of the group
}
