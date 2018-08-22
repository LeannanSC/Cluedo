import Entities.Action;
import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Tiles.*;
import Views.TextView;
import Views.View;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Initialises loaded assets and making a coherent game
 * Also allows manipulation of the board
 */
public class Game {

    //Game Associations
    final GameLoader gameLoader;
    private final List<Card> solutionCards = new ArrayList<>();
    private final List<Player> currentPlayers = new ArrayList<>();
    private List<Point> placesMoved = new ArrayList<>();
    private Tile[][] board;


    public boolean rolledThisTurn = false;
    public boolean movedThisTurn = false;
    public boolean suggestedThisTurn = false;

    CharacterCard selectedCharacter = null;
    RoomCard selectedRoom = null;
    WeaponCard selectedWeapon = null;
    Card thisRefutedCard = null;
    List<Card> refutedCards = new ArrayList<>();

    /**
     * Constructor
     *
     * @param numPlayers The number of human players
     */
    public Game(int numPlayers) {
        this.gameLoader = new GameLoader();
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
        rolledThisTurn = true;
        return moves;
    }

    /**
     * Draws the whole board
     */
    public void draw(View v) {
        v.drawBoard(board, GameLoader.WIDTH, GameLoader.HEIGHT); // fixme
    }

    /**
     * Moves the player to the neighbouring tile in the desired direction.
     *
     * @param player    The player to be moved
     * @param direction The cardinal direction the player wants to move in
     */
    public void movePlayer(Player player, Action direction) {
        board[player.getLocation().x][player.getLocation().y].setPlayer(null);
        player.move(direction);
        Tile nextTile = board[player.getLocation().x][player.getLocation().y];
        nextTile.setPlayer(player);
        placesMoved.add(new Point(player.getLocation().x, player.getLocation().y));
        movedThisTurn = true;
    }

    public Player changeTurn(Player currentPlayer) {
        int nextPlayer = currentPlayers.indexOf(currentPlayer) + 1;

        if (nextPlayer > currentPlayers.size() - 1) {
            nextPlayer = 0;
        }
        currentPlayer.setMovesRemaining(0);
        placesMoved = new ArrayList<>();
        rolledThisTurn = false;
        movedThisTurn = false;
        suggestedThisTurn = false;
        selectedCharacter = null;
        selectedWeapon = null;
        selectedRoom = null;

        return currentPlayers.get(nextPlayer);
    }

    /**
     * Checks if the player can do an action
     *
     * @param player The player to be moved
     * @param action The action the player wants to do
     * @return The validity of action (boolean)
     */

    public boolean canDoAction(Action action, Player player) {
        switch (action) {
            case NORTH:
            case SOUTH:
            case EAST:
            case WEST:
                return getCanMove(player, action);
            case SUGGESTION:
            case ACCUSATION:
                return gameLoader.getBoard()[player.getLocation().x][player.getLocation().y] instanceof RoomTile;
            case TURN:
                return !movedThisTurn;
            case INFO:
                return true;
        }
        return false;
    }

    public List<String> getAvailSuggestions() {
        List<String> currentOptions = new ArrayList<>();
        if (selectedCharacter == null) {
            currentOptions.add("1: Select Character");
        } else {
            currentOptions.add("1: Change Character");
        }

        if (selectedWeapon == null) {
            currentOptions.add("2: Select Weapon");
        } else {
            currentOptions.add("2: Change Weapon");
        }

        if (selectedRoom == null) {
            currentOptions.add("3: Select Room");
        } else {
            currentOptions.add("3: Change Room");
        }

        if (selectedCharacter != null && selectedWeapon != null && selectedRoom != null) {
            currentOptions.add("4: Confirm Selection");
        } else {
            currentOptions.add("4:");
        }

        if (currentOptions.isEmpty()) {
            throw new Error("unexpected current option size in suggest");
        }

        return currentOptions;

    }

    public List<String> getAvailRefutations(Player player) {
        List<String> currentOptions = new ArrayList<>();
        if (getCanRefute(player, selectedCharacter, selectedWeapon, selectedRoom)) {
            currentOptions.add("1: Refute");
        } else {
            currentOptions.add("1: Pass");
        }
        return currentOptions;
    }


    /**
     * Checks if the player can move in the selected direction
     *
     * @param p The player to be moved
     * @param direction The direction the player wants to move in
     * @return The validity of movement (boolean)
     */
    public boolean getCanMove(Player p, Action direction) {
        Tile currentTile = board[p.getLocation().x][p.getLocation().y];
        Tile nextTile;
        Point nextTileLocation;

        try {
            switch (direction) {

                //TODO: Make a function that takes in a point and a action, and gets the next tile
                case NORTH:
                    // if next tile is out of bounds
                    nextTileLocation = new Point(p.getLocation().x, p.getLocation().y - 1);
                    nextTile = board[nextTileLocation.x][nextTileLocation.y];
                    break;

                case SOUTH:
                    nextTileLocation = new Point(p.getLocation().x, p.getLocation().y + 1);
                    nextTile = board[nextTileLocation.x][nextTileLocation.y];
                    break;

                case EAST:
                    nextTileLocation = new Point(p.getLocation().x + 1, p.getLocation().y);
                    nextTile = board[nextTileLocation.x][nextTileLocation.y];
                    break;

                case WEST:
                    nextTileLocation = new Point(p.getLocation().x - 1, p.getLocation().y);
                    nextTile = board[nextTileLocation.x][nextTileLocation.y];
                    break;
                default:
                    throw new Error("unexpected direction in move validity check");
            }

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
    }

    public void getNextTile(){

    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public boolean getCanRefute(Player player, CharacterCard characterCard, WeaponCard weaponCard, RoomCard roomCard) {
        if (player.getCardsInHand().contains(characterCard) || player.getCardsInHand().contains(weaponCard) || player.getCardsInHand().contains(roomCard)) {
            return true;
        }
        return false;
    }

    public Tile[][] getBoard() {
        return board;
    }
}