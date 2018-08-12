import java.util.Arrays;
import java.util.List;

// todo possible name change?
public class Board {

    //Board Attributes
    private static final int width = 24;
    private static final int height = 25;

    //Board Associations
    private GameLoader gameLoader;
    private GameState gameState;

    private List<Card> solutionCards;
    private List<Player> currentPlayers;
    // todo fill
    private Tile[][] boardTiles;

    public Board(List<String> requestedPlayers) {
        gameLoader = new GameLoader();
        gameState = new GameState();
        solutionCards = gameLoader.initSolution();
        currentPlayers = gameLoader.initPlayers(requestedPlayers);
    }

    /**
     * Draws the whole board
     */
    public void draw() {
        StringBuilder printMe = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x1 = 0; x1 < width; x1++) {
                printMe.append(boardTiles[x1][y].getDrawMethod()[0]);
            }
            for (int x2 = 0; x2 < width; x2++) {
                printMe.append(boardTiles[x2][y].getDrawMethod()[1]);
            }
            for (int x3 = 0; x3 < width; x3++) {
                printMe.append(boardTiles[x3][y].getDrawMethod()[2]);
            }
            printMe.append("\n");
        }
        System.out.print(printMe);
    }

    //fixme check if works
    public Boolean checkSolution(List<Card> guessCards) {
        return solutionCards.equals(guessCards);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Tile[][] getBoardTiles() {
        return boardTiles;
    }

    @Override
    public String toString() {
        return "Board{" +
                "gameLoader=" + gameLoader +
                ", gameState=" + gameState +
                ", solutionCards=" + solutionCards +
                ", currentPlayers=" + currentPlayers +
                ", boardTiles=" + Arrays.toString(boardTiles) +
                '}';
    }
}
