package Entities.Tiles;

/**
 * Represents inaccessible tiles on the board (e.g Cellar)
 */
public class InaccessibleTile extends Tile {

	public InaccessibleTile() {
		super("Inaccessible Tile", false);
	}

	@Override
	public String[] draw() {
		return new String[]{"XXX", "XXX", "XXX"};
	}

}