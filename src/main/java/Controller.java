import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Action;
import Entities.Tiles.HallwayTile;
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


    /**
     * The game is run in this here
     */
    private Controller() {
        view = new TextView(); // fixme

        game = new Game(getInput());
        currentPlayer = game.getCurrentPlayers().get(0);


        boolean gameFinished = false;
        update(view);

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
     * @param p The current player
     */
    private void doCommand(Player p) {

        System.out.println("Select Command:");
        String avail = "[" + Arrays.stream(Action.values()).map(action -> this.createLabel(action, p)).collect(Collectors.joining(", ")) + "]";
        System.out.println("Current Valid Moves -\n" + avail);
        int input = getInput();
        Action selected = Action.values()[input - 1];
        if (selected == null || !this.game.getCanMove(p, selected)) {
            System.out.println("That move is unavailable please select another");
            return;
        }
        switch (selected) {
            case NORTH:
            case SOUTH:
            case EAST:
            case WEST:
                game.movePlayer(p, selected);
                break;
            case SUGGESTION:
                this.makeSuggestion();
                break;
            case ACCUSATION:
                this.makeAccusation(p); //TODO
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

    private String createLabel(Action action, Player p) {
        String text = (action.ordinal() + 1) + ": ";
        if (game.canDoAction(action, p)) {
            text += action.getLabel();
        }
        return text;
    }

    /**
     * Update the visuals
     */
    public void update(View v) {
        game.draw(v);
    }


    public void makeSuggestion() {
        while (!game.suggestedThisTurn) {
            doSuggestCommand();
        }
        List<Player> clone = game.getCurrentPlayers();
        clone.remove(currentPlayer);
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

    private void doSuggestCommand() {
        List<String> availSuggestions = game.getAvailSuggestions();
        System.out.println(game.getAvailSuggestions()); // fixme
        int input = getInput();

        switch (input) {
            case 1:
                if (availSuggestions.get(0).equals("1: Select Character")) {
                    cardSelection(currentPlayer, "Suggest Character");
                } else if (availSuggestions.get(0).equals("1: Change Character")) {
                    cardSelection(currentPlayer, "Suggest Character");
                } else throw new Error("Error unexpected move found during suggest selectCharacter");
                break;

            case 2:
                if (availSuggestions.get(1).equals("2: Select Weapon")) {
                    cardSelection(currentPlayer, "Suggest Weapon");
                } else if (availSuggestions.get(1).equals("2: Change Weapon")) {
                    cardSelection(currentPlayer, "Suggest Weapon");
                } else throw new Error("Error unexpected move found during suggest selectWeapon");
                break;

            case 3:
                if (availSuggestions.get(2).equals("3: Select Room")) {
                    cardSelection(currentPlayer, "Suggest Room");
                } else if (availSuggestions.get(2).equals("3: Change Room")) {
                    cardSelection(currentPlayer, "Suggest Room");
                } else throw new Error("Error unexpected move found during suggest selectRoom");
                break;

            case 4:
                if (availSuggestions.get(3).equals("4: Confirm Selection")) {
                    game.suggestedThisTurn = true;
                } else throw new Error("Error unexpected move found during suggest confirm");
                break;
        }


    }

    private void cardSelection(Player p, String mode) {

        if (mode.equals("Refute")) {
            view.printHand(p);
            game.thisRefutedCard = p.getCardsInHand().get(getInput());

            if (!(game.thisRefutedCard.equals(game.selectedCharacter) && game.thisRefutedCard.equals(game.selectedWeapon) && game.thisRefutedCard.equals(game.selectedRoom))) {
                System.out.println("invalid suggestion try again");
                cardSelection(p, mode);
                return;
            }
            game.refutedCards.add(game.thisRefutedCard);

        } else if (mode.equals("Suggest Character")) {
            List<CharacterCard> chrClone = game.gameLoader.getChrCards();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < chrClone.size(); i++) {
                CharacterCard c = chrClone.get(i);
                output.append(i).append(": ").append(c.getCardName()).append(" ");
            }
            System.out.println(output.toString());
            game.selectedCharacter = chrClone.get(getInput());

        } else if (mode.equals("Suggest Weapon")) {
            List<WeaponCard> wepClone = game.gameLoader.getWeapCards();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < wepClone.size(); i++) {
                WeaponCard c = wepClone.get(i);
                output.append(i).append(": ").append(c.getCardName()).append(" ");
            }
            System.out.println(output.toString());
            game.selectedWeapon = wepClone.get(getInput());

        } else if (mode.equals("Suggest Room")) {
            List<RoomCard> roomClone = game.gameLoader.getRoomCards();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < roomClone.size(); i++) {
                RoomCard c = roomClone.get(i);
                output.append(i).append(": ").append(c.getCardName()).append(" ");
            }
            System.out.println(output.toString());
            game.selectedRoom = roomClone.get(getInput());

        } else throw new Error("Error unexpected move found during select card select");
    }


    private void doRefute(Player p) {
        System.out.println(p.getCharacterName());
        System.out.println("Select Command: ");
        System.out.println(game.getAvailRefutations(p));
        int input = getInput();
        switch (input) {
            case 1:
                if (game.getAvailRefutations(p).get(0).equals("1: Pass")) {
                    break;
                } else {// fixme make more secure
                    cardSelection(p, "Refute");
                }

        }
    }


    public void makeAccusation(Player p) {
        System.out.println("not implemented");
        // TODO: Implement
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Cluedo");
        System.out.println("Starting Game...");
        new Controller();
    }

}
