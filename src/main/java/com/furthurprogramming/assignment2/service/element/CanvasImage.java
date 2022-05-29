package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasImagePropertyController;
import com.furthurprogramming.assignment2.controller.element.CanvasShapePropertyController;
import com.furthurprogramming.assignment2.controller.element.CanvasTextPropertyController;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public class CanvasImage extends CanvasElement{

    public CanvasImage(Image img) {
        super(new ImageView());

        this.loadFxml("image_property", new CanvasImagePropertyController(this));

        setImage(img);
    }

    public void setImage(Image img){
        var imView = (ImageView)getMainNode();

        imView.setImage(img);
    }

    @Override
    public void updateTransform(double x, double y, double width, double height) {
        var img = (ImageView)getMainNode();

        System.out.println("%.2f, %.2f, %.2f, %.2f".formatted(x, y, width, height));

        img.setX(x);
        img.setY(y);
        img.setFitWidth(width);
        img.setFitHeight(height);
    }
}
