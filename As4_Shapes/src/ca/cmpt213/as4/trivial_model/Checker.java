package ca.cmpt213.as4.trivial_model;

import ca.cmpt213.as4.UI.Canvas;

import java.awt.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the checker background type
 */

public class Checker extends Solid{
    public Checker(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText) {
        super(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText);
    }

    @Override
    public boolean isPartOfShape(int i, int j){
        if(i % 2 == 0 && j % 2 == 0){ // if row is even, then shaded columns are even
            return true;
        }else if(i % 2 != 0 && j % 2 != 0){ // if row is odd, then shaded columns are odd
            return true;
        }else{
            return false;
        }
    }

}
