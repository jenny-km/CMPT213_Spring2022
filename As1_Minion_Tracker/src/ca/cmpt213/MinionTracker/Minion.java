/*
    Minion Tracker - Assignment 1 by Jennifer Kim
    * Minion Description *
    --> This class setups the attributes / features of the minion such as name, height, and
        evil deeds. It also contains getter and setters so that the main class could access
        or change the minion's information. Lastly, it contains an override of toString()
        for the purpose of the dump object to String option.
*/
package ca.cmpt213.MinionTracker;

public class Minion {

    private String name;
    private double height;
    private int evilDeedCount;

    public Minion (String name, double height, int evilDeedCount){
        this.name = name;
        this.height = height;
        this.evilDeedCount = evilDeedCount;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public int getEvilDeedCount(){
        return evilDeedCount;
    }

    public void setEvilDeedCount(int evilDeedCount){
        this.evilDeedCount = evilDeedCount;
    }

    @Override
    public String toString(){
        return getClass().getName() +
                "[" + "Name:" + name + ", " +
                "Evil Deeds:" + evilDeedCount + ", " +
                "Height:" + height + "]";
    }

}
