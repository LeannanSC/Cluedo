import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;
import Entities.WeaponToken;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Initialise the game variables and loads in the assets according to the specification
 */
public class GameLoader {

	// Constants
	public static final int WIDTH = 24;
	public static final int HEIGHT = 25;

	// Variables
	private List<RoomCard> roomCards;
	private List<CharacterCard> chrCards;
	private List<WeaponCard> weapCards;

	private List<WeaponToken> weaponTokens;

	private List<Player> allCharacterPool;
	private List<Card> allCardPool;
	private Tile[][] board;
	private List<Card> remainingCards;

	/**
	 * Constructor, calls load and initialise methods
	 */
	public GameLoader() {
		loadTiles();
		loadCards();
		loadAvailableCharacters();
		loadWeaponTokens();
		initWeaponTokens();
		System.out.println("Assets loaded ...");
	}

	/**
	 * Puts the weapon tokens randomly on the board at the beginning
	 */
	public void initWeaponTokens() {
		Random rng = new Random();

		List<RoomCard> tempRooms = new ArrayList<>(roomCards);
		List<WeaponToken> clone = new ArrayList<>(weaponTokens);
		// Go through all weapon tokens
		while (!clone.isEmpty()) {
			int room = rng.nextInt(tempRooms.size() - 1);

			for (int y = 0; y < HEIGHT; y++) {
				for (int x = 0; x < WIDTH; x++) {
					if (clone.size() == 0) {
						break;
					}

					if (board[x][y].getName().equals(tempRooms.get(room).getCardName())) {
						if (!((RoomTile) board[x][y]).isDoorway()) {
							((RoomTile) board[x][y]).setWeaponToken(clone.remove(rng.nextInt(clone.size())));
							tempRooms.remove(room);
						}
					}
				}
			}
		}
	}

	/**
	 * Distributes all remaining cards to the players after the solution is picked
	 *
	 * @param currentPlayers The players
	 */
	public void initPlayerHands(List<Player> currentPlayers) {
		Random rng = new Random();

		// can now deal with uneven hand size
		while (!remainingCards.isEmpty()) {
			for (Player p : currentPlayers) {
				int size = remainingCards.size();
				if (size == 0) {
					break;
				}
				Card randomCard = remainingCards.get(rng.nextInt(size));
				p.addCardToHand(randomCard);
				remainingCards.remove(randomCard);
			}
		}

		if (currentPlayers.get(0).getCardsInHand().size() == 0) {
			throw new Error("No cards added to hand");
		}
	}


	/**
	 * Initialises the players, including their hands
	 *
	 * @param numPlayers Number of human players, must be between 3 and 6
	 * @return A list of all the initialised players
	 */
	public List<Player> initPlayers(int numPlayers) {
		Random rng = new Random();

		if (numPlayers < 3 || numPlayers > 6) {
			throw new Error("Invalid Player Amount");
		}

		List<Player> allPlayerCopy = new ArrayList<>(allCharacterPool);
		List<Player> currentPlayers = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++) {
			Player newPlayer = allPlayerCopy.remove(rng.nextInt(allPlayerCopy.size()));
			board[newPlayer.getLocation().x][newPlayer.getLocation().y].setPlayer(newPlayer);
			currentPlayers.add(newPlayer);
		}

		initPlayerHands(currentPlayers);

		if (remainingCards.size() != 0) {
			throw new Error("should have 0 remaining cards after all hands initialised size is: " + remainingCards.size());
		}
		return currentPlayers;
	}

	/**
	 * Randomly choose the murder solution from all available cards
	 * Must comprise of one weapon, one room, and one character
	 *
	 * @return A list of the three solution cards
	 */
	public List<Card> initSolution() {
		Random rng = new Random();
		//Randomly select availableCardPool from their respective type pool
		List<Card> solutionCards = new ArrayList<>();
		solutionCards.add(getRoomCardAtIndex(rng.nextInt(roomCards.size())));
		solutionCards.add(getChrCardAtIndex(rng.nextInt(chrCards.size())));
		solutionCards.add(getWeaponCardAtIndex(rng.nextInt(weapCards.size())));

		// keep list of remaining cards
		remainingCards = new ArrayList<>(allCardPool);
		for (Card c : solutionCards) {
			remainingCards.remove(c);
		}

		if (remainingCards.size() != 18) {
			throw new Error("should have 18 remaining cards after solution initialised");
		}
		return solutionCards;
	}

	/**
	 * Loads all cards into the game
	 */
	private void loadCards() {
		// load allCardPool into arrays grouped by type

		roomCards = new ArrayList<>();
		roomCards.add(new RoomCard("Ballroom"));
		roomCards.add(new RoomCard("Kitchen"));
		roomCards.add(new RoomCard("Dining Room"));
		roomCards.add(new RoomCard("Lounge"));
		roomCards.add(new RoomCard("Hall"));
		roomCards.add(new RoomCard("Study"));
		roomCards.add(new RoomCard("Library"));
		roomCards.add(new RoomCard("Billiard Room"));
		roomCards.add(new RoomCard("Conservatory"));

		chrCards = new ArrayList<>();
		chrCards.add(new CharacterCard("Miss Scarlett"));
		chrCards.add(new CharacterCard("Colonel Mustard"));
		chrCards.add(new CharacterCard("Mrs. White"));
		chrCards.add(new CharacterCard("Mrs. White"));
		chrCards.add(new CharacterCard("Mr. Green"));
		chrCards.add(new CharacterCard("Professor Plum"));

		weapCards = new ArrayList<>();
		weapCards.add(new WeaponCard("Candlestick"));
		weapCards.add(new WeaponCard("Dagger"));
		weapCards.add(new WeaponCard("Lead Pipe"));
		weapCards.add(new WeaponCard("Revolver"));
		weapCards.add(new WeaponCard("Rope"));
		weapCards.add(new WeaponCard("Spanner"));

		allCardPool = new ArrayList<>();
		allCardPool.addAll(roomCards);
		allCardPool.addAll(chrCards);
		allCardPool.addAll(weapCards);
	}

	/**
	 * Loads all characters that are available to play at launch
	 */
	private void loadAvailableCharacters() {
		//load all playable characters
		allCharacterPool = new ArrayList<>();
		allCharacterPool.add(new Player(new Point(7, 24), "Miss Scarlett", "Red"));
		allCharacterPool.add(new Player(new Point(0, 17), "Colonel Mustard", "Yellow"));
		allCharacterPool.add(new Player(new Point(9, 0), "Mrs. White", "White"));
		allCharacterPool.add(new Player(new Point(14, 0), "Mr. Green", "Green"));
		allCharacterPool.add(new Player(new Point(23, 6), "Mrs. Peacock", "Blue"));
		allCharacterPool.add(new Player(new Point(23, 19), "Professor Plum", "Purple"));
	}

	/**
	 * Loads all weapon tokens
	 */
	private void loadWeaponTokens() {
		//load all weapons
		weaponTokens = new ArrayList<>();
		weaponTokens.add(new WeaponToken("Candlestick", "*"));
		weaponTokens.add(new WeaponToken("Dagger", "%"));
		weaponTokens.add(new WeaponToken("Lead Pipe", "1"));
		weaponTokens.add(new WeaponToken("Revolver", "q"));
		weaponTokens.add(new WeaponToken("Rope", "&"));
		weaponTokens.add(new WeaponToken("Spanner", "!"));
	}

	/**
	 * Loads the tiles of the board.
	 * Each are a room, hallway, or inaccessible.
	 */
	private void loadTiles() {

		board = new Tile[WIDTH][HEIGHT];

		// Ballroom
		for (int x = 10; x < 14; x++) {
			board[x][1] = new RoomTile("Ballroom");

		}
		for (int x = 8; x < 16; x++) {
			for (int y = 2; y < 8; y++) {
				board[x][y] = new RoomTile("Ballroom");
			}
		}
		((RoomTile) board[8][5]).setDoorway(true);
		((RoomTile) board[9][7]).setDoorway(true);
		((RoomTile) board[15][5]).setDoorway(true);
		((RoomTile) board[14][7]).setDoorway(true);

		// Kitchen
		for (int x = 0; x < 6; x++) {
			for (int y = 1; y < 7; y++) {
				board[x][y] = new RoomTile("Kitchen");
			}
		}
		((RoomTile) board[4][6]).setDoorway(true);

		// Dining Room
		for (int x = 0; x < 5; x++) {
			board[x][9] = new RoomTile("Dining Room");
		}
		for (int x = 0; x < 8; x++) {
			for (int y = 10; y < 16; y++) {
				board[x][y] = new RoomTile("Dining Room");
			}
		}
		((RoomTile) board[7][12]).setDoorway(true);
		((RoomTile) board[6][15]).setDoorway(true);

		// Lounge
		for (int x = 0; x < 7; x++) {
			for (int y = 19; y < 25; y++) {
				board[x][y] = new RoomTile("Lounge");
			}
		}
		((RoomTile) board[6][19]).setDoorway(true);

		// Hall
		for (int x = 9; x < 15; x++) {
			for (int y = 18; y < 25; y++) {
				board[x][y] = new RoomTile("Hall");
			}
		}
		((RoomTile) board[11][18]).setDoorway(true);
		((RoomTile) board[12][18]).setDoorway(true);
		((RoomTile) board[14][20]).setDoorway(true);

		// Study
		for (int x = 17; x < 24; x++) {
			for (int y = 21; y < 25; y++) {
				board[x][y] = new RoomTile("Study");
			}
		}
		((RoomTile) board[17][21]).setDoorway(true);

		// Library
		for (int x = 18; x < 23; x++) {
			for (int y = 14; y < 19; y++) {
				board[x][y] = new RoomTile("Library");
			}
		}
		for (int x = 17; x < 24; x++) {
			for (int y = 15; y < 18; y++) {
				if (board[x][y] == null) {
					board[x][y] = new RoomTile("Library");
				}
			}
		}
		((RoomTile) board[20][14]).setDoorway(true);
		((RoomTile) board[17][16]).setDoorway(true);

		// Billiard Room
		for (int x = 18; x < 24; x++) {
			for (int y = 8; y < 13; y++) {
				board[x][y] = new RoomTile("Billiard Room");
			}
		}
		((RoomTile) board[18][9]).setDoorway(true);
		((RoomTile) board[22][12]).setDoorway(true);

		// Conservatory
		for (int x = 18; x < 24; x++) {
			for (int y = 1; y < 5; y++) {
				board[x][y] = new RoomTile("Conservatory");
			}
		}
		for (int x = 19; x < 23; x++) {
			board[x][5] = new RoomTile("Conservatory");
		}
		((RoomTile) board[18][4]).setDoorway(true);


		// INACCESSIBLE (It does replace rooms as it goes)
		// Top side of board
		for (int x = 0; x < WIDTH; x++) {
			if (x != 9 && x != 14) {
				board[x][0] = new InaccessibleTile();
			}
		}
		board[6][1] = new InaccessibleTile();
		board[17][1] = new InaccessibleTile();

		// Left side of board
		board[0][6] = new InaccessibleTile();
		board[0][8] = new InaccessibleTile();
		board[0][16] = new InaccessibleTile();
		board[0][18] = new InaccessibleTile();
		board[0][19] = new InaccessibleTile();

		// Right side of board
		board[23][5] = new InaccessibleTile();
		board[23][7] = new InaccessibleTile();
		board[23][13] = new InaccessibleTile();
		board[23][14] = new InaccessibleTile();
		board[23][18] = new InaccessibleTile();
		board[23][20] = new InaccessibleTile();
		board[23][21] = new InaccessibleTile();

		// Bottom side of board
		board[6][24] = new InaccessibleTile();
		board[8][24] = new InaccessibleTile();
		board[15][24] = new InaccessibleTile();
		board[17][24] = new InaccessibleTile();

		// Cellar (inaccessible)
		for (int x = 10; x < 15; x++) {
			for (int y = 10; y < 17; y++) {
				board[x][y] = new InaccessibleTile();
			}
		}

		// HALLWAY (All remaining tiles)
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (board[x][y] == null) {
					board[x][y] = new HallwayTile();
				}
			}
		}

	}

	// -------
	// GETTERS
	// -------
	public List<WeaponToken> getWeaponTokens() {
		return weaponTokens;
	}

	public Tile[][] getBoard() {
		return board;
	}

	public RoomCard getRoomCardAtIndex(int idx) {
		return roomCards.get(idx);
	}

    public List<RoomCard> getRoomCards() {
        return roomCards;
    }

    public List<CharacterCard> getChrCards() {
        return chrCards;
    }

    public List<WeaponCard> getWeapCards() {
        return weapCards;
    }

    public CharacterCard getChrCardAtIndex(int idx) {
		return chrCards.get(idx);
	}

	public WeaponCard getWeaponCardAtIndex(int idx) {
		return weapCards.get(idx);
	}
}