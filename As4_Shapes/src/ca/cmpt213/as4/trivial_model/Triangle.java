package ca.cmpt213.as4.trivial_model;

import ca.cmpt213.as4.UI.Canvas;
import java.awt.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the triangle background type
 */

public class Triangle extends Solid{

    public Triangle(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText) {
        super(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText);
    }

    @Override
    public boolean isPartOfShape(int i, int j){
        if(i >= j){
            return true;
        }else{
            return false;
        }
    }
}
