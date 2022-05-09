package com.furthurprogramming.assignment2.controller.element;

import com.furthurprogramming.assignment2.service.canvas.Canvas;
import com.furthurprogramming.assignment2.service.element.CanvasImage;
import com.furthurprogramming.assignment2.util.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class CanvasImagePropertyController {
    @FXML
    Button buttonChangePathImage;

    CanvasImage canvasImage;
    @FXML
    private void initialize (){
        buttonChangePathImage.setOnAction(this::buttonChangePathImageOnActionHandler);
    }

    public CanvasImagePropertyController(CanvasImage canvasImage){
        this.canvasImage = canvasImage;
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////

    private void buttonChangePathImageOnActionHandler(ActionEvent actionEvent) {
        var file = ImageUtil.chooseImageFile();

        if (file == null)
            return;
        Image img = new Image(file.getAbsolutePath());

        canvasImage.setImage(img);
    }
}

