package com.furthurprogramming.assignment2.service.element;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

public class CanvasImage extends CanvasElement{

    public CanvasImage(@NotNull Pane propertyPane, @NotNull String propertyViewName, @NotNull Object propertyViewController, @NotNull Node nodeObject) {
        super(propertyPane, propertyViewName, propertyViewController, nodeObject);
    }
}
