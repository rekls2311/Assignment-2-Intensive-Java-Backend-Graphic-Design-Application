package com.furthurprogramming.assignment2.service.element;

import com.furthurprogramming.assignment2.controller.element.CanvasTextPropertyController;
import com.furthurprogramming.assignment2.util.JavaFXUtil;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CanvasText extends CanvasShape {

    Parent textPropetyView;
    CanvasTextPropertyController viewController;
    Text text;

    public CanvasText(String text) {
        super(new Text(text));

        this.text = (Text)shape;

        viewController = new CanvasTextPropertyController(this);
        try {
            textPropetyView = JavaFXUtil.loadFXML("element_property/text_property", viewController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////
    // public methods
    /////////////////////////////////////////////////////////////////////
    public void setText(String text){
        this.text.setText(text);
    }

    public void setFont(String family, double size, FontWeight weight, FontPosture posture) {

        if (family == null){
            family = this.text.getFont().getFamily();
        }
        if (weight == null){
            weight = extractFontWeight(this.text.getFont().getName());
        }
        if (posture == null){
            posture = extractFontPosture(this.text.getFont().getName());
        }
        if (size < 0)
            size = this.text.getFont().getSize();

        Font font = Font.font(family, weight, posture, size);
        this.text.setFont(font);
    }

    public void setFamily(String family){
        setFont(family, -1, null, null);
    }

    public void setFontSize(double size){
        setFont(null, size, null, null);
    }

    public double getFontSize(){
        return text.getFont().getSize();
    }

    public void setFontWeight(String weight){
        setFont(null, -1, FontWeight.findByName(weight), null);
    }

    public void setFontPosture(String posture){
        setFont(null, -1, null, FontPosture.findByName(posture));
    }

    public List<String> getFontWeights(){
        List<String> result = new ArrayList<>();

        for(var weight : extractFontWeightFromFamily(this.text.getFont().getFamily())){
            result.add(weight.name());
        }

        return result;
    }

    public List<String> getFontPostures(){
        List<String> result = new ArrayList<>();

        for(var posture : extractFontPostureFromFamily(this.text.getFont().getFamily())){
            result.add(posture.name());
        }

        return result;
    }

    /////////////////////////////////////////////////////////////////////
    // private methods
    /////////////////////////////////////////////////////////////////////

    private List<FontWeight> extractFontWeightFromFamily(String family){

        var result = new LinkedHashSet<FontWeight>();

        for(var name : Font.getFontNames(family)){
            var weight = extractFontWeight(name);

            if (weight != null){
                result.add(weight);
            }
        }

        return result.stream().toList();
    }
    private List<FontPosture> extractFontPostureFromFamily(String family) {

        var result = new LinkedHashSet<FontPosture>();

        for (var name : Font.getFontNames(family)) {
            var posture = extractFontPosture(name);

            if (posture != null) {
                result.add(posture);
            }
        }

        return result.stream().toList();
    }

    private FontWeight extractFontWeight(String styleString){
        String[] styles = styleString.split(" ");

        FontWeight weight;

        for(var style : styles){
            weight = FontWeight.findByName(style);

            if (weight != null)
                return weight;
        }

        return FontWeight.NORMAL;
    }

    private FontPosture extractFontPosture(String styleString){
        String[] styles = styleString.split(" ");

        FontPosture posture;

        for(var style : styles){
            posture = FontPosture.findByName(style);

            if (posture != null)
                return posture;
        }

        return FontPosture.REGULAR;
    }
    @Override
    public void select() {
        getPropertyPane().getChildren().add(textPropetyView);
        super.select();
    }

    @Override
    public void deselect() {
        getPropertyPane().getChildren().remove(textPropetyView);
        super.deselect();
    }

    @Override
    public void setBackgroundColor(Color color) {
        text.setStyle("-fx-background-color: red");
    }

    @Override
    public void updateTransform(double x, double y, double width, double height) {
        // Pt / Px = 0.75 (Height)
        double dHeight = height - getFontSize() / 0.75;

        var oldX = getLayoutX();
        var oldY = getLayoutY();
        setFontSize(height * 0.75);
        // @TODO: Fix the position of the text
        shape.setLayoutX(oldX);
        shape.setLayoutY(oldY);
    }
}
