/*
    Minion Tracker - Assignment 1 by Jennifer Kim
    * TextMenu Description *
    --> This contains all the necessary text displayed in the program. This includes the interface text,
        input text, error messages, and methods for checking if inputs are valid.
*/

package ca.cmpt213.MinionTracker;
import java.util.*;

public class TextMenu {
    private static String longBorder = "*****************************************";
    private static String mediumBorder = "************************";
    private static String shortBorder = "*************";
    private static Scanner scanner = new Scanner(System.in);

    // Interface Text
    public static String getStartUpText(){
        String startUpGreeting = " Welcome to the Evil Minion Tracker (tm)\n by Jennifer Kim";
        String startUpText = longBorder + "\n" + startUpGreeting + "\n" + longBorder + "\n";
        return startUpText;
    }

    public static String getMainMenuText(){
        String mainMenuGreeting = "* Main Menu *";
        String mainMenuContents = "1. List minions\n" +
                                  "2. Add a new minion\n" +
                                  "3. Remove a minion\n" +
                                  "4. Attribute evil deed to a minion\n" +
                                  "5. DEBUG: Dump objects (toString)\n" +
                                  "6. Exit\n" + "> ";
        String mainMenuText = "\n" + shortBorder + "\n" + mainMenuGreeting + "\n" + shortBorder + "\n"
                              + mainMenuContents;
        return mainMenuText;
    }

    public static String getExitText(){
        String exitGreeting = "Goodbye!\n" +
                              "Thank you for using the Evil Minion Tracker\n" +
                              "by Jennifer Kim. Please rate us 5 stars.";
        String exitText = "\n" + longBorder + "\n" + exitGreeting + "\n" + longBorder;
        return exitText;
    }

    public static String getMinionListText(){
        String minionListGreeting = "List of Minions:";
        String minionListText = "\n" + minionListGreeting + "\n" + mediumBorder;
        return minionListText;
    }

    public static String getAttributedEvilDeedText(String name, int evilDeedCount){
        return name + " has now down " + evilDeedCount + " evil deed(s)\n";
    }

    public static String getDumptoString(){
        return "All minion objects: ";
    }

    public static String showMainMenu(){
        System.out.print(getMainMenuText());
        return scanner.nextLine().trim();
    }

    // Input Text
    public static String getMinionNameInputText(){
        return "Enter minion's name: ";
    }

    public static String getMinionHeightInputText(){
        return "Enter minion's height: ";
    }

    public static String getCancelInputText(){
        return "(Enter 0 to CANCEL)\n> ";
    }

    //Error Messages
    public static String getMainMenuErrorText(){
        return "\nError: Please enter a selection between 1 and 6\n";
    }

    public static String getMinionNameInputErrorText(){
        return "\nError: Name must be 1 or more characters long\n";
    }

    public static String getMinionHeightInputErrorText(){
        return "\nError: Height must be 0 or more\n";
    }

    public static String getMinionListErrorText(){
        return "No minions found.";
    }

    public static String getRemoveMinionErrorText(int minionCount){
        String removeMinionErrorText = "\nError: Please enter a selection between ";
        if(minionCount == 0){
            removeMinionErrorText = removeMinionErrorText + "0 and 0";
        }else{
            removeMinionErrorText = removeMinionErrorText + "1 and " + minionCount;
        }
        return removeMinionErrorText;
    }

    // Methods for Checking if Inputs are valid
    public static String checkMinionNameInput(){
        String name = "";
        do{
            System.out.print(getMinionNameInputText());
            name = scanner.nextLine();
            name = name.trim();
            // name must be 1 or more characters long
            if(name.equals("")){
                System.out.println(getMinionNameInputErrorText());
            }
        }while(name.equals(""));
        return name;

    }

    public static double checkMinionHeightInput(){
        double parsedHeight = -1;
        do{
            System.out.print(getMinionHeightInputText());
            String height = scanner.nextLine();
            try
            {
                parsedHeight = Double.parseDouble(height);
                if(parsedHeight < 0){
                    System.out.println(getMinionHeightInputErrorText());
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println(getMinionHeightInputErrorText());
            }
        }while(parsedHeight < 0);
        return parsedHeight;
    }

    public static int checkChosenMinionInput(int minionCount){
        int parsedChosenMinion = -1;
        do{
            System.out.print(getCancelInputText());
            String chosenMinion = scanner.nextLine();
            try
            {
                parsedChosenMinion = Integer.parseInt(chosenMinion);
                if(parsedChosenMinion < 0
                        || parsedChosenMinion > minionCount){
                    System.out.println(getRemoveMinionErrorText(minionCount));
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println(getRemoveMinionErrorText(minionCount));
            }
        }while(parsedChosenMinion < 0
                || parsedChosenMinion > minionCount);
        return parsedChosenMinion;
    }
}