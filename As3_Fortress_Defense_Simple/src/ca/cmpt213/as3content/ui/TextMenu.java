package ca.cmpt213.as3content.ui;
import ca.cmpt213.as3content.model.Cell;
import ca.cmpt213.as3content.model.Field;
import ca.cmpt213.as3content.model.Fortress;

import java.util.*;

/**
 * Fortress Defense - Assignment 3
 * TextMenu Description *
 * --> This contains all the necessary text displayed in the program. This includes the interface text,
 *     input text, map display, error messages, and methods for checking if inputs are valid.
 *
 * @author Jennifer Kim
 */

public class TextMenu {

    private static final String[] ROW_LETTERS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private static String border = "-----------------------------------";

    // Interface Text
    public static String getStartUpText(){
        String startUpGreeting = "Welcome to Fortress Defense!\n" + "by Jennifer Kim\n" + border;
        return startUpGreeting;
    }

    public static String getCheatAppliedText(){
        return "Notice: Cheat feature is applied.\nCheat Map is generating...";
    }

    public static String getMissText(){
        return "Miss.";
    }

    public static String getHitText(){
        return "HIT!";
    }

    public static String getTankDamageText(int tankId, int numOfCells, int damage){
        String deadOrAlive;
        if(numOfCells != 0){
            deadOrAlive = "Alive";
        }else{
            deadOrAlive = "Dead";
        }
        String tankDamageText = deadOrAlive + " tank #" + tankId + " of " + numOfCells + " shot you for " + damage;
        return tankDamageText;
    }

    public static String getLoseText(){
        return "I'm sorry, your fortress has been smashed!";
    }

    public static String getWinText(){
        return "Congratulations, You Won! All tanks have been successfully destroyed.";
    }

    public static String getTankLocationRevealText(){
        return "(Lowercase tank letters are where you shot.)";
    }

    // Map Display
    public static void displayField(Field field, Fortress fortress){
        System.out.println("\nGame Board:\n");
        System.out.println("      1   2   3   4   5   6   7   8   9  10");
        List<Cell> board = field.getBoard();
        for(int i = 0; i < 10; i++){
            System.out.print("  " + ROW_LETTERS[i].toUpperCase());
            for(int j = 0; j < 10; j++){
                int cellLocation = (i * 10) + j;
                System.out.print("   " + board.get(cellLocation).getDisplay());
            }
            System.out.print("\n");
        }
        System.out.println("\nFortress Structure Left: " + fortress.getHealth() + ".");
    }

    public static void displayCheatBoard(Fortress fortress, Field field){
        System.out.println("\nGame Board:\n");
        System.out.println("      1   2   3   4   5   6   7   8   9  10");
        List<Cell> board = field.getBoard();
        for(int i = 0; i < 10; i++){
            System.out.print("  " + ROW_LETTERS[i].toUpperCase());
            for(int j = 0; j < 10; j++){
                int cellLocation = (i * 10) + j;
                if(board.get(cellLocation).getIsTankCell()){
                    int cellId = board.get(cellLocation).getTank().getId();
                    char cellIdLetter = (char) (cellId + 65);
                    if(board.get(cellLocation).getIsHit()){
                        System.out.print("   " + Character.toLowerCase(cellIdLetter));
                    }else{
                        System.out.print("   " + Character.toUpperCase(cellIdLetter));
                    }
                }else{
                    if(board.get(cellLocation).getIsHit()){
                        System.out.print("   " + board.get(cellLocation).getDisplay());
                    }else{
                        System.out.print("   " + ".");
                    }
                }
            }
            System.out.print("\n");
        }
        System.out.println("\nFortress Structure Left: " + fortress.getHealth() + ".");
    }

    // Input Validation
    public static String getPlayerInputText(){
        return "Enter your move: ";
    }

    public static int getPlayerInput(){
        Scanner scanner = new Scanner(System.in);
        String playerInput;
        int cellLocation;
        do{
            System.out.print(getPlayerInputText());
            playerInput = scanner.nextLine().trim().replace(" ", "").toLowerCase();
            cellLocation = validatePlayerInput(playerInput);
        } while(cellLocation == -1);
        return cellLocation;
    }

    public static int validatePlayerInput(String playerInput){
        if(playerInput.length() >= 2 && playerInput.length() <= 3){
            List<String> rowLetters = new ArrayList<>();
            rowLetters.addAll(Arrays.asList(ROW_LETTERS));
            int row = rowLetters.indexOf(Character.toString(playerInput.charAt(0)));
            int column;
            if(playerInput.length() == 2){
                column = Character.getNumericValue(playerInput.charAt(1)) - 1;
            }else{
                String colSubstring = playerInput.substring(playerInput.length() - 2);
                column = Integer.parseInt(colSubstring) - 1;
            }

            if(row != -1 && (column >= 0 && column <= 9)){
                int cellLocation = (row * 10) + column;
                return cellLocation;
            }else{
                System.out.print(getMoveInputErrorText());
                return -1;
            }
        }
        System.out.print(getMoveInputErrorText());
        return -1;
    }

    // Error Messages
    private static String getMoveInputErrorText(){
        return "\nError: Please enter a value from A1 to J10\n";
    }

    public static String getArgumentsCountErrorText(){
        return "\nError: Please enter 0-2 command-line arguments\n";
    }

    public static String getNumOfTanksInvalidErrorText(){
        return "\nError: Please enter a number of tanks which is greater than 0.\n";
    }

    public static String getTankOverflowErrorText(){
        return "\nError: The number of tanks entered do not fit in the game board.\nPlease enter a smaller number.";
    }

}
