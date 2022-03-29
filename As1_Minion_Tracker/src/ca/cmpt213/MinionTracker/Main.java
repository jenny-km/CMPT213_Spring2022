/*
    Minion Tracker - Assignment 1 by Jennifer Kim
    * Main Description *
    --> This class records the list of minions as well as contains the main methods for
        several functions such as list minion, remove minion, attribute evil deeds, and
        dump object to string. Moreover, it is in charge of tying together the minion
        class and the TextMenu class in order to perform the basic functions of the program.
 */

package ca.cmpt213.MinionTracker;
import java.util.*;

public class Main {

    private static List<Minion> minions = new ArrayList<>();
    private static boolean hasNotExited = true;

    public static void main(String[] args){

        System.out.print(TextMenu.getStartUpText());

        while(hasNotExited){
            switch(TextMenu.showMainMenu()){
                case "1": // List Minions
                    getMinionList();
                    break;
                case "2": // Add a Minion
                    minions.add(new Minion(TextMenu.checkMinionNameInput(), TextMenu.checkMinionHeightInput(),0));
                    break;
                case "3": // Remove a Minion
                    removeMinion();
                    break;
                case "4": // Attribute an Evil Deed to a Minion
                    attributeEvilDeeds();
                    break;
                case "5": // DEBUG: Dump objects (toString)
                    dumpToString();
                    break;
                case "6":  // Exit
                    System.out.print(TextMenu.getExitText());
                    hasNotExited = false;
                    break;
                default:
                    System.out.println(TextMenu.getMainMenuErrorText());
            }
        }
    }

    // Main Functions
    private static void getMinionList(){
        System.out.println(TextMenu.getMinionListText());
        int minionCount = 1;
        if(minions.isEmpty()){
            System.out.println(TextMenu.getMinionListErrorText());
        }else {
            for (Minion minion : minions) {
                System.out.println(minionCount + ". "
                        + minion.getName() + ", "
                        + minion.getHeight() + "m, "
                        + minion.getEvilDeedCount() + " evil deed(s)");
                minionCount++;
            }
            System.out.print("\n");
        }
    }

    private static void removeMinion(){
        getMinionList();
        int removeMinionInput = TextMenu.checkChosenMinionInput(minions.size());
        if(removeMinionInput != 0){
            minions.remove(removeMinionInput-1);
        }
    }

    private static void attributeEvilDeeds(){
        getMinionList();
        int evilDeedsInput = TextMenu.checkChosenMinionInput(minions.size());
        if(evilDeedsInput != 0){
            Minion minion = minions.get(evilDeedsInput-1);
            minion.setEvilDeedCount(minion.getEvilDeedCount()+1);
            System.out.println(TextMenu.getAttributedEvilDeedText(minion.getName(), minion.getEvilDeedCount()));
        }
    }

    private static void dumpToString(){
        System.out.println(TextMenu.getDumptoString());
        int minionCount = 1;
        if(minions.isEmpty()){
            System.out.println(TextMenu.getMinionListErrorText());
        }else {
            for (Minion minion : minions) {
                System.out.println(minionCount + ". " + minion.toString());
                minionCount++;
            }
        }
    }
}
