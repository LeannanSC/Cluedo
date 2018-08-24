package Entities.Tiles;

import Entities.Player;
import Views.TextView;
import Views.View;

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

}