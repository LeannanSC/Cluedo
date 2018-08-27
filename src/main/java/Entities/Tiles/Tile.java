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

	public Tile(String name, boolean canAccess) {
		this.name = name;
		this.canAccess = canAccess;
	}

	// -------------------
	// GETTERS AND SETTERS
	// -------------------
	public Player getPlayer() {
		return player;
	}

	public boolean getCanAccess() {
		return canAccess;
	}

	public String getName() {
		return name;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String toString() {
		return super.toString() + "[" +
				"name" + ":" + getName() + "," +
				"canAccess" + ":" + getCanAccess() + "]";
	}
}