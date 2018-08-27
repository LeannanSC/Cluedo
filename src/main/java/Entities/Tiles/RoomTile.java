package Entities.Tiles;

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