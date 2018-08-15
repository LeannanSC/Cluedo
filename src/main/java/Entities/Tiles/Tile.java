package Entities.Tiles;

import Entities.Player;

/**
 * General class for tiles
 */
public abstract class Tile {

	//Tile Attributes
	private final String name;
	private final boolean canAccess;
	Player player = null;

	/**
	 * Constructor
	 *
	 * @param name      name of the room, used mostly for Room Tiles
	 * @param canAccess can the player access this tile?
	 */
	public Tile(String name, boolean canAccess) {
		this.name = name;
		this.canAccess = canAccess;
	}

	public String getName() {
		return name;
	}

	/**
	 * Draw method to be defined in specific tiles
	 */
	public abstract String[] draw();

	public boolean getCanAccess() {
		return canAccess;
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