package Entities.Tiles;

import Entities.Player;

/**
 * General class for
 */
public abstract class Tile {

    //Tile Attributes
    private final String name;
    private final boolean canAccess;
    private String[] drawMethod = new String[3];
    Player player = null;


    public Tile(String name, boolean canAccess, String[] drawMethod) {
        this.name = name;
        this.canAccess = canAccess;
        this.drawMethod = drawMethod;
    }

    public String getName() {
        return name;
    }

    public boolean getCanAccess() {
        return canAccess;
    }

    public String[] getDrawMethod() {
        return drawMethod;
    }

    public void setDrawMethod(String[] drawMethod) {
        this.drawMethod = drawMethod;
    }

    public String toString() {
        return super.toString() + "[" +
                "name" + ":" + getName() + "," +
                "canAccess" + ":" + getCanAccess() + "]";
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}