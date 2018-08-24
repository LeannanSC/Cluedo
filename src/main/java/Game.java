import Entities.Commands.Action;
import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Commands.Refutation;
import Entities.Player;
import Entities.Commands.Suggestion;
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
    private List<Tile> placesMoved = new ArrayList<>();
    private Tile[][] board;

    public boolean rolledThisTurn = false; // fixme getters + setters
    public boolean movedThisTurn = false;
    public boolean suggestedThisTurn = false;
    public Player currentPlayer;

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
        currentPlayer = getCurrentPlayers().get(0);
        placesMoved.add(board[currentPlayer.getLocation().x][currentPlayer.getLocation().y]);
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
        Tile nextTile = getNextTile(player.getLocation(), direction);
        if (nextTile == null){
            throw new Error("invalid next tile in movePlayer");
        }
        player.move(direction);
        nextTile.setPlayer(player);
        placesMoved.add(nextTile);
        movedThisTurn = true;
    }

    public Tile getNextTile(Point currentTileLocation, Action direction) {

        Point nextLocation = null;

        switch (direction) {
            case NORTH:
                nextLocation = new Point(currentTileLocation.x, currentTileLocation.y - 1);
                break;

            case SOUTH:
                nextLocation = new Point(currentTileLocation.x,currentTileLocation.y + 1);
                break;

            case EAST:
                nextLocation = new Point(currentTileLocation.x + 1, currentTileLocation.y);
                break;

            case WEST:
                nextLocation = new Point(currentTileLocation.x - 1, currentTileLocation.y);
                break;

        }

        if (nextLocation == null || nextLocation.x > GameLoader.WIDTH-1 || nextLocation.x < 0 || nextLocation.y > GameLoader.HEIGHT-1 || nextLocation. y < 0){ ;
            return null;

        } else {
            return board[nextLocation.x][nextLocation.y];
        }
    }

    public void changeTurn(Player player) {
        int nextPlayer = currentPlayers.indexOf(player) + 1;

        if (nextPlayer > currentPlayers.size() - 1) {
            nextPlayer = 0;
        }

        player.setMovesRemaining(0);
        placesMoved = new ArrayList<>();
        rolledThisTurn = false;
        movedThisTurn = false;
        suggestedThisTurn = false;
        selectedCharacter = null;
        selectedWeapon = null;
        selectedRoom = null;

        currentPlayer = currentPlayers.get(nextPlayer);
        placesMoved.add(board[currentPlayer.getLocation().x][currentPlayer.getLocation().y]);
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
                return getCanMove(player, action) && !player.isOut();

            case SUGGESTION:
            case ACCUSATION:
                return board[player.getLocation().x][player.getLocation().y] instanceof RoomTile && !player.isOut();

            case TURN:
                return movedThisTurn;

            case INFO:
                return true;
        }
        return false; // fixme?
    }

    public boolean canDoSuggestion(Suggestion suggestion) {
        switch (suggestion) {
            case CHARACTER:
                return selectedCharacter == null;
            case WEAPON:
                return selectedWeapon == null;
            case ROOM:
                return selectedRoom == null;
            case CONFIRM:
                return (selectedCharacter != null && selectedWeapon != null && selectedRoom != null);
        }
        return false; // fixme?
    }

    public boolean canDoRefutation(Refutation refutation, Player player) {
        switch (refutation) {
            case REFUTE:
                return (player.getCardsInHand().contains(selectedCharacter) || player.getCardsInHand().contains(selectedWeapon) || player.getCardsInHand().contains(selectedRoom));
            case PASS:
                return !(player.getCardsInHand().contains(selectedCharacter) || player.getCardsInHand().contains(selectedWeapon) || player.getCardsInHand().contains(selectedRoom));
        }
        return false; // fixme?
    }

    public boolean checkSolution() {
        return (solutionCards.contains(selectedCharacter) && solutionCards.contains(selectedWeapon) && solutionCards.contains(selectedRoom));
    }

    /**
     * Checks if the player can move in the selected direction
     *
     * @param player    The player to be moved
     * @param direction The direction the player wants to move in
     * @return The validity of movement (boolean)
     */
    public boolean getCanMove(Player player, Action direction) {
        Point currentTileLocation = player.getLocation();
        Tile currentTile = board[player.getLocation().x][player.getLocation().y];
        Tile nextTile = getNextTile(currentTileLocation, direction);

        if (nextTile == null){
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
        if (placesMoved.contains(nextTile)) {
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


    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Tile[][] getBoard() {
        return board;
    }
}