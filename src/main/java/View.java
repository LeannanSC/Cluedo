import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

/**
 * Abstract class for viewing the board
 */
public abstract class View {

	public abstract void drawBoard(Tile[][] board, int width, int height);

	public abstract void drawRoomTile(RoomTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void drawHallwayTile(HallwayTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void printHand(Player p);

	public abstract void printBoardInfo();

	public abstract void passToNextPlayerDialogue(Player nextPlayer);

	public abstract void printNewTurnMenu(Player currentPlayer);

	public abstract void printFalseAccusation();

	public abstract void printOutOfMoves(Player currentPlayer);

	public abstract void printCommands(Player player, Game game);

	public abstract void printSuggestions(Game game);

	public abstract void printSuggestions(Player player, Game game);

	public abstract void printCommandUnavailable();

	public abstract void printRefuteMenu(Player player);

	public abstract void printRefutationUnavailable();

	public abstract void printRutations(Game game);
}