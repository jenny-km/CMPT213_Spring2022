package ca.cmpt213.as4.trivial_model;

import ca.cmpt213.as4.UI.DrawableShape;
import java.awt.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the base Shape
 */

public abstract class Shape implements DrawableShape {
    private int row; // top
    private int column; // left
    private int width;
    private int height;
    private String background;
    private Color backgroundColor;
    private String line;
    private char lineChar;
    private String fill;
    private String fillText;
    private boolean isRedacted;

    Shape(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText){
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
        this.background = background;
        this.backgroundColor = backgroundColor;
        this.line = line;
        this.lineChar = lineChar;
        this.fill = fill;
        this.fillText = fillText;
        this.isRedacted = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getBackground() {
        return background;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line){
        this.line = line;
    }

    public char getLineChar() {
        return lineChar;
    }

    public void setLineChar(char lineChar){
        this.lineChar = lineChar;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill){
        this.fill = fill;
    }

    public String getFillText() {
        return fillText;
    }

    public void setFillText(String fillText) {
        this.fillText = fillText;
    }

    public void setRedacted(boolean redacted) {
        isRedacted = redacted;
    }

    public boolean getIsRedacted() {
        return isRedacted;
    }

    public boolean isBorderCell(int row, int column) {
        if(row == 0 || row == getWidth() - 1){
            return true;
        }else if(column == 0 || column == getHeight() - 1){
            return true;
        }else{
            return false;
        }
    }



}
