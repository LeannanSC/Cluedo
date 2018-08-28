import Entities.Cards.Card;
import Entities.Commands.Action;
import Entities.Commands.Suggestion;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Class for displaying objects using graphics
 */
public class GraphicalView extends View {

	/*# Image Icons #*/
	private ImageIcon inaccessibleTile = makeImageIcon("inaccessible-tile.png");
	private ImageIcon hallTileEmpty = makeImageIcon("hall-tile.png");
	private ImageIcon hallTileDoor = makeImageIcon("door-hall-tile.png");
	private ImageIcon hallTileGreen = makeImageIcon("green-hall-tile.png");
	private ImageIcon hallTileRed = makeImageIcon("red-hall-tile.png");
	private ImageIcon hallTileBlue = makeImageIcon("blue-hall-tile.png");
	private ImageIcon hallTileWhite = makeImageIcon("white-hall-tile.png");
	private ImageIcon hallTilePurple = makeImageIcon("purple-hall-tile.png");
	private ImageIcon hallTileYellow = makeImageIcon("yellow-hall-tile.png");
	private ImageIcon roomTileEmpty = makeImageIcon("room-tile.png");
	private ImageIcon roomTileDoor = makeImageIcon("door-room-tile.png");
	private ImageIcon roomTileGreen = makeImageIcon("green-room-tile.png");
	private ImageIcon roomTileRed = makeImageIcon("red-room-tile.png");
	private ImageIcon roomTileBlue = makeImageIcon("blue-room-tile.png");
	private ImageIcon roomTileWhite = makeImageIcon("white-room-tile.png");
	private ImageIcon roomTilePurple = makeImageIcon("purple-room-tile.png");
	private ImageIcon roomTileYellow = makeImageIcon("yellow-room-tile.png");
	private ImageIcon roomTileCandle = makeImageIcon("candle-room-tile.png");
	private ImageIcon roomTileDagger = makeImageIcon("dagger-room-tile.png");
	private ImageIcon roomTilePipe = makeImageIcon("pipe-room-tile.png");
	private ImageIcon roomTileRevolver = makeImageIcon("revolver-room-tile.png");
	private ImageIcon roomTileRope = makeImageIcon("rope-room-tile.png");
	private ImageIcon roomTileSpanner = makeImageIcon("spanner-room-tile.png");
	private ImageIcon dice0 = makeImageIcon("dice-0.png");
	private ImageIcon dice1 = makeImageIcon("dice-1.png");
	private ImageIcon dice2 = makeImageIcon("dice-2.png");
	private ImageIcon dice3 = makeImageIcon("dice-3.png");
	private ImageIcon dice4 = makeImageIcon("dice-4.png");
	private ImageIcon dice5 = makeImageIcon("dice-5.png");
	private ImageIcon dice6 = makeImageIcon("dice-6.png");

	private JFrame mainFrame;      // The overall view of the window, add components to this
	private JMenuBar menuBar;      // The menu bar,
	private JPanel boardArea;    	// The board to display to the user of the GUI
	private JPanel bottomPanel;		// The bottom panel where the dice and cards are displayed
	private JPanel dicePanel;      // The dice to display to the user
	private JPanel playerCards;    // The players cards in the players hand.
	private JPanel text;

	/**
	 * Constructor, starts the GUI
	 */
	public GraphicalView() {
		mainFrame = new JFrame("Cluedo");
		mainFrame.pack();
		setupGUI();
	}

	/**
	 * Sets up the GUI first time round
	 */
	private void setupGUI() {
		mainFrame.setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		menuBar.add(new JMenu("MENU BUTTON 1"));
		menuBar.add(new JMenu("MENU BUTTON 2"));
		bottomPanel = new JPanel();
		dicePanel = new JPanel();
		text = new JPanel();
		setDicePanel(0,0);
		playerCards = new JPanel();
		bottomPanel.add(dicePanel,FlowLayout.LEFT);
		bottomPanel.add(playerCards,FlowLayout.CENTER);
		bottomPanel.add(text,FlowLayout.RIGHT);
		mainFrame.add(menuBar, BorderLayout.PAGE_START);
		mainFrame.add(bottomPanel,BorderLayout.PAGE_END);
		mainFrame.setVisible(true);
	}

	/**
	 * Redraws the updated frame
	 *
	 * @param game
	 */
	@Override
	public void redraw(Game game) {
		mainFrame.getContentPane().removeAll();
		drawBoard(game.getBoard(), GameLoader.WIDTH, GameLoader.HEIGHT);
		printHand(game.currentPlayer);
		printNewTurnText(game.currentPlayer);
		bottomPanel.removeAll();
		bottomPanel.add(dicePanel);
		bottomPanel.add(playerCards);
		bottomPanel.add(text);
		bottomPanel.setBorder(BorderFactory.createBevelBorder(0,Color.GRAY,Color.GRAY));
		mainFrame.add(menuBar, BorderLayout.PAGE_START);
		mainFrame.add(bottomPanel,BorderLayout.SOUTH);
		mainFrame.pack();
//		mainFrame.getContentPane().removeAll();
//		setupGUI();
//		boardArea = new JPanel(false);
//		boardArea.setBorder(BorderFactory.createEmptyBorder());
//		mainFrame.add(boardArea,BorderLayout.WEST);
	}

	/**
	 *
	 */
	public void setDicePanel(int dice1,int dice2) {
		dicePanel = new JPanel();
		dicePanel.add(new JLabel(getDiceIcon(dice1)));
		dicePanel.add(new JLabel(getDiceIcon(dice2)));
		dicePanel.setPreferredSize(new Dimension(120,120));
		dicePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}

	/**
	 * This method gives the appropriate icon based on the dice
	 * @param diceInt The number of the dice roll
	 * @return
	 */
	private ImageIcon getDiceIcon(int diceInt) {
		if (diceInt==1){return dice1;}
		if (diceInt==2){return dice2;}
		if (diceInt==3){return dice3;}
		if (diceInt==4){return dice4;}
		if (diceInt==5){return dice5;}
		if (diceInt==6){return dice6;}
		return dice0;
	}

	/**
	 * Receives input from the player
	 * @param arrayOptionSize
	 * @return
	 */
	@Override
	public int getInput(int arrayOptionSize) {
		Object[] options;
			if (arrayOptionSize == Action.values().length) {
				options = Action.values();
			} else if (arrayOptionSize == Suggestion.values().length){
				options = Suggestion.values();
			} else throw new Error("error in get input");

		int input = JOptionPane.showOptionDialog(mainFrame,
				null,
				"Please Select Command: ",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		System.out.println(input+1);
		return input +1 ;
	}

	/**
	 * Sets up display board
	 *
	 * @param board
	 * @param width
	 * @param height
	 */
	@Override
	public void drawBoard(Tile[][] board, int width, int height) {
		new TextView().drawBoard(board, width, height); // test fixme
		System.out.println("Print Draw");
		boardArea = new JPanel(new GridLayout(height, width));
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				JLabel addThis = new JLabel(getTileIcon(board[y][x]),JLabel.CENTER);
				addThis.setMinimumSize(new Dimension(15,15));
				boardArea.add(addThis);
			}
		}
		System.out.println(boardArea.contains(1, 1));
		boardArea.setMaximumSize(new Dimension(15*width,15*height));
		boardArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainFrame.add(boardArea,BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}

	/**
	 * This method gives the appropriate icon based on the tile and its content
	 *
	 * @param tile The tile to grab the image for
	 * @return The image for the tile
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
				if (((RoomTile) tile).isDoorway()) {
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

	/**
	 * Method for loading icons. Taken from SWEN225 Lab 3 code
	 *
	 * @param path name of .png file
	 * @return ImageIcon of the desired file
	 */
	private ImageIcon makeImageIcon(String path) {
		java.net.URL imageURL = GraphicalView.class.getResource(path);
		if (imageURL != null) {
			return new ImageIcon(imageURL);
		}
		return null;
	}

	@Override
	public int getPlayers() {
		String input = JOptionPane.showInputDialog("Please Enter number of players(3-6): ");
		System.out.println(input);
		int numplayers = Integer.parseInt(input);
		if (numplayers > 6 || numplayers < 3) {
			JOptionPane.showMessageDialog(mainFrame, "invalid Input");
			return getPlayers();
		}
		return numplayers;
	}

	public static void main(String[] args) {
		GraphicalView v = new GraphicalView();
		new Controller(v).initController();
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

	@Override
	public void printCommandMenu(Player player, Game game) {

	}

	@Override
	public void printSuggestionMenu(Game game) {

	}

	@Override
	public void printRefutationMenu(Player player, Game game) {

	}

	@Override
	public void printRefuteCardSelectionMenu(Player player) {

	}

	@Override
	public void printAllCharacterCards(Game game) {

	}

	@Override
	public void printAllWeaponCards(Game game) {

	}

	@Override
	public void printAllRoomCards(Game game) {

	}

	@Override
	public void printBoardInfo() {

	}

	@Override
	public void printError(String error) {

	}

	@Override
	public void printHand(Player p) {
		playerCards = new JPanel();
		playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.Y_AXIS));
		playerCards.add(new JLabel("YOUR CARDS:"));
		for (Card card:p.getCardsInHand()) {
			playerCards.add(new JLabel(card.getCardName()));
		}
		playerCards.setPreferredSize(new Dimension(120,120));
		playerCards.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}

	@Override
	public void printRefutations(Game game) {

	}

	@Override
	public void printWinText(Player player) {

	}

	@Override
	public void printLoseText(List<Card> solution) {

	}

	@Override
	public void printNewTurnText(Player currentPlayer) {
		text = new JPanel();
		text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
		text.add(new JLabel("You are playing as " + currentPlayer.getCharacterName()));
		text.add(new JLabel("Your colour token is " + currentPlayer.getColour().toLowerCase()));
		text.add(new JLabel("You have " + currentPlayer.getMovesRemaining() + " steps remaining"));
		text.setPreferredSize(new Dimension(200,120));
		text.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	}

	@Override
	public void printEndTurnText() {

	}

	@Override
	public void printPassInstruction(Player nextPlayer) {

	}
}