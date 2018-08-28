import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Commands.Action;
import Entities.Commands.Refutation;
import Entities.Commands.Suggestion;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.InaccessibleTile;
import Entities.Tiles.RoomTile;
import Entities.Tiles.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Class for displaying objects using text
 */
public class TextView extends View {

    public TextView() {
        initView();
    }

    @Override
    public void redraw(Game game){
        drawBoard(game.getBoard(), GameLoader.WIDTH, GameLoader.HEIGHT);
    }

    @Override
    public int getInput(int arrayOptionSize) {
        try {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            int input = Integer.parseInt(line);
            if (input > arrayOptionSize) {
                System.out.println("Invalid input, please try again");
                return getInput(arrayOptionSize);
            }
            return input;
        } catch (Exception e) {
            System.out.println("Invalid character, please try again");
            return getInput(arrayOptionSize);
        }
    }


    /*
     * Draw entities and board
     */

    @Override
    public void drawBoard(Tile[][] board, int width, int height) {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();
            StringBuilder line3 = new StringBuilder();
            for (int x = 0; x < width; x++) {

                if (board[x][y] instanceof RoomTile) {
                    drawRoomTile((RoomTile) board[x][y], line1, line2, line3);
                }
                if (board[x][y] instanceof InaccessibleTile) {
                    drawInaccessibleTile((InaccessibleTile) board[x][y], line1, line2, line3);
                }
                if (board[x][y] instanceof HallwayTile) {
                    drawHallwayTile((HallwayTile) board[x][y], line1, line2, line3);
                }
            }
            output.append(line1).append('\n')
                    .append(line2).append('\n')
                    .append(line3).append('\n');
        }

        System.out.println(output);
    }

    @Override
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

    @Override
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

    @Override
    public void drawInaccessibleTile(InaccessibleTile t, StringBuilder line1, StringBuilder line2, StringBuilder line3) {
        line1.append("xxx");
        line2.append("xxx");
        line3.append("xxx");
    }


    /*
     * Draw Menus
     */

    @Override
    public void printCommandMenu(Player player, Game game) {
        String avail = Arrays.stream(Action.values()).map(
                action -> this.createLabel(action, player, game))
                .collect(Collectors.joining(", "));
        System.out.println("Current Valid Commands -\n" + avail);
    }

    @Override
    public void printSuggestionMenu(Game game) {
        String avail = Arrays.stream(Suggestion.values()).map(
                suggestion -> createLabel(suggestion, game))
                .collect(Collectors.joining(", "));
        System.out.println("Current Valid Commands -\n" + avail);

    }

    @Override
    public void printRefutationMenu(Player player, Game game) {
        System.out.println("Suggested cards: " + game.selectedCharacter
                + ", " + game.selectedWeapon + ", " + game.selectedRoom);

        System.out.println(player.getCharacterName());
        String avail = Arrays.stream(Refutation.values()).map(
                refutation -> createLabel(refutation, player, game))
                .collect(Collectors.joining(", "));
        System.out.println("Current Valid Commands -\n" + avail);
    }

    @Override
    public void printRefuteCardSelectionMenu(Player player) {
        System.out.println("Select card to refute");
        printHand(player);
    }




    /*
     * Draw cards for selection
     */

    @Override
    public void printAllCharacterCards(Game game) {
        StringBuilder output;
        List<CharacterCard> chrs = game.gameLoader.getChrCards();
        output = new StringBuilder();
        for (int i = 0; i < chrs.size(); i++) {
            CharacterCard c = chrs.get(i);
            output.append(i + 1).append(": ").append(c.getCardName()).append(" ");
        }
        System.out.println(output.toString());
    }

    @Override
    public void printAllWeaponCards(Game game) {
        StringBuilder output;
        List<WeaponCard> weps = game.gameLoader.getWeapCards();
        output = new StringBuilder();
        for (int i = 0; i < weps.size(); i++) {
            WeaponCard c = weps.get(i);
            output.append(i + 1).append(": ").append(c.getCardName()).append(" ");
        }
        System.out.println(output.toString());
    }

    @Override
    public void printAllRoomCards(Game game) {
        StringBuilder output;
        List<RoomCard> roomClone = game.gameLoader.getRoomCards();
        output = new StringBuilder();
        for (int i = 0; i < roomClone.size(); i++) {
            RoomCard c = roomClone.get(i);
            output.append(i + 1).append(": ").append(c.getCardName()).append(" ");
        }
        System.out.println(output.toString());
    }


    /*
     * Utility
     */

    @Override
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

    @Override
    public void printError(String error) {
        System.out.println(error);
    }


    @Override
    public void printHand(Player p) {

        System.out.println("Your hand -");

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < p.getCardsInHand().size(); i++) {
            Card c = p.getCardsInHand().get(i);
            output.append(i + 1).append(": ").append(c.getCardName()).append(" ");
        }

        System.out.println(output.toString());
    }

    @Override
    public void printRefutations(Game game) {
        printSpacer();
        System.out.println("Refutations: ");
        System.out.println(game.refutedCards);
    }

    @Override
    public void printWinText(Player player) {
        System.out.println("Congratulations "  + player.getCharacterName() + " YoU WIN");
    }

    @Override
    public void printLoseText(List<Card> solution) {
        System.out.println("Well... it seems as though no one guessed correctly, The Murderer Has Won");
        System.out.println("The solution was: " + solution);
    }

    @Override
    public void printNewTurnText(Player currentPlayer) {
        System.out.println("Character name - " + currentPlayer.getCharacterName() + ", Board Representation - " + currentPlayer.getColour().charAt(0));
        System.out.println("You have " + currentPlayer.getMovesRemaining() +
                " steps remaining");
        printHand(currentPlayer);
    }

    @Override
    public void printEndTurnText(){
        printSpacer();
    }

    public void printSpacer(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    @Override
    public void printPassInstruction(Player nextPlayer) {
        System.out.println("please pass the computer to the next player: " + nextPlayer.getCharacterName());
        System.out.println("Press 1 once received");
    }

    @Override
    public int getPlayers() {
        System.out.println("Loading Assets...");
        System.out.println("Please Enter number of players(3-6): ");
        return getInput(6);
    }

    private void initView() {
        System.out.println("Welcome to Cluedo");
        System.out.println("Starting Game...");



    }



    private String createLabel(Action action, Player player, Game game) {
        String text = (action.ordinal() + 1) + ": ";
        if (game.canDoAction(action, player)) {
            text += action.getLabel();
        }
        return text;
    }

    private String createLabel(Refutation refutation, Player player, Game game) {
        String text = (refutation.ordinal() + 1) + ": ";
        if (game.canDoRefutation(refutation, player)) {
            text += refutation.getLabel();
        }
        return text;
    }

    private String createLabel(Suggestion suggestion, Game game) {
        String text = (suggestion.ordinal() + 1) + ": ";
        if (game.canDoSuggestion(suggestion)) {
            text += suggestion.getLabel();
        }
        return text;
    }


    public static void main(String[] args) {
        new Controller(new TextView());

    }
}