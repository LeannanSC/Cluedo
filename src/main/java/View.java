import Entities.Cards.Card;
import Entities.Commands.Action;
import Entities.Commands.Refutation;
import Entities.Commands.Suggestion;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Abstract class for viewing the board
 */

public abstract class View{

	public abstract void redraw(Game game);

	public abstract int getInput(int arrayOptionSize);

	public View() {
	}

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

	public abstract int getPlayers();

	public abstract void setDicePanel(int dice1,int dice2);

	public String createLabel(Action action, Player player, Game game) {
		String text = (action.ordinal() + 1) + ": ";
		if (game.canDoAction(action, player)) {
			text += action.getLabel();
		}
		return text;
	}

	public String createLabel(Refutation refutation, Player player, Game game) {
		String text = (refutation.ordinal() + 1) + ": ";
		if (game.canDoRefutation(refutation, player)) {
			text += refutation.getLabel();
		}
		return text;
	}

	public String createLabel(Suggestion suggestion, Game game) {
		String text = (suggestion.ordinal() + 1) + ": ";
		if (game.canDoSuggestion(suggestion)) {
			text += suggestion.getLabel();
		}
		return text;
	}
}