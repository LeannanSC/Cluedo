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
    private List<Tile> tiles;
    private List<Player> currentPlayers;
    private List<Player> allPlayers;
    private List<Card> allCards;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Board() {
        initItems();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public void initItems(){
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
        allPlayers = new ArrayList<>();
        boolean didAddPlayers = setPlayers(
                new Player( new Point(-1,-1),"Miss Scarlett","Red"),
                new Player( new Point(-1,-1),"Colonel Mustard","Yellow"),
                new Player( new Point(-1,-1),"Mrs. White","White"),
                new Player( new Point(-1,-1),"Mr. Green","Green"),
                new Player( new Point(-1,-1),"Mrs. Peacock","Blue"),
                new Player( new Point(-1,-1),"Professor Plum","Purple")
        );
        if (!didAddPlayers) {
            throw new RuntimeException("Unable to create Board, must have 3 to 6 allPlayers");
        }

        allCards = new ArrayList<>();
        boolean didAddAllCards = setAllCards(
                new RoomCard("Ballroom"),
                new RoomCard("Kitchen"),
                new RoomCard("Dining Room"),
                new RoomCard("Lounge"),
                new RoomCard("Hall"),
                new RoomCard("Study"),
                new RoomCard("Library"),
                new RoomCard("Billiard Room"),
                new RoomCard("Conservatory"),
                new CharacterCard( "Miss Scarlett"),
                new CharacterCard("Colonel Mustard"),
                new CharacterCard("Mrs. White"),
                new CharacterCard("Mr. Green"),
                new CharacterCard("Mrs. Peacock"),
                new CharacterCard("Professor Plum"),
                new WeaponCard("Candlestick"),
                new WeaponCard("Dagger"),
                new WeaponCard("Lead Pipe"),
                new WeaponCard("Revolver"),
                new WeaponCard("Rope"),
                new WeaponCard("Spanner" )
        );
        if (!didAddAllCards) {
            throw new RuntimeException("Unable to create Board, must have 21 Card in total");
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

    /* Code from template association_GetMany */
    public Tile getTile(int index) {
        return tiles.get(index);
    }

    public List<Tile> getTiles() {
        return Collections.unmodifiableList(tiles);
    }

    public int numberOfTiles() {
        return tiles.size();
    }

    public boolean hasTiles() {
        return tiles.size() > 0;
    }

    public int indexOfTile(Tile aTile) {
        return tiles.indexOf(aTile);
    }

    /* Code from template association_GetMany */
    public Player getPlayer(int index) {
        return allPlayers.get(index);
    }

    public List<Player> getAllPlayers() {
        return Collections.unmodifiableList(allPlayers);
    }

    public int numberOfPlayers() {
        return allPlayers.size();
    }

    public boolean hasPlayers() {
        return allPlayers.size() > 0;
    }

    public int indexOfPlayer(Player aPlayer) {
        return allPlayers.indexOf(aPlayer);
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

    /* Code from template association_AddUnidirectionalMany */
    public boolean addTile(Tile aTile) {
        boolean wasAdded = false;
        if (tiles.contains(aTile)) {
            return false;
        }
        tiles.add(aTile);
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeTile(Tile aTile) {
        boolean wasRemoved = false;
        if (tiles.contains(aTile)) {
            tiles.remove(aTile);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /* Code from template association_AddIndexControlFunctions */
    public boolean addTileAt(Tile aTile, int index) {
        boolean wasAdded = false;
        if (addTile(aTile)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfTiles()) {
                index = numberOfTiles() - 1;
            }
            tiles.remove(aTile);
            tiles.add(index, aTile);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveTileAt(Tile aTile, int index) {
        boolean wasAdded = false;
        if (tiles.contains(aTile)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfTiles()) {
                index = numberOfTiles() - 1;
            }
            tiles.remove(aTile);
            tiles.add(index, aTile);
            wasAdded = true;
        } else {
            wasAdded = addTileAt(aTile, index);
        }
        return wasAdded;
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
    public boolean addPlayer(Player aPlayer) {
        boolean wasAdded = false;
        if (allPlayers.contains(aPlayer)) {
            return false;
        }
        if (numberOfPlayers() < maximumNumberOfPlayers()) {
            allPlayers.add(aPlayer);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean removePlayer(Player aPlayer) {
        boolean wasRemoved = false;
        if (!allPlayers.contains(aPlayer)) {
            return wasRemoved;
        }

        if (numberOfPlayers() <= minimumNumberOfPlayers()) {
            return wasRemoved;
        }

        allPlayers.remove(aPlayer);
        wasRemoved = true;
        return wasRemoved;
    }

    /* Code from template association_SetUnidirectionalMN */
    public boolean setPlayers(Player... newPlayers) {
        boolean wasSet = false;
        ArrayList<Player> verifiedPlayers = new ArrayList<Player>();
        for (Player aPlayer : newPlayers) {
            if (verifiedPlayers.contains(aPlayer)) {
                continue;
            }
            verifiedPlayers.add(aPlayer);
        }

        if (verifiedPlayers.size() != newPlayers.length || verifiedPlayers.size() < minimumNumberOfPlayers() || verifiedPlayers.size() > maximumNumberOfPlayers()) {
            return wasSet;
        }

        allPlayers.clear();
        allPlayers.addAll(verifiedPlayers);
        wasSet = true;
        return wasSet;
    }

    /* Code from template association_AddIndexControlFunctions */
    public boolean addPlayerAt(Player aPlayer, int index) {
        boolean wasAdded = false;
        if (addPlayer(aPlayer)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfPlayers()) {
                index = numberOfPlayers() - 1;
            }
            allPlayers.remove(aPlayer);
            allPlayers.add(index, aPlayer);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMovePlayerAt(Player aPlayer, int index) {
        boolean wasAdded = false;
        if (allPlayers.contains(aPlayer)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfPlayers()) {
                index = numberOfPlayers() - 1;
            }
            allPlayers.remove(aPlayer);
            allPlayers.add(index, aPlayer);
            wasAdded = true;
        } else {
            wasAdded = addPlayerAt(aPlayer, index);
        }
        return wasAdded;
    }

    /* Code from template association_RequiredNumberOfMethod */
    public static int requiredNumberOfAllCards() {
        return 21;
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
        tiles.clear();
        allPlayers.clear();
        allCards.clear();
    }

    // line 20 "model.ump"
    public void draw() {

    }

    public String toString() {
        return super.toString() + "[" +
                "width" + ":" + width + "," +
                "height" + ":" + height + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "gameState = " + (getGameState() != null ? Integer.toHexString(System.identityHashCode(getGameState())) : "null");
    }
}