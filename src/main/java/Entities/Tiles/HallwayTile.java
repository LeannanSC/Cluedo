package Entities.Tiles;

import Entities.Player;

/**
 * Represents hallway tiles on the board.
 * Hallway tiles are always accessible
 */
public class HallwayTile extends Tile {

	/**
	 * Constructor
	 */
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
		} else return new String[]{"   ", "   ", "   "};
	}

	public void setPlayer(Player player) {
		super.player = player;
	}
}