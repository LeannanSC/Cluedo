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
    //our random number
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

        loadWeaponTokens();

        loadAvailableCharacters();

        loadCards();

        loadTiles();

        System.out.println("Assets loaded ...");
    }

    public void initPlayerHands(int handSize,List<Player> currentPlayers) {
        Random rng = new Random();
        Card randomCard;

        for (int i = 0; i < handSize; i++) {
            for (Player p: currentPlayers) {
                randomCard = remainingCards.remove(rng.nextInt(remainingCards.size()));
                p.addCardToHand(randomCard);
            }
        }

        if (currentPlayers.get(0).getCardsInHand().size() == 0){
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
            System.out.println("cards set: " + newPlayer.getCardsInHand().size());
            currentPlayers.add(newPlayer);
        }

        initPlayerHands(remainingCards.size()/currentPlayers.size(), currentPlayers);

        if (remainingCards.size() != 0){
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

        if (remainingCards.size() != 18){
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
        allCharacterPool.add(new Player(new Point(7,24), "Miss Scarlett", "Red"));
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
        String[] inaccessibleDraw = new String[3];
        inaccessibleDraw[0] = "XXX";
        inaccessibleDraw[1] = "XXX";
        inaccessibleDraw[2] = "XXX";

        String[] hallwayDraw = new String[3];
        hallwayDraw[0] = "+-+";
        hallwayDraw[1] = "| |";
        hallwayDraw[2] = "+-+";

        String[] roomDraw = new String[3];
        roomDraw[0] = "***";
        roomDraw[1] = "* *";
        roomDraw[2] = "***";

        board = new Tile[Game.getWidth()][Game.getHeight()];
        // TODO: Test if this draws properly
        // ROOMS
        // Ballroom
        for (int x = 10; x < 14; x++) {
            board[x][1] = new RoomTile("Ballroom", roomDraw);
        }
        for (int x = 8; x < 16; x++) {
            for (int y = 2; y < 8; y++) {
                board[x][y] = new RoomTile("Ballroom", roomDraw);
            }
        }
        // Kitchen
        for (int x = 0; x < 6; x++) {
            for (int y = 1; y < 7; y++) {
                board[x][y] = new RoomTile("Kitchen", roomDraw);

            }
        }
        // Dining Room
        for (int x = 0; x < 5; x++) {
            board[x][9] = new RoomTile("Dining Room", roomDraw);
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 10; y < 16; y++) {
                board[x][y] = new RoomTile("Dining Room", roomDraw);
            }
        }
        // Lounge
        for (int x = 0; x < 7; x++) {
            for (int y = 19; y < 25; y++) {
                board[x][y] = new RoomTile("Lounge", roomDraw);
            }
        }
        // Hall
        for (int x = 9; x < 15; x++) {
            for (int y = 18; y < 25; y++) {
                board[x][y] = new RoomTile("Hall", roomDraw);
            }
        }
        // Study
        for (int x = 17; x < 24; x++) {
            for (int y = 20; y < 25; y++) {
                board[x][y] = new RoomTile("Study", roomDraw);
            }
        }
        // Library
        for (int x = 18; x < 23; x++) {
            for (int y = 13; y < 18; y++) {
                board[x][y] = new RoomTile("Library", roomDraw);
            }
        }
        for (int x = 17; x < 24; x++) {
            for (int y = 14; y < 17; y++) {
                if (board[x][y] == null) {
                    board[x][y] = new RoomTile("Library", roomDraw);
                }
            }
        }
        // Billiard Room
        for (int x = 18; x < 24; x++) {
            for (int y = 8; y < 13; y++) {
                board[x][y] = new RoomTile("Billiard Room", roomDraw);
            }
        }
        // Conservatory
        for (int x = 18; x < 24; x++) {
            for (int y = 1; y < 5; y++) {
                board[x][y] = new RoomTile("Conservatory", roomDraw);
            }
        }
        for (int x = 19; x < 23; x++) {
            board[x][5] = new RoomTile("Conservatory", roomDraw);
        }
        // INACCESSIBLE (It does replace rooms as it goes)
        // Top side of board
        for (int x = 0; x < Game.getWidth(); x++) {
            if (x != 9 && x != 14) {
                board[x][0] = new InaccessibleTile(inaccessibleDraw);
            }
        }
        board[6][1] = new InaccessibleTile(inaccessibleDraw);
        board[17][1] = new InaccessibleTile(inaccessibleDraw);
        // Left side of board
        board[0][6] = new InaccessibleTile(inaccessibleDraw);
        board[0][8] = new InaccessibleTile(inaccessibleDraw);
        board[0][16] = new InaccessibleTile(inaccessibleDraw);
        board[0][18] = new InaccessibleTile(inaccessibleDraw);
        // Right side of board
        board[23][5] = new InaccessibleTile(inaccessibleDraw);
        board[23][7] = new InaccessibleTile(inaccessibleDraw);
        board[23][13] = new InaccessibleTile(inaccessibleDraw);
        board[23][14] = new InaccessibleTile(inaccessibleDraw);
        board[23][18] = new InaccessibleTile(inaccessibleDraw);
        board[23][20] = new InaccessibleTile(inaccessibleDraw);
        // Bottom side of board
        board[6][24] = new InaccessibleTile(inaccessibleDraw);
        board[8][24] = new InaccessibleTile(inaccessibleDraw);
        board[15][24] = new InaccessibleTile(inaccessibleDraw);
        board[17][24] = new InaccessibleTile(inaccessibleDraw);
        // Cellar (inaccessible)
        for (int x = 10; x < 15; x++) {
            for (int y = 10; y < 17; y++) {
                board[x][y] = new InaccessibleTile(inaccessibleDraw);
            }
        }
        // HALLWAY (All remaining tiles)
        for (int x = 0; x < Game.getWidth(); x++) {
            for (int y = 0; y < Game.getHeight(); y++) {
                if (board[x][y] == null) {
                    board[x][y] = new HallwayTile(hallwayDraw);
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
