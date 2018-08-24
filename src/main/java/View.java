import Entities.Cards.Card;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.util.List;

/**
 * Abstract class for viewing the board
 */
public abstract class View {


	public abstract void redraw(Game game);

	public abstract int getInput(int arrayOptionSize);

	public abstract void drawBoard(Tile[][] board, int width, int height);

	public abstract void drawRoomTile(RoomTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void drawHallwayTile(HallwayTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3);

	public abstract void printCommandMenu(Player player, Game game);

	public abstract void printSuggestionMenu(Game game);

	public abstract void printRefutationMenu(Player player, Game game);

	public abstract void printRefuteCardSelectionMenu(Player player);

	public abstract void printAllCharacterCards(Game game);

	public abstract void printAllWeaponCards(Game game);

	public abstract void printAllRoomCards(Game game);

	public abstract void printBoardInfo();

	public abstract void printError(String error);

	public abstract void printHand(Player p);

	public abstract void printRefutations(Game game);

	public abstract void printWinText(Player player);

	public abstract void printLoseText(List<Card> solution);

	public abstract void printNewTurnText(Player currentPlayer);

	public abstract void printEndTurnText();

	public abstract void printPassInstruction(Player nextPlayer);
}