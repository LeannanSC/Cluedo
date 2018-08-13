package Entities.Tiles;

import Entities.Player;

public class HallwayTile extends Tile {

    public HallwayTile() {
        super("Hallway Tile", true,new String[]{ "   ", "   ","   "});
    }

    public String[] playerHere(){
        if (player != null) {
            String[] hallwayDraw = new String[3];
            hallwayDraw[0] = "   ";
            hallwayDraw[1] = " " + player.getCharacterName().charAt(0) + " ";
            hallwayDraw[2] = "   ";

            return hallwayDraw;
        } else return new String[]{ "   ", "   ","   "};
    }

    public void setPlayer(Player player) {
        super.player = player;
        super.setDrawMethod(playerHere());
    }
}