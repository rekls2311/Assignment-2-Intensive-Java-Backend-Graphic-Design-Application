package com.furthurprogramming.assignment2.controller.element;

import com.furthurprogramming.assignment2.service.element.CanvasText;
import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.List;

public class CanvasTextPropertyController {
    @FXML
    public TextField textFieldText;
    @FXML
    public ComboBox<String> comboBoxFont;
    @FXML
    public Spinner<Integer> spinnerFontSize;
    @FXML
    public ComboBox<String> comboBoxWeight;
    @FXML
    public ComboBox<String> comboBoxPosture;
    @FXML
    public ColorPicker colorPickerTextColour;
    @FXML
    public Button buttonAlignLeft;
    @FXML
    public Button buttonAlignCenter;
    @FXML
    public Button buttonAlignRight;

    @FXML
    private void initialize(){

        // Combo box font
        comboBoxFont.setItems(fontList);
        comboBoxFont.setValue(Font.getDefault().getFamily());

        // Spinner font size
        spinnerFontSize.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 13, 1));

        // Combo box weight
        comboBoxWeight.setItems(fontWeightList);
        //combo box posture
        comboBoxPosture.setItems(fontPostureList);

        refreshFontAttributes();

        createHandlers();
    }

    /////////////////////////////////////////////////////////////////////
    // Private fields
    /////////////////////////////////////////////////////////////////////

    ObservableList<String> fontList;
    ObservableList<String> fontWeightList;
    ObservableList<String> fontPostureList;

    CanvasText canvasText;

    public CanvasTextPropertyController(CanvasText canvasText){
        this.canvasText = canvasText;

        fontList = new SimpleListProperty<>(FXCollections.observableArrayList(Font.getFamilies()));
        fontWeightList = new SimpleListProperty<>(FXCollections.observableArrayList());
        fontPostureList = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    /////////////////////////////////////////////////////////////////////
    // Event handler
    ////////////////////////////////////////////////////////////////////
    public void createHandlers(){
        textFieldText.textProperty().addListener(this::textFieldTextListener);
        comboBoxFont.valueProperty().addListener(this::comboBoxFontListener);
        spinnerFontSize.valueProperty().addListener(this::spinnerFontSizeValueListener);
        comboBoxWeight.valueProperty().addListener(this::comboBoxWeightValueListener);
        comboBoxWeight.setOnShowing(this::comboBoxWeightOnShowingHandler);
        comboBoxPosture.setOnShowing(this::comboBoxPostureOnShowingHandler);
        comboBoxPosture.valueProperty().addListener(this::comboBoxPostureValueListener);
        colorPickerTextColour.valueProperty().addListener(this::colorPickerValueListener);
    }

    void textFieldTextListener(Observable observable, String oldValue, String newValue){
        canvasText.setText(newValue);
    }

    void comboBoxFontListener(Observable observable, String oldFamily, String newFamily){
        canvasText.setFamily(newFamily);
        refreshFontAttributes();
    }

    void spinnerFontSizeValueListener(Observable observable, double oldValue, double newValue) {
        canvasText.setSize(newValue);
    }

    void comboBoxWeightValueListener(Observable observable, String oldValue, String newValue){
        canvasText.setWeight(newValue);
    }

    void comboBoxPostureValueListener(Observable observable,String oldValue, String newValue){
        canvasText.setPosture(newValue);
    }

    void comboBoxWeightOnShowingHandler(Event ev){
        mergeListStable(fontWeightList, canvasText.getFontWeights());
    }
    void comboBoxPostureOnShowingHandler(Event eve){
        mergeListStable(fontPostureList, canvasText.getFontPostures());
    }
    void colorPickerValueListener(Observable observable, Color oldColor, Color newColor){
        canvasText.setForegroundColor(newColor);
    }

    void refreshFontAttributes(){
        fontWeightList.clear();
        fontWeightList.addAll(canvasText.getFontWeights());
        if (fontWeightList.size() > 0)
            comboBoxWeight.setValue(fontWeightList.get(0));
        fontPostureList.clear();
        fontPostureList.addAll(canvasText.getFontPostures());
        if (fontPostureList.size() > 0)
            comboBoxPosture.setValue(fontPostureList.get(0));
    }

    static void mergeListStable(List<String> mergeTo, List<String> mergeFrom){
        for (int i = 0; i < mergeTo.size(); i++){
            if (mergeFrom.contains(mergeTo.get(i)))
                continue;

            mergeTo.remove(i);
            i--;
        }

        for(var str : mergeFrom){
            if (mergeTo.contains(str))
                continue;

            mergeTo.add(str);
        }
    }
}
