package Entities.Tiles;

import Entities.Player;
import Entities.WeaponToken;

public class RoomTile extends Tile {


    private boolean isDoorway = false;
    private WeaponToken weaponToken = null;

    public RoomTile(String name) {
        super(name, true);
    }

    @Override
    public String[] draw() {
        if (player != null) {
            String[] hallwayDraw = new String[3];
            hallwayDraw[0] = "+++";
            hallwayDraw[1] = "+" + player.getColour().charAt(0) + "+";
            hallwayDraw[2] = "+++";
            return hallwayDraw;
        } else if (weaponToken != null) {
            String[] hallwayDraw = new String[3];
            hallwayDraw[0] = "+ +";
            hallwayDraw[1] = " " + weaponToken.getIcon() + " ";
            hallwayDraw[2] = "+ +";
            return hallwayDraw;
        } else if (isDoorway) {
            return new String[]{" 0 ", "000", " 0 "};
        } else return new String[]{"+++", "+++", "+++"};
    }

    public void setPlayer(Player player) {
        super.player = player;
    }

    public WeaponToken getWeaponToken() {
        return weaponToken;
    }

    public void setWeaponToken(WeaponToken weaponToken) {
        this.weaponToken = weaponToken;
    }

    public boolean isDoorway() {
        return isDoorway;
    }

    public void setDoorway(boolean doorway) {
        isDoorway = doorway;
    }
}