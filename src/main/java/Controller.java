import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Action;
import Entities.Suggestion;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.RoomTile;
import Views.TextView;
import Views.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The main class. Controller class handles the user inputs and runs the game overall
 */
public class Controller {

    private Game game;
    private Player currentPlayer;
    private View view;
    boolean gameFinished;

    /**
     * The game is run in this here
     */
    private Controller() {
        view = new TextView(); // fixme move to view
        game = new Game(getInput());
        currentPlayer = game.getCurrentPlayers().get(0);


        gameFinished = false;
        redraw(view);

        while (!gameFinished) {
            System.out.println("Character name - " + currentPlayer.getCharacterName() + ", Board Representation - " + currentPlayer.getColour().charAt(0));
            if (!game.rolledThisTurn) {
                System.out.println("Rolling dice...");
                currentPlayer.setMovesRemaining(game.rollDice());
            }

            if (currentPlayer.getMovesRemaining() == 0) {
                if (game.getBoard()[currentPlayer.getLocation().x][currentPlayer.getLocation().y]
                        instanceof HallwayTile) {
                    System.out.println("you have run out of steps and are unable to continue this turn");
                    currentPlayer = game.changeTurn(currentPlayer);
                    continue;
                }
            }

            System.out.println("You have " + currentPlayer.getMovesRemaining() +
                    " steps remaining");
            view.printHand(currentPlayer);
            doCommand(currentPlayer);
        }
    }

    /**
     * Parses the users input from a string to an integer representing what action the player wants to take
     *
     * @return Int of the action the player wants to take
     */
    private int getInput() {

        try {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            int input = Integer.parseInt(line);
            return input;
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
            return getInput();
        }
    }


    /**
     * Checks if the move the player wants to do is a valid move. If so, then proceed.
     *
     * @param player The current player
     */
    private void doCommand(Player player) {

        String avail = Arrays.stream(Action.values()).map( //fixme
                action -> this.createLabel(action, player))
                .collect(Collectors.joining(", "));
        System.out.println("Current Valid Commands -\n" + avail);
        int input = getInput();

        Action selected = Action.values()[input - 1];

        if (selected == null || !this.game.canDoAction(selected, player)) {
            System.out.println("That move is unavailable please select another");
            return;
        }

        switch (selected) {
            case NORTH:
            case SOUTH:
            case EAST:
            case WEST:
                game.movePlayer(player, selected);
                game.draw(view);
                break;
            case SUGGESTION:
                makeSuggestion(player);
                break;
            case ACCUSATION:
                makeAccusation(player);
                break;
            case TURN:
                currentPlayer = game.changeTurn(currentPlayer);
                System.out.println("Turn Ended");
                break;
            case INFO:
                view.printBoardInfo();
                break;
        }
    }

    private void doSuggest(){

        String avail = Arrays.stream(Suggestion.values()).map( // fixme move to view
                suggestion -> this.createLabel(suggestion))
                .collect(Collectors.joining(", "));
        System.out.println("Current Valid Commands -\n" + avail);

        int input = getInput();

        Suggestion selected = Suggestion.values()[input - 1];

        if (selected == null || !this.game.canDoSuggestion(selected)) {
            System.out.println("That move is unavailable please select another");
            return;
        }

        StringBuilder output;

        switch (selected){
            case CHARACTER:
                List<CharacterCard> chrClone = game.gameLoader.getChrCards(); // fixme
                output = new StringBuilder();
                for (int i = 0; i < chrClone.size(); i++) {
                    CharacterCard c = chrClone.get(i);
                    output.append(i).append(": ").append(c.getCardName()).append(" ");
                }
                System.out.println(output.toString());
                game.selectedCharacter = chrClone.get(getInput());
                break;

            case WEAPON:
                List<WeaponCard> wepClone = game.gameLoader.getWeapCards();
                output = new StringBuilder();
                for (int i = 0; i < wepClone.size(); i++) {
                    WeaponCard c = wepClone.get(i);
                    output.append(i).append(": ").append(c.getCardName()).append(" ");
                }
                System.out.println(output.toString());
                game.selectedWeapon = wepClone.get(getInput());
                break;

            case ROOM:
                List<RoomCard> roomClone = game.gameLoader.getRoomCards();
                output = new StringBuilder();
                for (int i = 0; i < roomClone.size(); i++) {
                    RoomCard c = roomClone.get(i);
                    output.append(i).append(": ").append(c.getCardName()).append(" ");
                }
                System.out.println(output.toString());
                game.selectedRoom = roomClone.get(getInput());
                break;

            case CONFIRM:
                game.suggestedThisTurn = true;
                break;
        }
    }

    private void doRefute(Player p) { // fixme refactor to follow doCommand and doSuggest style
        System.out.println(p.getCharacterName());
        System.out.println("Select Command: ");
        System.out.println(game.getAvailRefutations(p));
        int input = getInput();
        switch (input) {
            case 1:
                if (game.getAvailRefutations(p).get(0).equals("1: Pass")) {
                    break;
                } else {// fixme make more secure
                    view.printHand(p);
                    game.thisRefutedCard = p.getCardsInHand().get(getInput());

                    if (!(game.thisRefutedCard.equals(game.selectedCharacter) && game.thisRefutedCard.equals(game.selectedWeapon) && game.thisRefutedCard.equals(game.selectedRoom))) {
                        System.out.println("invalid suggestion try again");
                        doRefute(p);
                        return;
                    }
                    game.refutedCards.add(game.thisRefutedCard);
                }

        }
    }

    public void makeSuggestion(Player player) {
        while (!game.suggestedThisTurn) {
            doSuggest();
        }
        List<Player> clone = game.getCurrentPlayers();
        clone.remove(player);
        for (Player p : clone) {
            System.out.println("Suggested cards: " + game.selectedCharacter
                    + ", " + game.selectedWeapon + ", " + game.selectedRoom);
            doRefute(p);
            game.thisRefutedCard = null;
        }

        if (game.refutedCards.size() > 0) {
            System.out.println("Refutations: ");
            System.out.println(game.refutedCards); // fixme
            currentPlayer = game.changeTurn(currentPlayer);
        }
    }

    public void makeAccusation(Player player) {
        for (RoomCard roomCard : game.gameLoader.getRoomCards()) {
            if ( game.getBoard()[player.getLocation().x][player.getLocation().y].getName().equals(roomCard.getCardName())){
                game.selectedRoom = roomCard;
            }
        }

        while (!game.suggestedThisTurn){
            doSuggest();
        }

        if (game.checkSolution()){
            gameFinished = true;
            return;
        } else {
            player.setOut(true);
        }
        //todo test
    }


    /**
     * Update the visuals
     */
    public void redraw(View v) {
        game.draw(v);
    }


    private String createLabel(Suggestion suggestion) {
        String text = (suggestion.ordinal() + 1) + ": ";
        if (game.canDoSuggestion(suggestion)) {
            text += suggestion.getLabel();
        }
        return text;
    }

    private String createLabel(Action action, Player player) {
        String text = (action.ordinal() + 1) + ": ";
        if (game.canDoAction(action, player)) {
            text += action.getLabel();
        }
        return text;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Cluedo");
        System.out.println("Starting Game...");
        new Controller();
    }

}
