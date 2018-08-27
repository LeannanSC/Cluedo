import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Class for displaying objects using graphics
 */
public class GraphicalView extends View {

	// Image Icons
	// MERCIFUL GOD IN HEAVEN THIS IS A NIGHTMARE
	private static ImageIcon inaccessibleTile = makeImageIcon("inaccessible-tile.png");
	private static ImageIcon hallTileEmpty = makeImageIcon("hall-tile.png");
	private static ImageIcon hallTileDoor = makeImageIcon("door-hall-tile.png");
	private static ImageIcon hallTileGreen = makeImageIcon("green-hall-tile.png");
	private static ImageIcon hallTileRed = makeImageIcon("red-hall-tile.png");
	private static ImageIcon hallTileBlue = makeImageIcon("blue-hall-tile.png");
	private static ImageIcon hallTileWhite = makeImageIcon("white-hall-tile.png");
	private static ImageIcon hallTilePurple = makeImageIcon("purple-hall-tile.png");
	private static ImageIcon hallTileYellow = makeImageIcon("yellow-hall-tile.png");
	private static ImageIcon roomTileEmpty = makeImageIcon("room-tile.png");
	private static ImageIcon roomTileDoor = makeImageIcon("door-room-tile.png");
	private static ImageIcon roomTileGreen = makeImageIcon("green-room-tile.png");
	private static ImageIcon roomTileRed = makeImageIcon("red-room-tile.png");
	private static ImageIcon roomTileBlue = makeImageIcon("blue-room-tile.png");
	private static ImageIcon roomTileWhite = makeImageIcon("white-room-tile.png");
	private static ImageIcon roomTilePurple = makeImageIcon("purple-room-tile.png");
	private static ImageIcon roomTileYellow = makeImageIcon("yellow-room-tile.png");
	private static ImageIcon roomTileCandle = makeImageIcon("candle-room-tile.png");
	private static ImageIcon roomTileDagger = makeImageIcon("dagger-room-tile.png");
	private static ImageIcon roomTilePipe = makeImageIcon("pipe-room-tile.png");
	private static ImageIcon roomTileRevolver = makeImageIcon("revolver-room-tile.png");
	private static ImageIcon roomTileRope = makeImageIcon("rope-room-tile.png");
	private static ImageIcon roomTileSpanner = makeImageIcon("spanner-room-tile.png");

	private JPanel fullSetup = new JPanel();
	private JPanel displayBoard = new JPanel();

	public GraphicalView() {
		super("Cluedo");
		redraw();
	}

	private void redraw() {
		makeFullSetup();
		add(displayBoard);
		addWindowListener(this);
		pack();
		setVisible(true);
	}

	@Override
	public void drawBoard(Tile[][] board, int width, int height) {
		displayBoard = new JPanel(new GridLayout(height,width));
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				displayBoard.add(new JLabel(getTileIcon(board[y][x])));
			}
		}
		displayBoard.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		redraw();
	}

	/**
	 * This method gives the appropriate icon based on the tile and its content
	 *
	 * @param tile The tile to grab the image for
	 * @return
	 */
	private ImageIcon getTileIcon(Tile tile) {
		// HALLWAY TILES
		switch (tile.getName()) {
			case "Hallway Tile":
				// If there is a character on the tile
				if (tile.getPlayer() != null) {
					switch (tile.getPlayer().getColour()) {
						case "Red":
							return hallTileRed;
						case "Yellow":
							return hallTileYellow;
						case "White":
							return hallTileWhite;
						case "Green":
							return hallTileGreen;
						case "Blue":
							return hallTileBlue;
						case "Purple":
							return hallTilePurple;
						default:
							return null;
					}
				} else {// Return Empty Hall Tile
					return hallTileEmpty;
				}
				// INACCESSIBLE TILES
			case "Inaccessible Tile": {
				return inaccessibleTile;
			}
			// If it is neither, it must be a Room Tile
			default:
				if (tile.getPlayer() != null) {
					switch (tile.getPlayer().getColour()) {
						case "Red":
							return roomTileRed;
						case "Yellow":
							return roomTileYellow;
						case "White":
							return roomTileWhite;
						case "Green":
							return roomTileGreen;
						case "Blue":
							return roomTileBlue;
						case "Purple":
							return roomTilePurple;
					}
				}
				if (((RoomTile)tile).isDoorway()){
					return roomTileDoor;
				}
				if (((RoomTile) tile).getWeaponToken() != null) {
					switch (((RoomTile) tile).getWeaponToken().getName()) {
						case "Candlestick":
							return roomTileCandle;
						case "Dagger":
							return roomTileDagger;
						case "Lead Pipe":
							return roomTilePipe;
						case "Revolver":
							return roomTileRevolver;
						case "Rope":
							return roomTileRope;
						case "Spanner":
							return roomTileSpanner;
					}
				}
				return roomTileEmpty;
		}
	}

	private void makeFullSetup() {
		fullSetup = new JPanel();
		JPanel dice = new JPanel();
		JPanel info = new JPanel();
		JPanel cards = new JPanel();
		JPanel actions = new JPanel();
		fullSetup.add(dice);
	}

	/**
	 * Method for loading icons. Taken from SWEN225 Lab 3 code
	 *
	 * @param path name of .png file
	 * @return ImageIcon of the desired file
	 */
	private static ImageIcon makeImageIcon(String path) {
		URL imgURL = null;
		try {
			imgURL = Paths.get(path).toUri().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * The following are part of WindowListener, but not needed yet
	 *
	 * @param e
	 */
	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

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