import Entities.Action;
import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Suggestion;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;
import Views.View;

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

    public void draw(View v) {
        v.drawBoard(board, GameLoader.WIDTH, GameLoader.HEIGHT);
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
                return board[player.getLocation().x][player.getLocation().y] instanceof RoomTile;

            case TURN:
                return !movedThisTurn;

            case INFO:
                return true;
        }
        return false;
    }

    public boolean canDoSuggestion(Suggestion suggestion){
        switch (suggestion){
            case CHARACTER:
                return selectedCharacter == null;
            case WEAPON:
                return selectedWeapon == null;
            case ROOM:
                return selectedWeapon == null;
            case CONFIRM:
                return (selectedCharacter != null && selectedWeapon != null && selectedRoom != null);
        }
        return false;
    }

    public List<String> getAvailRefutations(Player player) { // fixme make follow suit
        List<String> currentOptions = new ArrayList<>();
        if (getCanRefute(player)) {
            currentOptions.add("1: Refute");
        } else {
            System.out.println("unable to refute");
            changeTurn(player);
        }
        return currentOptions;
    }

    public boolean checkSolution(){
        return (solutionCards.contains(selectedCharacter) && solutionCards.contains(selectedWeapon) && solutionCards.contains(selectedRoom));
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
//todo implement
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public boolean getCanRefute(Player player) {
        return (player.getCardsInHand().contains(selectedCharacter) || solutionCards.contains(selectedWeapon) || solutionCards.contains(selectedRoom));
    }


    public Tile[][] getBoard() {
        return board;
    }
}