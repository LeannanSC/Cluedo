package Views;

import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

/**
 * Class for displaying objects using graphics
 */
public class GraphicalView extends View {

	public GraphicalView() {
		super();
	}

	@Override
	public void drawBoard(Tile[][] board, int width, int height) {

	}

	@Override
	public void drawRoomTile(RoomTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {

	}

	@Override
	public void drawHallwayTile(HallwayTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {

	}

	@Override
	public void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {

	}
}