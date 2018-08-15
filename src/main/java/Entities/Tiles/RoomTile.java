package Entities.Tiles;

import Entities.Player;
import Entities.WeaponToken;

/**
 * Represents the room tiles.
 * Specific tiles of the room act as doorways between the room and the hallway.
 */
public class RoomTile extends Tile {

	// Variables
	private boolean isDoorway = false;
	private WeaponToken weaponToken = null;

	/**
	 * Constructor
	 *
	 * @param name Name of the room type (e.g Study, Ballroom)
	 */
	public RoomTile(String name) {
		super(name, true);
	}

	/**
	 * Gives the draw method of the tile, including the player icon if a player is present
	 *
	 * @return An array of 3 strings each 3 chars long, to represent the tile
	 */
	@Override
	public String[] draw() {
		// Draw player if on tile
		if (player != null) {
			String[] hallwayDraw = new String[3];
			hallwayDraw[0] = "+++";
			hallwayDraw[1] = "+" + player.getColour().charAt(0) + "+";
			hallwayDraw[2] = "+++";
			return hallwayDraw;
		}
		// Draw weapon if on tile
		else if (weaponToken != null) {
			String[] hallwayDraw = new String[3];
			hallwayDraw[0] = "+ +";
			hallwayDraw[1] = " " + weaponToken.getIcon() + " ";
			hallwayDraw[2] = "+ +";
			return hallwayDraw;
		}
		// Draw differently if is doorway
		else if (isDoorway) {
			return new String[]{" 0 ", "000", " 0 "};
		} else return new String[]{"+++", "+++", "+++"};
	}

	// -------------------
	// GETTERS AND SETTERS
	// -------------------
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