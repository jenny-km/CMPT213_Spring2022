package ca.cmpt213.as3content.model;

/**
 * Fortress Defense - Assignment 3
 * Fortress Description *
 * --> This is the class that represents the player's fortress.
 *     This class is used mostly to indicate and update the player's health.
 *
 * @author Jennifer Kim
 */

public class Fortress {
    private int health;

    public Fortress(int health){
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void updateHealth(int damage){
        health -= damage;
        if(health < 0){
            health = 0;
        }
    }
}