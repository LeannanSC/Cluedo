import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Initialises loaded assets and making a coherent game
 * Also allows manipulation of the board
 */
public class Game {

	//Game Associations
	private final GameLoader gameLoader;
	private final List<Card> solutionCards = new ArrayList<>();
	private final List<Player> currentPlayers = new ArrayList<>();
	List<Point> placesMoved = new ArrayList<>();
	private Tile[][] board;

	/**
	 * Constructor
	 *
	 * @param numPlayers The number of human players
	 * @param gameLoader A newly constructed game setup
	 */
	public Game(int numPlayers, GameLoader gameLoader) {
		this.gameLoader = gameLoader;
		solutionCards.addAll(gameLoader.initSolution()); // always init solution before players
		currentPlayers.addAll(gameLoader.initPlayers(numPlayers));
		board = gameLoader.getBoard();
		System.out.println("Starting game!");
	}

	/**
	 * Simulates the rolling of two dice.
	 *
	 * @return Integer sum of 2 random numbers
	 */
	public int rollDice() {
		Random rng = new Random();
		int d1 = rng.nextInt(6) + 1; // 0 -> 5 so + 1
		int d2 = rng.nextInt(6) + 1;

		int moves = d1 + d2;
		if (d1 > 6 || d2 > 6 || moves > 12) {
			throw new Error("Dice rolled too high");
		}
		return moves;
	}

	/**
	 * Draws the whole board
	 */
	public void draw() {

		StringBuilder output = new StringBuilder();
		for (int y = 0; y < GameLoader.HEIGHT; y++) {
			StringBuilder line1 = new StringBuilder();
			StringBuilder line2 = new StringBuilder();
			StringBuilder line3 = new StringBuilder();
			for (int x = 0; x < GameLoader.WIDTH; x++) {
				line1.append(board[x][y].draw()[0]);
				line2.append(board[x][y].draw()[1]);
				line3.append(board[x][y].draw()[2]);
			}
			output.append(line1).append('\n')
					.append(line2).append('\n')
					.append(line3).append('\n');
		}

		System.out.println(output);
	}

	/**
	 * Moves the player to the neighbouring tile in the desired direction.
	 *
	 * @param player    The player to be moved
	 * @param direction The cardinal direction the player wants to move in
	 */
	public void movePlayer(Player player, String direction) {
		board[player.getLocation().x][player.getLocation().y].setPlayer(null);
		player.move(direction);
		Tile nextTile = board[player.getLocation().x][player.getLocation().y];
		nextTile.setPlayer(player);
		placesMoved.add(new Point(player.getLocation().x, player.getLocation().y));
	}

	/**
	 * Checks if the player can move in the selected direction
	 *
	 * @param p         The player to be moved
	 * @param direction The direction the player wants to move in
	 * @return The validity of movement (boolean)
	 */
	public boolean getCanMove(Player p, String direction) {
		Tile currentTile = board[p.getLocation().x][p.getLocation().y];
		Tile nextTile;
		Point nextTileLocation;

		switch (direction) {
			case "North":
				// if next tile is out of bounds
				try {
					nextTileLocation = new Point(p.getLocation().x, p.getLocation().y - 1);
					nextTile = board[nextTileLocation.x][nextTileLocation.y];
				} catch (ArrayIndexOutOfBoundsException ae) {
					return false;
				}

				//check for invalid tile
				if (nextTile instanceof InaccessibleTile) {
					return false;
				}

				// check if player is at next tile
				if (nextTile.getPlayer() != null) {
					return false;
				}

				// check if moved to the next tile before
				if (placesMoved.contains(nextTileLocation)) {
					return false;
				}

				// check for entrance on room enter
				if (currentTile instanceof HallwayTile && nextTile instanceof RoomTile) {
					return ((RoomTile) nextTile).isDoorway();
				}
				// check for entrance on room exit
				if (currentTile instanceof RoomTile && nextTile instanceof HallwayTile) {
					return ((RoomTile) currentTile).isDoorway();
				}
				return true;

			case "South":
				try {
					nextTileLocation = new Point(p.getLocation().x, p.getLocation().y + 1);
					nextTile = board[nextTileLocation.x][nextTileLocation.y];
				} catch (ArrayIndexOutOfBoundsException ae) {
					return false;
				}

				if (nextTile instanceof InaccessibleTile) {
					return false;
				}

				if (nextTile.getPlayer() != null) {
					return false;
				}

				// check if moved to the next tile before
				if (placesMoved.contains(nextTileLocation)) {
					return false;
				}

				// check for entrance on room enter
				if (currentTile instanceof HallwayTile && nextTile instanceof RoomTile) {
					return ((RoomTile) nextTile).isDoorway();
				}
				if (currentTile instanceof RoomTile && nextTile instanceof HallwayTile) {
					return ((RoomTile) currentTile).isDoorway();
				}

				return true;

			case "East":
				try {
					nextTileLocation = new Point(p.getLocation().x + 1, p.getLocation().y);
					nextTile = board[nextTileLocation.x][nextTileLocation.y];
				} catch (ArrayIndexOutOfBoundsException ae) {
					return false;
				}

				//check for invalid tile
				if (nextTile instanceof InaccessibleTile) {
					return false;
				}

				if (nextTile.getPlayer() != null) {
					return false;
				}

				if (placesMoved.contains(nextTileLocation)) {
					return false;
				}

				// check for entrance on room enter
				if (currentTile instanceof HallwayTile && nextTile instanceof RoomTile) {
					return ((RoomTile) nextTile).isDoorway();
				}
				if (currentTile instanceof RoomTile && nextTile instanceof HallwayTile) {
					return ((RoomTile) currentTile).isDoorway();
				}
				return true;

			case "West":
				try {
					nextTileLocation = new Point(p.getLocation().x - 1, p.getLocation().y);
					nextTile = board[nextTileLocation.x][nextTileLocation.y];
				} catch (ArrayIndexOutOfBoundsException ae) {
					return false;
				}
				if (nextTile instanceof InaccessibleTile) {
					return false;
				}

				if (nextTile.getPlayer() != null) {
					return false;
				}
				if (placesMoved.contains(nextTileLocation)) {
					return false;
				}

				if (currentTile instanceof HallwayTile && nextTile instanceof RoomTile) {
					return ((RoomTile) nextTile).isDoorway();
				}

				if (currentTile instanceof RoomTile && nextTile instanceof HallwayTile) {
					return ((RoomTile) currentTile).isDoorway();
				}
				return true;

			default:
				throw new Error("unexpected direction in move validity check");
		}
	}

	// ------
	// GETTER
	// ------
	public List<Player> getCurrentPlayers() {
		return currentPlayers;
	}

    public boolean getCanRefute(Player player, CharacterCard characterCard, WeaponCard weaponCard, RoomCard roomCard) {
	   if( player.getCardsInHand().contains(characterCard) || player.getCardsInHand().contains(weaponCard) || player.getCardsInHand().contains(weaponCard)){
	       return true;
        }
        return false;
    }
}