package ca.cmpt213.as3content.model;

import ca.cmpt213.as3content.ui.TextMenu;

import java.util.*;

/**
 * Fortress Defense - Assignment 3 by Jennifer Kim
 * Field Description *
 * --> This is the class that represents the field or game board.
 *     This class is connects the tank and cells to attack cells,
 *     calculate damage, and generate the game board.
 *
 * @author Jennifer Kim
 */

public class Field {
    private static final int FIELD_ROWS = 10;
    private static final int FIELD_COLUMNS = 10;
    private static List<Cell> board;
    private static List<Tank> tanks;
    private int numOfTanks;

    public Field(int numOfTanks){
        board = new ArrayList<>();
        tanks = new ArrayList<>();
        this.numOfTanks = numOfTanks;
        for (int i = 0; i < FIELD_ROWS; i++){
            for(int j = 0; j < FIELD_COLUMNS; j++){
                board.add(new Cell());
            }
        }
    }

    //Getters
    public List<Cell> getBoard(){
        return board;
    }

    public List<Tank> getTanks(){
        return tanks;
    }

    //Methods
    public void generateField(){
        for(int i = 0; i < numOfTanks; i++){
            tanks.add(new Tank(i));
            tanks.get(i).setTankCells(placeTank(i));
        }
    }

    private List<Cell> placeTank(int tankNo){
        int startingPoint = tankNo * 5;
        List<Cell> tankCells = new ArrayList<>();
        if(startingPoint >= 100){
            System.out.println(TextMenu.getTankOverflowErrorText());
            System.exit(0);
        }
        // place tanks starting from top left
        for (int i = startingPoint; i < startingPoint + 5; i++){
            board.get(i).setIsTankCell(true);
            board.get(i).setTank(tanks.get(tankNo));
            tankCells.add(board.get(i));
        }
        return tankCells;
    }

    public String attackCell(int cellLocation){
        board.get(cellLocation).cellAttacked();
        if(board.get(cellLocation).getIsTankCell()){
            board.get(cellLocation).getTank().setDamage();
            return "HIT";
        }
        return "MISS";
    }

    public int calculateDamage(){
        int totalDamage = 0;
        for(Tank tank: tanks){
            System.out.println(TextMenu.getTankDamageText(tank.getId() + 1, tank.getNumOfCellsAlive(), tank.getDamage()));
            totalDamage += tank.getDamage();
        }
        return totalDamage;
    }
}
