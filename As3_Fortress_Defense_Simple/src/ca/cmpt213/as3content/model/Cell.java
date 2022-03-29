package ca.cmpt213.as3content.model;

/**
 * Fortress Defense - Assignment 3 by Jennifer Kim
 * Cell Description *
 *  --> This is the class that represents the cells on the field.
 *      There are two kinds of field cells which are tank cells or
 *      regular cells (cells that do not contain the tanks).
 *
 * @author Jennifer Kim
 */

public class Cell {
    private boolean isTankCell;
    private boolean isHit;
    private String display;
    private Tank tank;

    public Cell(){
        this.isTankCell = false;
        this.isHit = false;
        this.display = "~"; // Status of the cell (foggy, hit, or miss)
        this.tank = null;
    }

    // Getters
    public boolean getIsTankCell() {
        return isTankCell;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public String getDisplay() {
        return display;
    }

    public Tank getTank(){
        return tank;
    }

    // Setters
    public void setIsTankCell(boolean isTankCell) {
        this.isTankCell = isTankCell;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setTank(Tank tank){
        this.tank = tank;
    }

    // Method
    public void cellAttacked(){
        isHit = true;
        if(isTankCell){
            setDisplay("X");
        }else{
            setDisplay(" ");
        }
    }
}