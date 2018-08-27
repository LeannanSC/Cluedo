import Entities.Cards.Card;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;


/**
 * Class for displaying objects using text
 */
public class TextView{

	public TextView() {
		System.out.println("Loading Assets...");
		System.out.println("Please Enter number of players(3-6): ");

	}

	public void drawBoard(Tile[][] board, int width, int height ) {
		StringBuilder output = new StringBuilder();
		for (int y = 0; y < height; y++) {
			StringBuilder line1 = new StringBuilder();
			StringBuilder line2 = new StringBuilder();
			StringBuilder line3 = new StringBuilder();
			for (int x = 0; x < width; x++) {

				if (board[x][y] instanceof RoomTile) {
					drawRoomTile((RoomTile) board[x][y],line1,line2,line3);
				}
				if (board[x][y] instanceof InaccessibleTile) {
					drawInaccessibleTile((InaccessibleTile) board[x][y],line1,line2,line3);
				}
				if (board[x][y] instanceof HallwayTile) {
					drawHallwayTile((HallwayTile) board[x][y],line1,line2,line3);
				}
			}
			output.append(line1).append('\n')
					.append(line2).append('\n')
					.append(line3).append('\n');
		}

		System.out.println(output);
	}

	public void drawRoomTile(RoomTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {
		// Draw player if on tile
		if (t.getPlayer() != null) {
			line1.append("+++");
			line2.append("+" + t.getPlayer().getColour().charAt(0) + "+");
			line3.append("+++");
		}
		// Draw weapon if on tile
		else if (t.getWeaponToken() != null) {
			line1.append("+ +");
			line2.append(" " + t.getWeaponToken().getIcon() + " ");
			line3.append("+ +");
		}
		// Draw differently if is doorway
		else if (t.isDoorway()) {
			line1.append(" 0 ");
			line2.append("000");
			line3.append(" 0 ");

		} else {
			line1.append("+++");
			line2.append("+++");
			line3.append("+++");
		}
	}

	public void drawHallwayTile(HallwayTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {
		if (t.getPlayer() != null) {

			line1.append("   ");
			line2.append(" " + t.getPlayer().getColour().charAt(0) + " ");
			line3.append("   ");


		} else {
			line1.append("   ");
			line2.append("   ");
			line3.append("   ");
		}
	}

	public void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {
		line1.append("xxx");
		line2.append("xxx");
		line3.append("xxx");
	}

	public void printHand(Player p){

		System.out.println("Your hand -");

		StringBuilder output = new StringBuilder();
		for (int i = 0; i < p.getCardsInHand().size(); i++) {
			Card c = p.getCardsInHand().get(i);
			output.append(i + 1).append(": ").append(c.getCardName()).append(" ");
		}

		System.out.println(output.toString());
	}

	public void printBoardInfo() {
		StringBuilder output = new StringBuilder();

		output.append("Board Information:\n");
		output.append("\tHallways have no marks\n");
		output.append("\t Rooms are marked with +");
		output.append("\tYou can enter rooms through doors, which are marked with 0\n");
		output.append("\tYou cannot enter inaccessible tiles, which are marked with X\n");
		output.append("\tPlayers are represented as such:\n");
		output.append("\tMiss Scarlett: R, Colonel Mustard: Y, Mrs. White: W, Mr. Green: G, Mrs. Peacock: B, Professor Plum: P\n");
		output.append("\tWeapons are represented as such:\n");
		output.append("\tCandlestick: *, Dagger: %, Lead Pipe: 1, Revolver: q, Rope: &, Spanner: !\n");

		System.out.println(output);
	}

}