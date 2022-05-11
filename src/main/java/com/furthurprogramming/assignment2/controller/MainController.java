package com.furthurprogramming.assignment2.controller;

import java.io.IOException;

import com.furthurprogramming.assignment2.service.canvas.Canvas;
import com.furthurprogramming.assignment2.service.element.CanvasCircle;
import com.furthurprogramming.assignment2.service.element.CanvasImage;
import com.furthurprogramming.assignment2.service.element.CanvasRectangle;
import com.furthurprogramming.assignment2.service.element.CanvasText;
import com.furthurprogramming.assignment2.util.ImageUtil;
import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.furthurprogramming.assignment2.Main;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

// Second scene controller
public class MainController {

    /////////////////////////////////////////////////////////////////////
    // FXML fields
    /////////////////////////////////////////////////////////////////////
    @FXML
    Pane paneCanvas;
    @FXML
    VBox vBoxProperties;
    @FXML
    Button buttonAddRectangle;
    @FXML
    Button buttonAddCircle;
    @FXML
    Button buttonAddText;
    @FXML
    Button buttonAddImage;
    @FXML
    Button buttonAddCanvas;
    @FXML
    MenuItem menuItemNewCanvas;
    @FXML
    MenuItem menuItemClearCanvas;
    @FXML
    MenuItem menuItemSaveAs;
    @FXML
    MenuItem menuItemDeleteElements;


    /////////////////////////////////////////////////////////////////////
    // Normal fields
    /////////////////////////////////////////////////////////////////////
    Canvas mainCanvas;


    /////////////////////////////////////////////////////////////////////
    // FXML methods
    /////////////////////////////////////////////////////////////////////
    @FXML
    private void initialize() throws IOException {
        Main.getStage().setResizable(true);

        //mainCanvas = new Canvas(paneCanvas, 1000, 800);

        paneCanvas.setStyle("-fx-background-color: #" + Color.DARKGRAY.toString().substring(2));

        setToolsEnabled(false);

        //mainCanvas.setBackgroundColor(Color.WHITE);
        createHandlers();
    }

    private void createHandlers()
    {
        buttonAddCircle.setOnAction(this::buttonAddCircleOnActionHandler);
        buttonAddRectangle.setOnAction(this::buttonAddRectangleOnActionHandler);
        buttonAddText.setOnAction(this::buttonAddTextOnActionHandler);
        buttonAddImage.setOnAction(this::buttonAddImageOnActionHandler);
        buttonAddCanvas.setOnAction(this::buttonAddCanvasOnActionHandler);
        menuItemNewCanvas.setOnAction(this::menuItemNewCanvasOnActionHandler);
        menuItemClearCanvas.setOnAction(this::menuItemClearCanvasOnActionHandler);
        menuItemDeleteElements.setOnAction(this::menuItemDeleteElementsOnActionHandler);
    }




    public MainController()
    {
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////
    private void buttonAddCircleOnActionHandler(ActionEvent e){
        var circleElem = new CanvasCircle(100);
        mainCanvas.addElement(circleElem);
        circleElem.IsSelected.set(true);
    }

    private void buttonAddRectangleOnActionHandler(ActionEvent e){
        var rectElem = new CanvasRectangle(200, 100);
        mainCanvas.addElement(rectElem);
        rectElem.IsSelected.set(true);
    }
    private void buttonAddTextOnActionHandler(ActionEvent e){
        var textElem = new CanvasText("Text");
        mainCanvas.addElement(textElem);
        textElem.IsSelected.set(true);
    }
    private void buttonAddImageOnActionHandler(ActionEvent actionEvent) {
        var imgFile = ImageUtil.chooseImageFile();

        if (imgFile == null || !imgFile.canRead()){
            return;
        }

        var imageElem = new CanvasImage(new Image(imgFile.getAbsolutePath()));
        mainCanvas.addElement(imageElem);
        imageElem.IsSelected.set(true);
    }
    private void buttonAddCanvasOnActionHandler(ActionEvent actionEvent) {
        createNewCanvas();
    }

    private void menuItemNewCanvasOnActionHandler(ActionEvent actionEvent) {
        createNewCanvas();
    }

    private void menuItemClearCanvasOnActionHandler(ActionEvent actionEvent) {
        if (mainCanvas != null){
            mainCanvas.clearCanvas();
        }
    }
    private void menuItemDeleteElementsOnActionHandler(ActionEvent actionEvent) {
        if (mainCanvas != null){
            mainCanvas.removeSelectedElements();
        }
    }


    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////
    private void setToolsEnabled(boolean enabled){
        buttonAddImage.setDisable(!enabled);
        buttonAddText.setDisable(!enabled);
        buttonAddRectangle.setDisable(!enabled);
        buttonAddCircle.setDisable(!enabled);
    }

    private void createNewCanvas(){

        CreateCanvasController createCanvasController = new CreateCanvasController();
        DialogPane dialogPane;
        try {
            dialogPane = (DialogPane)JavaFXUtil.loadFXML("createcanvas", createCanvasController);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("Create new canvas");

        var clickButton = dialog.showAndWait();

        if (clickButton.isPresent() && clickButton.get() == ButtonType.OK){
            var w = createCanvasController.getWidth();
            var h = createCanvasController.getHeight();

            if (w == 0 || h == 0)
                return;

            if (mainCanvas != null){
                mainCanvas.removeCanvas();
            }
            mainCanvas = new Canvas(paneCanvas, vBoxProperties, w, h);
            setToolsEnabled(true);
        }
    }
}