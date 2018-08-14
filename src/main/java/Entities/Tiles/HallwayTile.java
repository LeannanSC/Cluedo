package Entities.Tiles;

import Entities.Player;

public class HallwayTile extends Tile {

    public HallwayTile() {
        super("Hallway Tile", true);
    }

    @Override
    public String[] draw() {
        if (player != null) {
            String[] hallwayDraw = new String[3];
            hallwayDraw[0] = "   ";
            hallwayDraw[1] = " " + player.getColour().charAt(0) + " ";
            hallwayDraw[2] = "   ";

            return hallwayDraw;
        } else return new String[]{ "   ", "   ","   "};
    }

    public void setPlayer(Player player) {
        super.player = player;
    }
}