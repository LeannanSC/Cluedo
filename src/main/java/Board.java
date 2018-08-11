/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/

import java.awt.*;
import java.util.*;
import java.util.List;

// line 13 "model.ump"
// line 139 "model.ump"
public class Board {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Board Attributes
    private static final int width = 24;
    private static final int height = 25;

    //Board Associations
    private GameState gameState;
    private List<WeaponTokens> weaponTokens;
    private Tile[][] tiles = new Tile[width][height];
    private List<Player> currentPlayers;
    private Map<String, Player> poolOfAvailablePlayers;
    private List<Card> solutionCards;
    private List<Card> allCards;


    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Board(List<String> requestedPlayers) {
        initItems();
        initPlayers(requestedPlayers);
    }

    //------------------------
    // INTERFACE
    //------------------------

    public void initItems() {
        //load all weapons
        weaponTokens = new ArrayList<WeaponTokens>();
        boolean didAddWeaponTokens = setWeaponTokens(
                new WeaponTokens("Candlestick", "grey"),
                new WeaponTokens("Dagger", "grey"),
                new WeaponTokens("Lead Pipe", "grey"),
                new WeaponTokens("Revolver", "grey"),
                new WeaponTokens("Rope", "grey"),
                new WeaponTokens("Spanner", "grey")
        );
        if (!didAddWeaponTokens) {
            throw new RuntimeException("Unable to create Board, must have 6 weaponTokens");
        }

        //load all players
        poolOfAvailablePlayers = new HashMap<>();
        poolOfAvailablePlayers.put("Miss Scarlett", new Player(new Point(-1, -1), "Miss Scarlett", "Red"));
        poolOfAvailablePlayers.put("Colonel Mustard", new Player(new Point(-1, -1), "Colonel Mustard", "Yellow"));
        poolOfAvailablePlayers.put("Mrs. White", new Player(new Point(-1, -1), "Mrs. White", "White"));
        poolOfAvailablePlayers.put("Mr. Green", new Player(new Point(-1, -1), "Mr. Green", "Green"));
        poolOfAvailablePlayers.put("Mrs. Peacock", new Player(new Point(-1, -1), "Mrs. Peacock", "Blue"));
        poolOfAvailablePlayers.put("Professor Plum", new Player(new Point(-1, -1), "Professor Plum", "Purple"));

        List<RoomCard> roomCards = new ArrayList<>();
        roomCards.add(new RoomCard("Ballroom"));
        roomCards.add(new RoomCard("Kitchen"));
        roomCards.add(new RoomCard("Dining Room"));
        roomCards.add(new RoomCard("Lounge"));
        roomCards.add(new RoomCard("Hall"));
        roomCards.add(new RoomCard("Study"));
        roomCards.add(new RoomCard("Library"));
        roomCards.add(new RoomCard("Billiard Room"));
        roomCards.add(new RoomCard("Conservatory"));

        List<CharacterCard> chrCards = new ArrayList<>();
        chrCards.add(new CharacterCard("Miss Scarlett"));
        chrCards.add(new CharacterCard("Colonel Mustard"));
        chrCards.add(new CharacterCard("Mrs. White"));
        chrCards.add(new CharacterCard("Mrs. White"));
        chrCards.add(new CharacterCard("Mr. Green"));
        chrCards.add(new CharacterCard("Professor Plum"));

        List<WeaponCard> wepCards = new ArrayList<>();
        wepCards.add(new WeaponCard("Candlestick"));
        wepCards.add(new WeaponCard("Dagger"));
        wepCards.add(new WeaponCard("Lead Pipe"));
        wepCards.add(new WeaponCard("Revolver"));
        wepCards.add(new WeaponCard("Rope"));
        wepCards.add(new WeaponCard("Spanner"));

        Random rng = new Random();
        solutionCards = new ArrayList<>();
        solutionCards.add(roomCards.remove(rng.nextInt(roomCards.size())));
        solutionCards.add(chrCards.remove(rng.nextInt(chrCards.size())));
        solutionCards.add(wepCards.remove(rng.nextInt(wepCards.size())));

        List<Card> cardsAfterSolution = new ArrayList<>();
        allCards = new ArrayList<>();
        cardsAfterSolution.addAll(roomCards);
        cardsAfterSolution.addAll(chrCards);
        cardsAfterSolution.addAll(wepCards);

        boolean didAddAllCards = setAllCards(cardsAfterSolution.toArray(new Card[]{}));
        if (didAddAllCards) {
            throw new RuntimeException("Unable to create Board, must have 21 Card in total");
        }
    }

    public void initPlayers(List<String> requestedPlayers) {
        currentPlayers = new ArrayList<>();
        for (String s: requestedPlayers){
            if(poolOfAvailablePlayers.containsKey(s)){
                currentPlayers.add(poolOfAvailablePlayers.get(s));
            }
        }

        if (currentPlayers.size() < 3 || currentPlayers.size() > 6 || currentPlayers.size() != requestedPlayers.size()) {
            throw new RuntimeException("Unable to create Board, must have 3 to 6 poolOfAvailablePlayers");
        }
    }

    /* Code from template association_GetOne */
    public GameState getGameState() {
        return gameState;
    }

    /* Code from template association_GetMany */
    public WeaponTokens getWeaponToken(int index) {
        return weaponTokens.get(index);
    }

    public List<WeaponTokens> getWeaponTokens() {
        return Collections.unmodifiableList(weaponTokens);
    }

    public int numberOfWeaponTokens() {
        return weaponTokens.size();
    }

    public boolean hasWeaponTokens() {
        return weaponTokens.size() > 0;
    }

    public int indexOfWeaponToken(WeaponTokens aWeaponToken) {
        return weaponTokens.indexOf(aWeaponToken);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    /* Code from template association_GetMany */
    public Player getPlayer(int index) {
        return poolOfAvailablePlayers.get(index);
    }

    public Map<String, Player> getPoolOfAvailablePlayers() {
        return Collections.unmodifiableMap(poolOfAvailablePlayers);
    }

    public int numberOfPlayers() {
        return poolOfAvailablePlayers.size();
    }

    public boolean hasPlayers() {
        return poolOfAvailablePlayers.size() > 0;
    }

    /* Code from template association_GetMany */
    public Card getAllCard(int index) {
        return allCards.get(index);
    }

    public List<Card> getAllCards() {
        return Collections.unmodifiableList(allCards);
    }

    public int numberOfAllCards() {
        return allCards.size();
    }

    public boolean hasAllCards() {
        return allCards.size() > 0;
    }

    public int indexOfAllCard(Card aAllCard) {
        return allCards.indexOf(aAllCard);
    }

    /* Code from template association_SetUnidirectionalOne */
    public boolean setGameState(GameState aNewGameState) {
        boolean wasSet = false;
        if (aNewGameState != null) {
            gameState = aNewGameState;
            wasSet = true;
        }
        return wasSet;
    }

    /* Code from template association_RequiredNumberOfMethod */
    public static int requiredNumberOfWeaponTokens() {
        return 6;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfWeaponTokens() {
        return 6;
    }

    /* Code from template association_MaximumNumberOfMethod */
    public static int maximumNumberOfWeaponTokens() {
        return 6;
    }

    /* Code from template association_SetUnidirectionalN */
    public boolean setWeaponTokens(WeaponTokens... newWeaponTokens) {
        boolean wasSet = false;
        ArrayList<WeaponTokens> verifiedWeaponTokens = new ArrayList<WeaponTokens>();
        for (WeaponTokens aWeaponToken : newWeaponTokens) {
            if (verifiedWeaponTokens.contains(aWeaponToken)) {
                continue;
            }
            verifiedWeaponTokens.add(aWeaponToken);
        }

        if (verifiedWeaponTokens.size() != newWeaponTokens.length || verifiedWeaponTokens.size() != requiredNumberOfWeaponTokens()) {
            return wasSet;
        }

        weaponTokens.clear();
        weaponTokens.addAll(verifiedWeaponTokens);
        wasSet = true;
        return wasSet;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfTiles() {
        return 0;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfPlayers() {
        return 3;
    }

    /* Code from template association_MaximumNumberOfMethod */
    public static int maximumNumberOfPlayers() {
        return 6;
    }

    /* Code from template association_AddUnidirectionalMN */
    public boolean addPlayer(String characterName, Player aPlayer) {
        boolean wasAdded = false;
        if (numberOfPlayers() < maximumNumberOfPlayers()) {
            poolOfAvailablePlayers.put(characterName, aPlayer);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean removePlayer(String characterName, Player aPlayer) {
        boolean wasRemoved = false;
        if (!poolOfAvailablePlayers.remove(characterName, aPlayer)) {
            return wasRemoved;
        }

        if (numberOfPlayers() <= minimumNumberOfPlayers()) {
            return wasRemoved;
        }

        poolOfAvailablePlayers.remove(aPlayer);
        wasRemoved = true;
        return wasRemoved;
    }

    /* Code from template association_RequiredNumberOfMethod */
    public static int requiredNumberOfAllCards() {
        return 18;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAllCards() {
        return 21;
    }

    /* Code from template association_MaximumNumberOfMethod */
    public static int maximumNumberOfAllCards() {
        return 21;
    }

    /* Code from template association_SetUnidirectionalN */
    public boolean setAllCards(Card... newAllCards) {
        boolean wasSet = false;
        ArrayList<Card> verifiedAllCards = new ArrayList<Card>();
        for (Card aAllCard : newAllCards) {
            if (verifiedAllCards.contains(aAllCard)) {
                continue;
            }
            verifiedAllCards.add(aAllCard);
        }

        if (verifiedAllCards.size() != newAllCards.length || verifiedAllCards.size() != requiredNumberOfAllCards()) {
            return wasSet;
        }

        allCards.clear();
        allCards.addAll(verifiedAllCards);
        wasSet = true;
        return wasSet;
    }

    public void delete() {
        gameState = null;
        weaponTokens.clear();
        poolOfAvailablePlayers.clear();
        tiles = new Tile[width][height];
        allCards.clear();
    }

    /**
     * Draws the whole board
     */
    public void draw() {
        StringBuilder printMe = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x1 = 0; x1 < width; x1++) {
                printMe.append(tiles[x1][y].getDrawMethod()[0]);
            }
            for (int x2 = 0; x2 < width; x2++) {
                printMe.append(tiles[x2][y].getDrawMethod()[1]);
            }
            for (int x3 = 0; x3 < width; x3++) {
                printMe.append(tiles[x3][y].getDrawMethod()[2]);
            }
            printMe.append("\n");
        }
        System.out.print(printMe);
    }

    public String toString() {
        return super.toString() + "[" +
                "width" + ":" + width + "," +
                "height" + ":" + height + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "gameState = " + (getGameState() != null ? Integer.toHexString(System.identityHashCode(getGameState())) : "null");
    }
}
