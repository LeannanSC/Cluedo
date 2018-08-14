import Entities.Cards.Card;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    //Game Associations
    private final GameLoader gameLoader;
    private final List<Card> solutionCards = new ArrayList<>();
    private final List<Player> currentPlayers = new ArrayList<>();
    private Tile[][] board;

    public Game(int numPlayers, GameLoader gameLoader) {
        this.gameLoader = gameLoader;
        solutionCards.addAll(gameLoader.initSolution()); // always init solution before players
        currentPlayers.addAll(gameLoader.initPlayers(numPlayers));
        board = gameLoader.getBoard();
        System.out.println("Starting game!");
    }

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

    public void movePlayer(Player player, String direction) {
        board[player.getLocation().x][player.getLocation().y].setPlayer(null);
        player.move(direction);
        board[player.getLocation().x][player.getLocation().y].setPlayer(player);
    }


    public boolean getCanMove(Player p, String direction) {
        Tile currentTile = board[p.getLocation().x][p.getLocation().y];
        Tile nextTile;

        switch (direction) {
            case "North":
                // if next tile is out of bounds
                try {
                    nextTile = board[p.getLocation().x][p.getLocation().y - 1];
                } catch (ArrayIndexOutOfBoundsException ae) {
                    return false;
                }

                //check for invalid tile
                if (nextTile instanceof InaccessibleTile) {
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
                    nextTile = board[p.getLocation().x][p.getLocation().y + 1];
                } catch (ArrayIndexOutOfBoundsException ae) {
                    return false;
                }

                if (nextTile instanceof InaccessibleTile) {
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
                    nextTile = board[p.getLocation().x - 1][p.getLocation().y];
                } catch (ArrayIndexOutOfBoundsException ae) {
                    return false;
                }

                //check for invalid tile
                if (nextTile instanceof InaccessibleTile) {
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
                    nextTile = board[p.getLocation().x + 1][p.getLocation().y];
                } catch (ArrayIndexOutOfBoundsException ae) {
                    return false;
                }
                if (nextTile instanceof InaccessibleTile) {
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


    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

}