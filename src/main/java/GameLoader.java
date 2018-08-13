import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Tiles.Tile;
import Entities.WeaponTokens;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameLoader {
    //our random number
    final Random rng;

    private List<RoomCard> roomCards;
    private List<CharacterCard> chrCards;
    private List<WeaponCard> weapCards;

    // todo ask sophie about
    private List<WeaponTokens> weaponTokens;
    private Map<String, Player> allCharacterMap;
    private List<Card> allCardPool;

    private List<Player> availableCharacterPool; // fixme may not need?
    private List<Card> remainingCards;

    public GameLoader() {
        //seed our random
        rng = new Random();

        loadWeaponTokens();

        loadAvailableCharacters();

        loadCards();

        // todo need to implement
        loadTiles();


    }

    private List<Card> initPlayerHand(List<Player> currentPlayers) {
        int sizeSnapshot = remainingCards.size();
        List<Card> hand = new ArrayList<>();
            for (int i = 0; i < sizeSnapshot / currentPlayers.size(); i++) {
                remainingCards.remove(rng.nextInt());
            }
            return hand;
    }


    public List<Player> initPlayers(List<String> requestedPlayers) {

        List<Player> currentPlayers = new ArrayList<>();
        for (String s : requestedPlayers) {
            if (allCharacterMap.containsKey(s)) {
                currentPlayers.add(allCharacterMap.remove(s));
            }
        }

        // keep list of remaining characters
        availableCharacterPool = new ArrayList<>(allCharacterMap.values());

        for (Player p : currentPlayers) {
            availableCharacterPool.remove(p);
            p.setCardsInHand(initPlayerHand(currentPlayers));
        }

        return currentPlayers;
    }

    public List<Card> initSolution() {
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

        return solutionCards;
    }


    private void loadCards() {
        // load allCardPool into arrays grouped by type

        roomCards = new ArrayList<>();
        roomCards.add(new RoomCard("Ballroom"));
        roomCards.add(new RoomCard("Kitchen"));
        roomCards.add(new RoomCard("Dining Entities.Tiles.RoomTile"));
        roomCards.add(new RoomCard("Lounge"));
        roomCards.add(new RoomCard("Hall"));
        roomCards.add(new RoomCard("Study"));
        roomCards.add(new RoomCard("Library"));
        roomCards.add(new RoomCard("Billiard Entities.Tiles.RoomTile"));
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
        allCharacterMap = new HashMap<>();
        allCharacterMap.put("Miss Scarlett", new Player(new Point(-1, -1), "Miss Scarlett", "Red"));
        allCharacterMap.put("Colonel Mustard", new Player(new Point(-1, -1), "Colonel Mustard", "Yellow"));
        allCharacterMap.put("Mrs. White", new Player(new Point(-1, -1), "Mrs. White", "White"));
        allCharacterMap.put("Mr. Green", new Player(new Point(-1, -1), "Mr. Green", "Green"));
        allCharacterMap.put("Mrs. Peacock", new Player(new Point(-1, -1), "Mrs. Peacock", "Blue"));
        allCharacterMap.put("Professor Plum", new Player(new Point(-1, -1), "Professor Plum", "Purple"));
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

    private void loadTiles(){
        Tile[][] tempBoard;
        // kitchen room init
        Tile[][] kitchen = new Tile[6][6];
        kitchen[0][1] // todo load tiles hre sophie

    }

    //Getters and Setters
    public List<WeaponTokens> getWeaponTokens() {
        return weaponTokens;
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
