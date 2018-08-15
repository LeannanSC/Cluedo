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

	/**
	 * Gives the draw method of the tile, including the player icon if a player is present
	 *
	 * @return An array of 3 strings each 3 chars long, to represent the tile
	 */
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