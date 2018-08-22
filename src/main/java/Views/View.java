package Views;

import Entities.Player;
import Entities.Tiles.Tile;

/**
 * Abstract class for viewing the board
 */
public abstract class View {
	// FIXME: Extended classes unused

	public View() {
	}

	public void redraw() {
	}

	public abstract void drawBoard(Tile[][] board, int width, int height);

	public void printHand(Player p){}

	public void printBoardInfo() {}

}