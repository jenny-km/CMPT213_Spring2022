package ca.cmpt213.as4tank.restapi;

/**
 * As4 - Tank Game Rest Api by Jennifer Kim
 *
 * ApiGameWrapper Class
 * - represents the game's state
 * - contains the the game's id, whether player has won or lost, fortress health
 *   and number of tanks alive
 *
 *   Wrapper Classes are used to communicate with the backend
 */

public class ApiGameWrapper {
    public int gameNumber;
    public boolean isGameWon;
    public boolean isGameLost;
    public int fortressHealth;
    public int numTanksAlive;

    public int[] lastTankDamages;

    public ApiGameWrapper(int gameNumber, boolean isGameWon, boolean isGameLost, int fortressHealth, int numTanksAlive, int[] lastTankDamages){
        this.gameNumber = gameNumber;
        this.isGameWon = isGameWon;
        this.isGameLost = isGameLost;
        this.fortressHealth = fortressHealth;
        this.numTanksAlive = numTanksAlive;
        this.lastTankDamages = lastTankDamages;
    }
}
