package ca.cmpt213.as4tank.restapi;

/**
 * As4 - Tank Game Rest Api by Jennifer Kim
 *
 * ApiBoardWrapper Class
 * - represents the game board
 * - contains the board's width, height, and the state of
 *   each cell
 *
 *   Wrapper Classes are used to communicate with the backend
 */
public class ApiBoardWrapper {
    public int boardWidth;
    public int boardHeight;
    public String[][] cellStates;

    public ApiBoardWrapper(int boardWidth, int boardHeight, String[][] cellStates){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.cellStates = cellStates;
    }
}