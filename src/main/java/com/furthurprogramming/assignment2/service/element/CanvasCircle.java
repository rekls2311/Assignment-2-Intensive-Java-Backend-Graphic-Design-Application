package com.furthurprogramming.assignment2.service.element;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CanvasCircle extends CanvasShape {

    public CanvasCircle(double radius){
        super(new Circle(radius));

        setBackgroundColor(Color.DEEPPINK);

    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setForegroundColor(color);
    }

    @Override
    public void updateTransform(double x, double y, double width, double height) {
        System.out.println(x + " " + y +  " " + width + " " + height);

        var circle = (Circle)shape;

        var cx = x + width / 2;
        var cy = y + height / 2;

        var r = (width + height) / 4;

        //circle.setCenterX(cx);
        //circle.setCenterY(cy);

        circle.setRadius(r);
    }
}

