package ca.cmpt213.as4tank.restapi;

/**
 * As4 - Tank Game Rest Api by Jennifer Kim
 *
 * ApiLocationWrapper Class
 * - represents player's move
 * - contains the row and column of the player's chosen cell
 *
 *   Wrapper Classes are used to communicate with the backend
 */

public class ApiLocationWrapper {
    public int row;
    public int col;

    public ApiLocationWrapper(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}