import Entities.Cards.Card;
import Entities.Player;
import Entities.Tiles.Tile;

import java.util.List;

public class Game {

    //Game Attributes
    private static final int width = 24;
    private static final int height = 25;

    //Game Associations
    private final GameLoader gameLoader;

    private final List<Card> solutionCards;
    private final List<Player> currentPlayers;
    // todo fill
    private Tile[][] board;

    public Game(int numPlayers) {
        this.gameLoader = new GameLoader();
        solutionCards = gameLoader.initSolution();
        currentPlayers = gameLoader.initPlayers(numPlayers);
    }

    /**
     * Draws the whole board
     */
    public void draw() {
        StringBuilder printMe = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x1 = 0; x1 < width; x1++) {
                printMe.append(board[x1][y].getDrawMethod()[0]);
            }
            for (int x2 = 0; x2 < width; x2++) {
                printMe.append(board[x2][y].getDrawMethod()[1]);
            }
            for (int x3 = 0; x3 < width; x3++) {
                printMe.append(board[x3][y].getDrawMethod()[2]);
            }
            printMe.append("\n");
        }
        System.out.print(printMe);
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
