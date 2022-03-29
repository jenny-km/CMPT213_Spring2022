package ca.cmpt213.as4.trivial_model;

import java.awt.*;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the sequence border style
 */

public class SequenceBorderStyle extends Solid{

    private Character[] sequenceNumbers = {'1', '2', '3', '4', '5'};

    public SequenceBorderStyle(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText) {
        super(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText);
    }

    @Override
    public char getBorderStyle(int i, int j){
        if(getIsRedacted()){
            return getLineChar();
        }

        if(j == 0){ // top border
            return sequenceNumbers[i%5];
        }else if(i == getWidth() - 1 &&  j > 0 ){ // right border
            return sequenceNumbers[(i + j) % 5];
        }else if(i < getWidth() - 1 && j == getHeight() - 1){ // bottom border
            int sumOfMaxWidthAndHeight = (getHeight() - 1) + (getWidth() - 1);
            int diffOfWidth = (getWidth() - 1) - i;
            int total = sumOfMaxWidthAndHeight + diffOfWidth;
            return sequenceNumbers[total % 5];
        }else if(i == 0){ // left border
            int sumOfMaxWidthAndHeight = (getHeight() - 1) + (getWidth() - 1);
            int sumOfTopRightAndBottomBorder = sumOfMaxWidthAndHeight + (getWidth() - 1);
            int diffOfHeight = (getHeight() - 1) - j;
            int total = sumOfTopRightAndBottomBorder + diffOfHeight;
            return sequenceNumbers[total % 5];
        }
        return ' ';
    }
}
