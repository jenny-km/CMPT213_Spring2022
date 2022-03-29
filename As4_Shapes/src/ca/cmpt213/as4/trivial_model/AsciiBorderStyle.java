package ca.cmpt213.as4.trivial_model;

import java.awt.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the Ascii border style
 */

public class AsciiBorderStyle extends Solid{

    public AsciiBorderStyle(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText) {
        super(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText);
    }

    @Override
    public char getBorderStyle(int i, int j) {
        if(getIsRedacted()){
            return getLineChar();
        }

        if (getWidth() + getHeight() < 4) { // shape cannot be drawn with double lined characters
            return '■';
        } else {
            if (i == 0 && j == 0) { // top left
                return '╔';
            } else if (i == 0 && j == getHeight() - 1) { // bottom left
                return '╚';
            } else if (i == getWidth() - 1 && j == 0) { // top right
                return '╗';
            } else if (i == getWidth() - 1 && j == getHeight() - 1) { // bottom right
                return '╝';
            } else if (i == 0 || i == getWidth() - 1) { // left or right line
                return '║';
            } else if (j == 0 || j == getHeight() - 1) { // top or bottom line
                return '═';
            }
        }
      return ' ';
    }
}
