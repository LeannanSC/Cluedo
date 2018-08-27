import Entities.Cards.Card;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.util.List;

/**
 * Class for displaying objects using graphics
 */
public class GraphicalView extends View {
	@Override
	public void redraw(Game game) {

	}

	@Override
	public int getInput(int arrayOptionSize) {
		return 0;
	}

	@Override
	public void drawBoard(Tile[][] board, int width, int height) {

	}

	@Override
	public void drawRoomTile(RoomTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {

	}

	@Override
	public void drawHallwayTile(HallwayTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {

	}

	@Override
	public void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {

	}

	@Override
	public void printCommandMenu(Player player, Game game) {

	}

	@Override
	public void printSuggestionMenu(Game game) {

	}

	@Override
	public void printRefutationMenu(Player player, Game game) {

	}

	@Override
	public void printRefuteCardSelectionMenu(Player player) {

	}

	@Override
	public void printAllCharacterCards(Game game) {

	}

	@Override
	public void printAllWeaponCards(Game game) {

	}

	@Override
	public void printAllRoomCards(Game game) {

	}

	@Override
	public void printBoardInfo() {

	}

	@Override
	public void printError(String error) {

	}

	@Override
	public void printHand(Player p) {

	}

	@Override
	public void printRefutations(Game game) {

	}

	@Override
	public void printWinText(Player player) {

	}

	@Override
	public void printLoseText(List<Card> solution) {

	}

	@Override
	public void printNewTurnText(Player currentPlayer) {

	}

	@Override
	public void printEndTurnText() {

	}

	@Override
	public void printPassInstruction(Player nextPlayer) {

	}
}