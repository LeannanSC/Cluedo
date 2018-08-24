package Entities.Tiles;

import Views.TextView;
import Views.View;


/**
 * Represents inaccessible tiles on the board (e.g Cellar)
 */
public class InaccessibleTile extends Tile {

	/**
	 * Constructor
	 */
	public InaccessibleTile() {
		super("Inaccessible Tile", false);
	}

}