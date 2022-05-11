package com.furthurprogramming.assignment2.controller;

import com.furthurprogramming.assignment2.service.canvas.Canvas;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class CreateCanvasController {

    @FXML
    TextField textFieldWidth;

    @FXML
    TextField textFieldHeight;

    @FXML
    private void initialize(){

        textFieldHeight.setText("400");
        textFieldWidth.setText("400");

        // force the field to be numeric only
        textFieldWidth.textProperty().addListener(this::textFieldWidthTextListener);
        textFieldHeight.textProperty().addListener(this::textFieldHeightTextListener);
    }

    public int getWidth(){
        if (textFieldWidth == null)
            return 0;

        var str = textFieldWidth.getText();

        int w = 0;

        try {
            w = Integer.parseInt(str);
        }
        catch (NumberFormatException ignored){

        }

        return w;
    }

    public int getHeight(){

        if (textFieldHeight == null){
            return 0;
        }

        var str = textFieldHeight.getText();

        int h = 0;

        try {
            h = Integer.parseInt(str);
        }
        catch (NumberFormatException ignored){

        }

        return h;
    }
    private void textFieldWidthTextListener(Observable observable, String oldText, String newText){
        textFieldWidth.setText(digitOnly(newText));
    }

    private void textFieldHeightTextListener(Observable observable, String oldText, String newText){
        textFieldHeight.setText(digitOnly(newText));
    }

    private String digitOnly(String str){
        return str.replaceAll("[^\\d]", "");
    }
}
