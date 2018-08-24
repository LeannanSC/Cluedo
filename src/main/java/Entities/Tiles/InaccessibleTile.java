package Entities.Tiles;


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