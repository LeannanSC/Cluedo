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

	/**
	 * Gives the draw method of the tile
	 *
	 * @return An array of 3 strings each 3 chars long, to represent the tile
	 */
	@Override
	public String[] draw() {
		return new String[]{"XXX", "XXX", "XXX"};
	}

}