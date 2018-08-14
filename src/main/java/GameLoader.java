import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;
import Entities.WeaponTokens;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoader {
    public static final int width = 24;
    public static final int height = 25;

    private List<RoomCard> roomCards;
    private List<CharacterCard> chrCards;
    private List<WeaponCard> weapCards;

    // todo ask sophie about
    private List<WeaponTokens> weaponTokens;

    private List<Player> allCharacterPool;
    private List<Card> allCardPool;
    private Tile[][] board;

    private List<Player> availableCharacterPool; // fixme may not need?
    private List<Card> remainingCards;

    public GameLoader() {

        loadTiles();

        loadCards();

        loadAvailableCharacters();

        loadWeaponTokens();


        System.out.println("Assets loaded ...");
    }

    public void initPlayerHands(int handSize, List<Player> currentPlayers) {
        Random rng = new Random();

        // can now deal with uneven hand size
        while (!remainingCards.isEmpty()) {
            for (Player p : currentPlayers) {
                int size = remainingCards.size();
                if (size == 0){
                    break;
                }
                Card randomCard = remainingCards.remove(rng.nextInt(size));
                p.addCardToHand(randomCard);
                remainingCards.remove(randomCard);
                System.out.println("cards set: " + p.getCardsInHand().size());
            }
        }

        if (currentPlayers.get(0).getCardsInHand().size() == 0) {
            throw new Error("No cards added to hand");
        }
    }


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

        initPlayerHands(remainingCards.size() / currentPlayers.size(), currentPlayers);

        if (remainingCards.size() != 0) {
            throw new Error("should have 0 remaining cards after all hands initialised size is: " + remainingCards.size());
        }
        return currentPlayers;
    }

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

    private void loadWeaponTokens() {
        //load all weapons
        weaponTokens = new ArrayList<>();
        weaponTokens.add(new WeaponTokens("Candlestick", "grey"));
        weaponTokens.add(new WeaponTokens("Dagger", "grey"));
        weaponTokens.add(new WeaponTokens("Lead Pipe", "grey"));
        weaponTokens.add(new WeaponTokens("Dagger", "grey"));
        weaponTokens.add(new WeaponTokens("Revolver", "grey"));
        weaponTokens.add(new WeaponTokens("Rope", "grey"));
        weaponTokens.add(new WeaponTokens("Spanner", "grey"));
    }

    private void loadTiles() {
//        String[] inaccessibleDraw = new String[3];
//        inaccessibleDraw[0] = "XXX";
//        inaccessibleDraw[1] = "XXX";
//        inaccessibleDraw[2] = "XXX";

//        String[] hallwayDraw = new String[3];
//        hallwayDraw[0] = "+-+";
//        hallwayDraw[1] = "| |";
//        hallwayDraw[2] = "+-+";


//        roomDraw[0] = "***";
//        roomDraw[1] = "* *";
//        roomDraw[2] = "***";

        board = new Tile[width][height];

        // Ballroom
        for (int x = 10; x < 14; x++) {
            board[x][1] = new RoomTile("Ballroom");

        }
        for (int x = 8; x < 16; x++) {
            for (int y = 2; y < 8; y++) {
                board[x][y] = new RoomTile("Ballroom");
            }
        }
        // Kitchen
        for (int x = 0; x < 6; x++) {
            for (int y = 1; y < 7; y++) {
                board[x][y] = new RoomTile("Kitchen");

            }
        }
        // Dining Room
        for (int x = 0; x < 5; x++) {
            board[x][9] = new RoomTile("Dining Room");
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 10; y < 16; y++) {
                board[x][y] = new RoomTile("Dining Room");
            }
        }
        // Lounge
        for (int x = 0; x < 7; x++) {
            for (int y = 19; y < 25; y++) {
                board[x][y] = new RoomTile("Lounge");
            }
        }
        // Hall
        for (int x = 9; x < 15; x++) {
            for (int y = 18; y < 25; y++) {
                board[x][y] = new RoomTile("Hall");
            }
        }
        // Study
        for (int x = 17; x < 24; x++) {
            for (int y = 20; y < 25; y++) {
                board[x][y] = new RoomTile("Study");
            }
        }
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
        // Billiard Room
        for (int x = 18; x < 24; x++) {
            for (int y = 8; y < 13; y++) {
                board[x][y] = new RoomTile("Billiard Room");
            }
        }
        // Conservatory
        for (int x = 18; x < 24; x++) {
            for (int y = 1; y < 5; y++) {
                board[x][y] = new RoomTile("Conservatory");
            }
        }
        for (int x = 19; x < 23; x++) {
            board[x][5] = new RoomTile("Conservatory");
        }


        // INACCESSIBLE (It does replace rooms as it goes)
        // Top side of board
        for (int x = 0; x < width; x++) {
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
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (board[x][y] == null) {
                    board[x][y] = new HallwayTile();
                }
            }
        }

    }

    //Getters and Setters
    public List<WeaponTokens> getWeaponTokens() {
        return weaponTokens;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public RoomCard getRoomCardAtIndex(int idx) {
        return roomCards.get(idx);
    }

    public CharacterCard getChrCardAtIndex(int idx) {
        return chrCards.get(idx);
    }

    public WeaponCard getWeaponCardAtIndex(int idx) {
        return weapCards.get(idx);
    }
}
