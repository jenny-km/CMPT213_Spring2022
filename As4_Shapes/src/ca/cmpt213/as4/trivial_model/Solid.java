package ca.cmpt213.as4.trivial_model;

import ca.cmpt213.as4.UI.Canvas;
import java.awt.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the Solid background type
 */


// Rectangle
public class Solid extends Shape{

    public Solid(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText) {
        super(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText);
    }

    @Override
    public void draw(Canvas canvas) {
        for (int j = 0; j < getHeight(); j++) {
            for (int i = 0; i < getWidth(); i++) {
                int canvasLocationX = getColumn() + i;
                int canvasLocationY = getRow() + j;
                if (isBorderCell(i, j)) {
                    canvas.setCellText(canvasLocationX, canvasLocationY, getBorderStyle(i, j));
                }else {
                    canvas.setCellText(canvasLocationX, canvasLocationY, getCellText());
                }
                if(isPartOfShape(i, j) || getIsRedacted()){
                    canvas.setCellColor(canvasLocationX, canvasLocationY, getBackgroundColor());
                }
            }
        }
    }

    public char getBorderStyle(int i, int j){
        return getLineChar();
    }

    public char getCellText(){
        return getFillText().charAt(0);
    }

    public boolean isPartOfShape(int i, int j){
        return true;
    }
}
