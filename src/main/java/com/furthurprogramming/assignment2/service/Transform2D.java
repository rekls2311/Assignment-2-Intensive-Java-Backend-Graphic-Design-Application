package com.furthurprogramming.assignment2.service;

import javafx.geometry.Point2D;
import javafx.scene.transform.Affine;
import org.jetbrains.annotations.NotNull;

public class Transform2D extends Affine {

    private Point2D position;

    // The root is at top-left corner of the shape
    private Point2D anchor;
    private double rotationDegree;
    private Point2D scale;

    public Transform2D()
    {
         position = Point2D.ZERO;
         anchor = Point2D.ZERO;
         rotationDegree = 0;
         scale = new Point2D(1, 1);
    }

    // Set
    public void setAnchor(Point2D anchor) {
        this.anchor = anchor;
    }

    public void setAnchor(double x, double y)
    {
        setAnchor(new Point2D(x, y));
    }

    public void setPosition(@NotNull Point2D newPos) {
        prependTranslation(-position.getX(), -position.getY());
        prependTranslation(newPos.getX(), newPos.getY());
        position = newPos;
    }

    public void setPosition(double x, double y)
    {
        setPosition(new Point2D(x, y));
    }

    public void setRotatioṇ(@NotNull double degree)
    {
        double dx = position.getX() + anchor.getX();
        double dy = position.getY() + anchor.getY();
        prependTranslation(-dx, -dy);
        prependRotation(-rotationDegree);
        rotationDegree = degree;
        prependRotation(rotationDegree);
        prependTranslation(dx, dy);
    }

    // Get
    public double getRotationDegree() {
        return rotationDegree;
    }

    public Point2D getAnchor() {
        return anchor;
    }

    public Point2D getPosition() {
        return position;
    }

    public Point2D getScale() {
        return scale;
    }
}
