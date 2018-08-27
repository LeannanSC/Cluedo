package Views;

import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Abstract class for viewing the board
 */
public abstract class View extends JFrame implements WindowListener {

	public View() {
	}

	public View(String windowName) {
		super(windowName);
	}

	public abstract void drawBoard(Tile[][] board, int width, int height);

	public abstract void drawRoomTile(RoomTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void drawHallwayTile(HallwayTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public void printHand(Player p) {
	}

	public void printBoardInfo() {
	}

}