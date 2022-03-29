package ca.cmpt213.as3content.ui;
import ca.cmpt213.as3content.model.Field;
import ca.cmpt213.as3content.model.Fortress;
import ca.cmpt213.as3content.model.Tank;

import java.util.*;

/**
 * Fortress Defense - Assignment 3
 * Main Description *
 *  --> This class ties all the other classes together.
 *      It is a framework for the game logic.
 *
 * @author Jennifer Kim
 */

public class Main {

    private static List<String> arguments = new ArrayList<>();
    private static int numOfTanks;
    private static boolean isCheatApplied = false;

    public static void main(String[] args)
    {
        for (String arg: args){
            arguments.add(arg);
        }

        System.out.println(TextMenu.getStartUpText());

        setNumOfTanks();
        Fortress fortress = new Fortress(2500);
        Field field = new Field(numOfTanks);
        field.generateField();

        if(isCheatApplied){
            System.out.println(TextMenu.getCheatAppliedText());
            TextMenu.displayCheatBoard(fortress, field);
        }

        while(true){
            TextMenu.displayField(field, fortress);
            checkIfGameOver(fortress, field);
            int cellLocation = TextMenu.getPlayerInput();
            if(field.attackCell(cellLocation).equals("HIT")){
                System.out.println(TextMenu.getHitText());
            }else{
                System.out.println(TextMenu.getMissText());
            }
            int totalDamage = field.calculateDamage();
            fortress.updateHealth(totalDamage);
        }
    }

    private static void setNumOfTanks(){
        if(arguments.size() == 0){
            numOfTanks = 5;
        }else if (arguments.size() == 1){
            numOfTanks = Integer.parseInt(arguments.get(0));
        }else if(arguments.size() == 2){
            numOfTanks = Integer.parseInt(arguments.get(0));
            checkIfCheatIsApplied();
        }else{
            TextMenu.getArgumentsCountErrorText(); // too many arguments
            System.exit(0);
        }
        if(checkIfNumOfTanksIsInvalid(numOfTanks)){
            System.out.println(TextMenu.getNumOfTanksInvalidErrorText());
            System.exit(0);
        }
    }

    private static boolean checkIfNumOfTanksIsInvalid(int numOfTanks){
        if(numOfTanks <=0){
            return true;
        }else{
            return false;
        }
    }

    private static void checkIfCheatIsApplied(){
        String secondArg = arguments.get(1).toLowerCase();
        if(secondArg.equals("--cheat")){
            isCheatApplied = true;
        }
    }

    private static void checkIfGameOver(Fortress fortress, Field field){
        if(fortress.getHealth() <= 0){
            System.out.println(TextMenu.getLoseText());
            TextMenu.displayCheatBoard(fortress, field);
            System.out.println(TextMenu.getTankLocationRevealText());
            System.exit(0);
        }
        List<Tank> tanks = field.getTanks();
        int totalDamageOfTanks = 0;
        for(Tank tank : tanks){
            totalDamageOfTanks += tank.getDamage();
        }
        if(totalDamageOfTanks == 0){
            System.out.println(TextMenu.getWinText());
            TextMenu.displayCheatBoard(fortress, field);
            System.out.println(TextMenu.getTankLocationRevealText());
            System.exit(0);
        }
    }
}
