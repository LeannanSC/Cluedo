package Entities.Tiles;

import Entities.Player;

public class RoomTile extends Tile {


    private boolean hasEntry;
    public RoomTile(String name) {
        super(name, true, new String[]{"+++", "+++", "+++"});
    }

    public String[] playerHere(){
        if (player != null) {
            String[] hallwayDraw = new String[3];
            hallwayDraw[0] = "+++";
            hallwayDraw[1] = "+" + player.getCharacterName().charAt(0) + "+";
            hallwayDraw[2] = "+++";
            return hallwayDraw;
        } else return new String[]{"+++", "+++", "+++"};
    }

    public void setPlayer(Player player) {
        super.player = player;
        super.setDrawMethod(playerHere());
    }
}