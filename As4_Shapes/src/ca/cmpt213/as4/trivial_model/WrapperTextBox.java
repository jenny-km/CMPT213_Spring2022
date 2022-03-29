package ca.cmpt213.as4.trivial_model;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * As4 - Shapes by Jennifer Kim
 *
 * Represents the Wrapped Text Box Fill
 */

public class WrapperTextBox extends Solid{

    private String text = getFillText();
    private String formattedText = "";
    private int counter = 0;

    public WrapperTextBox(int row, int column, int width, int height, String background, Color backgroundColor, String line, char lineChar, String fill, String fillText) {
        super(row, column, width, height, background, backgroundColor, line, lineChar, fill, fillText);
    }

    @Override
    public char getCellText(){
        if(getIsRedacted()){
            return getFillText().charAt(0);
        }else{
            if(formattedText.equals("")){
                formatFilledText();
            }
            char letter;
            if(counter <= formattedText.length() - 1){
                letter = formattedText.charAt(counter);
                counter++;
            }else {
                letter = ' ';
            }
            return letter;
        }
    }

    public void formatFilledText(){
        String[] words = text.split(" ");
        List<String> lineText = new ArrayList<>();
        int lineWidth = getWidth() - 2;
        String tempLine = "";
        String tempWord = "";
        boolean isFull = false;

        for (int i = 0; i < words.length; i++) {
            if(words[i].length() <= lineWidth) {

                if(tempLine.length() == 0){ // at the start
                    tempLine = words[i];
                }else if (tempLine.length() + words[i].length() + 1 < lineWidth) {
                    tempLine += " " + words[i];
                } else if (tempLine.length() + words[i].length() + 1 == lineWidth) {
                    tempLine += " " + words[i];
                    lineText.add(tempLine);
                    tempLine = "";
                } else { // word cannot fit into the current tempLine
                    tempWord = words[i];
                    isFull = true;
                }

                if(i == words.length - 1){ // Checks spacing for last word
                    isFull = true;
                }

                if(isFull){ // if tempLine is full then we check spacing
                    tempLine = checkSpacing(lineWidth, tempLine);
                    lineText.add(tempLine);
                    tempLine = tempWord;
                    tempWord = "";
                    isFull = false;
                }

                if(i == words.length - 1 && !tempLine.equals("")){
                    tempLine = checkSpacing(lineWidth, tempLine);
                    lineText.add(tempLine);
                }

            }else{ // if word length is too big
                tempLine = words[i];
                while (tempLine.length() > lineWidth) {
                    lineText.add(tempLine.substring(0, lineWidth));
                    tempLine = tempLine.substring(lineWidth);
                }
                tempLine = checkSpacing(lineWidth, tempLine);
                lineText.add(tempLine);
            }
        }

        for(String line : lineText){ // add every line into one string
            formattedText += line;
        }
    }

    public String checkSpacing(int lineWidth, String tempLine){
        int spaces = lineWidth - tempLine.length();
        String leftSpaces = "";
        String rightSpaces = "";
        for(int j = 0; j < spaces; j ++){
            if(j % 2 == 0){
                leftSpaces += " ";
            }else{
                rightSpaces += " ";
            }
        }
        tempLine = leftSpaces + tempLine + rightSpaces;
        return tempLine;
    }
}
