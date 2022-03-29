package ca.cmpt213.as3content.model;

import java.util.List;

/**
 * Fortress Defense - Assignment 3
 * Tank Description *
 * --> This is the class that represents the tanks that are on the
 *     field. Through this class we will know which of the cells of the
 *     tank are alive or dead.
 *
 * @author Jennifer Kim
 */

public class Tank{
    private int id;
    private int damage;
    private List<Cell> tankCells;
    private int numOfCellsAlive;

    public Tank(int id){
        this.id = id;
        this.damage = 20;
        this.numOfCellsAlive = 5;
    }

    // Getters
    public int getId(){
        return id;
    }

    public int getDamage() {
        return damage;
    }

    public int getNumOfCellsAlive(){
        return numOfCellsAlive;
    }

    // Setters
    public void setTankCells(List<Cell> tankCells){
        this.tankCells = tankCells;
    }

    public void setDamage(){
        setNumOfCellsAlive();
        if(numOfCellsAlive == 5){
            damage = 20;
        }else if(numOfCellsAlive == 4){
            damage = 20;
        }else if(numOfCellsAlive == 3){
            damage = 5;
        }else if(numOfCellsAlive == 2){
            damage = 2;
        }else if(numOfCellsAlive == 1){
            damage = 1;
        }else if(numOfCellsAlive == 0){
            damage = 0;
        }else{
            assert false: "There is an invalid number of cells alive: "
                    + numOfCellsAlive
                    + "\nPlease check Tank class, setDamage() method";
        }
    }

    private void setNumOfCellsAlive(){
        int numOfCellsAlive = 0;
        for(Cell tankCell : tankCells){
            if (!tankCell.getIsHit()) {
                numOfCellsAlive++;
            }
        }
        this.numOfCellsAlive = numOfCellsAlive;
    }
}
