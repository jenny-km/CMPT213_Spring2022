package ca.cmpt213.as4.trivial_model;

import ca.cmpt213.as4.ShapeModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.Color;
import java.io.*;
import java.util.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Trivial ShapeModel implementation to demonstrate how it connects with provided classes.
 *
 * It must:
 * - Implement ShapeModel so that the UI can be passed a reference to the model at runtime (DI)
 *     - populateFromJSON(): load all the boxes from the JSON file
 *            Create and store DrawableShape objects.
 **
 *     - redact(): change all existing objects to be "redacted" (see assignment doc).
 *
 *     - iterator(): return an iterator to the DrawableShapes (your boxes) which you have created.
 * This class just puts some text in the middle of the screen, but it shows *how* you can do this.
 */

public class TrivialShapeModel implements ShapeModel {

    private int row;
    private int column;
    private int width;
    private int height;
    private String background;
    private String color;
    private Color backgroundColor;
    private String line;
    private char lineChar;
    private String fill;
    private String fillText;

    private List<Shape> shapeList = new ArrayList<>();

    // Load objects from file
    @Override
    public void populateFromJSON(File jsonFile) {
        try{
            JsonElement fileElement = JsonParser.parseReader(new FileReader((jsonFile)));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray shapes = fileObject.get("shapes").getAsJsonArray();
            for(JsonElement shape : shapes) {
                recordShapeFeatures(shape);
                if(background.equals("solid")){
                    if(fill.equals("wrapper")) {
                        shapeList.add(new WrapperTextBox(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText));
                    }else if(line.equals("ascii line")){
                        shapeList.add(new AsciiBorderStyle(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText));
                    }else if(line.equals("sequence")){
                        shapeList.add(new SequenceBorderStyle(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText));
                    }else{
                        shapeList.add(new Solid(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText));
                    }
                }else if(background.equals("triangle")){
                    shapeList.add(new Triangle(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText));
                }else if(background.equals("checker")){
                    shapeList.add(new Checker(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText));
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void recordShapeFeatures(JsonElement shape) {
        JsonObject shapeObject = shape.getAsJsonObject();
        row = Integer.parseInt(shapeObject.get("top").getAsString());
        column = Integer.parseInt(shapeObject.get("left").getAsString());
        width = Integer.parseInt(shapeObject.get("width").getAsString());
        height = Integer.parseInt(shapeObject.get("height").getAsString());
        background = shapeObject.get("background").getAsString();
        color = shapeObject.get("backgroundColor").getAsString().toUpperCase();
        if (color.equalsIgnoreCase("dark gray")) {
            backgroundColor = Color.DARK_GRAY;
        }else if (color.equalsIgnoreCase("light gray")){
            backgroundColor = Color.LIGHT_GRAY;
        }else{
            try {
                backgroundColor = (Color)Color.class.getField(color).get(null);
            } catch (Exception e) {
                backgroundColor = null;
            }
        }
        line = shapeObject.get("line").getAsString();
        if(line.equals("char")){
            lineChar = shapeObject.get("lineChar").getAsString().charAt(0);
        }
        fill = shapeObject.get("fill").getAsString();
        fillText = shapeObject.get("fillText").getAsString();
    }

    // Redact all our objects (UI updates after calling this)
    @Override
    public void redact() {
        backgroundColor = Color.LIGHT_GRAY;
        for(Shape shape: shapeList){
            shape.setBackgroundColor(backgroundColor);
            shape.setRedacted(true);
            shape.setLine("char");
            shape.setLineChar('+');
            shape.setFill("solid");
            shape.setFillText("X");
        }
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapeList.iterator();
    }

}
