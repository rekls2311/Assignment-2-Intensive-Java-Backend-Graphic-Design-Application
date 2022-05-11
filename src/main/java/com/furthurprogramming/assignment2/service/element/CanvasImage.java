package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasImagePropertyController;
import com.furthurprogramming.assignment2.controller.element.CanvasShapePropertyController;
import com.furthurprogramming.assignment2.controller.element.CanvasTextPropertyController;
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

    public Rectangle getBoundingRectangle() {
        Rectangle rect = new Rectangle();
        var img = (ImageView)getMainNode();
        var bounds = img.getBoundsInLocal();


        rect.setX(bounds.getMinX());
        rect.setY(bounds.getMinY());
        rect.setWidth(bounds.getWidth());
        rect.setHeight(bounds.getHeight());

        return rect;
    }

    @Override
    public void updateTransform(Rectangle bounds) {
        var img = (ImageView)getMainNode();

        img.setX(bounds.getX());
        img.setY(bounds.getY());
        img.setFitWidth(bounds.getWidth());
        img.setFitHeight(bounds.getHeight());
        img.setRotate(bounds.getRotate());
    }
}
