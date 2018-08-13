import Entities.Cards.Card;
import Entities.Player;
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


    /**
     * Draws the whole board
     */
    public void draw() {
        StringBuilder printMe = new StringBuilder();

        StringBuilder output = new StringBuilder();
        for (int y = 0; y < GameLoader.height; y++) {
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();
            StringBuilder line3 = new StringBuilder();
            for (int x = 0; x < GameLoader.width; x++) {
                line1.append(board[x][y].getDrawMethod()[0]);
                line2.append(board[x][y].getDrawMethod()[1]);
                line3.append(board[x][y].getDrawMethod()[2]);
            }
            output.append(line1).append('\n')
                    .append(line2).append('\n')
                    .append(line3).append('\n');
        }

        System.out.println(output);
    }


    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

}