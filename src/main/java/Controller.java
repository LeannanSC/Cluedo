import Entities.Cards.Card;
import Entities.Cards.CharacterCard;
import Entities.Cards.RoomCard;
import Entities.Cards.WeaponCard;
import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.RoomTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The main class. Controller class handles the user inputs and runs the game overall
 */
public class Controller {

    private Game game;
    private GameLoader gameLoader;
    private Player currentPlayer;
    private boolean rolledThisTurn = false;
    private boolean movedThisTurn = false;

    CharacterCard selectedCharacter = null;
    RoomCard selectedRoom = null;
    WeaponCard selectedWeapon = null;
    Card thisRefutedCard = null;
    List<Card> refutedCards = new ArrayList<>();

    /**
     * The game is run in this here
     */
    private Controller() {
        System.out.println("Loading Assets...");
        gameLoader = new GameLoader();

        System.out.println("Please Enter number of players(3-6): ");
        game = new Game(getInput(), gameLoader);
        currentPlayer = game.getCurrentPlayers().get(0);


        boolean gameFinished = false;
        update();

        while (!gameFinished) {
            System.out.println("\n");
            System.out.println("Character name - " + currentPlayer.getCharacterName() + ", Board Representation - " + currentPlayer.getColour().charAt(0));
            if (!rolledThisTurn) {
                System.out.println("Rolling dice...");
                currentPlayer.setMovesRemaining(game.rollDice());
                rolledThisTurn = true;
            }

            if (currentPlayer.getMovesRemaining() == 0) {
                if (gameLoader.getBoard()[currentPlayer.getLocation().x][currentPlayer.getLocation().y]
                        instanceof HallwayTile) {
                    System.out.println("you have run out of steps " +
                            "and are unable to continue this turn");
                    nextTurn();
                    continue;
                }
            }

            System.out.println("You have " + currentPlayer.getMovesRemaining() +
                    " steps remaining");
            System.out.println("Your hand -\n" + currentPlayer.getCardsInHand());

            System.out.println("Current Valid Moves -\n" + getAvailMoves());
            System.out.println("Select Command:");
            doCommand(currentPlayer, getInput());
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
            System.out.println("Invalid input");
            return getInput();
        }

    }

    /**
     * Checks what actions the character can do at their current location.
     * Each action has a corresponding integer which the user inputs in order to make that move.
     * If an action is invalid (e.g The player is next to a wall), that option is not displayed.
     *
     * @return A list of actions (as Strings) available to the player
     */
    public List<String> getAvailMoves() {
        List<String> currentOptions = new ArrayList<>();
        // add conditions limiting options

        if (game.getCanMove(currentPlayer, "North")) {
            currentOptions.add("1: Move North");
        } else {
            currentOptions.add("1:");
        }

        if (game.getCanMove(currentPlayer, "South")) {
            currentOptions.add("2: Move South");
        } else {
            currentOptions.add("2:");
        }

        if (game.getCanMove(currentPlayer, "East")) {
            currentOptions.add("3: Move East");
        } else {
            currentOptions.add("3:");
        }

        if (game.getCanMove(currentPlayer, "West")) {
            currentOptions.add("4: Move West");
        } else {
            currentOptions.add("4:");
        }

        if (gameLoader.getBoard()[currentPlayer.getLocation().x][currentPlayer.getLocation().y]
                instanceof RoomTile) {
            currentOptions.add("5: Make Suggestion");
        } else {
            currentOptions.add("5:");
        }

        if (gameLoader.getBoard()[currentPlayer.getLocation().x][currentPlayer.getLocation().y]
                instanceof RoomTile) {
            currentOptions.add("6: Make Accusation");
        } else {
            currentOptions.add("6:");
        }

        if (movedThisTurn) {
            currentOptions.add("7: End Turn");
        } else {
            currentOptions.add("7:");
        }

        currentOptions.add("8: Show Board Info");

        if (currentOptions.size() != 8) {
            throw new Error("Options incorrect size");
        }
        return currentOptions;
    }


    /**
     * Checks if the move the player wants to do is a valid move. If so, then proceed.
     *
     * @param p     The current player
     * @param input The action the player wants to take
     */
    private void doCommand(Player p, int input) {

        switch (input) {

            case 1: // Move North
                if (getAvailMoves().get(0).equals("1: Move North")) {
                    game.movePlayer(currentPlayer, "North");
                    movedThisTurn = true;
                    update();
                } else if (getAvailMoves().contains("1:")) {
                    System.out.println("That move is unavailable please select another");
                }
                break;


            case 2: // Move South
                if (getAvailMoves().get(1).equals("2: Move South")) {
                    game.movePlayer(currentPlayer, "South");
                    movedThisTurn = true;
                    update();
                } else if (getAvailMoves().contains("2:")) {
                    System.out.println("That move is unavailable please select another");
                }
                break;


            case 3: // Move East
                if (getAvailMoves().get(2).equals("3: Move East")) {
                    game.movePlayer(currentPlayer, "East");
                    movedThisTurn = true;
                    update();
                } else if (getAvailMoves().contains("3:")) {
                    System.out.println("That move is unavailable please select another");
                }
                break;


            case 4: // Move West
                if (getAvailMoves().get(3).equals("4: Move West")) {
                    game.movePlayer(currentPlayer, "West");
                    movedThisTurn = true;
                    update();
                } else if (getAvailMoves().contains("4:")) {
                    System.out.println("That move is unavailable please select another");
                }

                break;

            case 5: // Make Suggestion
                if (getAvailMoves().get(4).equals("5: Make Suggestion")) {
                    makeSuggestion();    // TODO:

                } else if (getAvailMoves().contains("5:")) {
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during make suggestion");
                break;

            case 6: // Make Accusation
                if (getAvailMoves().get(5).equals("6: Make Accusation")) {
                    makeAccusation(p);    // TODO:

                } else if (getAvailMoves().contains("6:")) {
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during make accusation");

                break;

            case 7: // End Turn
                if (getAvailMoves().get(6).equals("7: End Turn")) {
                    nextTurn();
                    System.out.println("Turn Ended");

                } else if (getAvailMoves().contains("7:")) {
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during end turn");
                break;


            case 8: // board info
                if (getAvailMoves().get(7).equals("8: Show Board Info")) {
                    // FIXME: Should the weapons and characters be listed on one line or multiple
                    System.out.println("Board Information:");
                    System.out.println("\tHallways have no marks");
                    System.out.println("\t Rooms are marked with +");
                    System.out.println("\tYou can enter rooms through doors, which are marked with 0");
                    System.out.println("\tYou cannot enter inaccessible tiles, which are marked with X");
                    System.out.println("\tPlayers are represented as such:");
                    System.out.println("\tMiss Scarlett: R, Colonel Mustard: Y, Mrs. White: W, Mr. Green: G, Mrs. Peacock: B, Professor Plum: P");
                    System.out.println("\tWeapons are represented as such:");
                    System.out.println("\tCandlestick: *, Dagger: %, Lead Pipe: 1, Revolver: q, Rope: &, Spanner: !");

                } else if (getAvailMoves().contains("8:")) {
                    System.out.println("That move is unavailable please select another");
                }
                break;

            default:
                System.out.println("That move is unavailable please select another");
        }
    }

    /**
     * Update the visuals
     */
    public void update() {
        game.draw();
    }

    /**
     * Selects the next player in turn order
     *
     *
     */
    public void nextTurn() {
        int nextPlayer = game.getCurrentPlayers().indexOf(currentPlayer) + 1;

        if (nextPlayer > game.getCurrentPlayers().size() - 1) {
            nextPlayer = 0;
        }
        currentPlayer.setMovesRemaining(0);
        currentPlayer = game.getCurrentPlayers().get(nextPlayer);
        game.placesMoved = new ArrayList<>();
        rolledThisTurn = false;
        movedThisTurn = false;
        selectedCharacter = null;
        selectedWeapon = null;
        selectedRoom = null;
    }

    public void makeSuggestion() {
        doSuggestCommand();
        List<Player> clone = game.getCurrentPlayers();
        clone.remove(currentPlayer);
        for (Player pl : clone) {
            System.out.println("Suggested cards: " + selectedCharacter
                    + ", " + selectedWeapon + ", " + selectedRoom);
            doRefute(pl);
            thisRefutedCard = null;
        }
        if (refutedCards.size() > 0){
            System.out.println(refutedCards);
        }
    }

    private void doSuggestCommand() {
        while (true) {
            System.out.println(suggestMenu());
            int input = getInput();

            switch (input) {
                case 1:
                    if (suggestMenu().get(0).equals("1: Select Character")) {
                        cardSelection(currentPlayer, "Suggest Character");
                    } else if (suggestMenu().get(0).equals("1: Change Character")) {
                        cardSelection(currentPlayer, "Suggest Character");
                    } else throw new Error("Error unexpected move found during suggest selectCharacter");

                case 2:
                    if (suggestMenu().get(1).equals("2: Select Weapon")) {
                        cardSelection(currentPlayer, "Suggest Weapon");
                    } else if (suggestMenu().get(1).equals("2: Change Weapon")) {
                        cardSelection(currentPlayer, "Suggest Weapon");
                    } else throw new Error("Error unexpected move found during suggest selectWeapon");

                case 3:
                    if (suggestMenu().get(2).equals("3: Select Room")) {
                        cardSelection(currentPlayer, "Suggest Room");
                    } else if (getAvailMoves().get(2).equals("3: Change Room")) {
                        cardSelection(currentPlayer, "Suggest Room");
                    } else throw new Error("Error unexpected move found during suggest selectRoom");


                case 4:
                    if (suggestMenu().get(3).equals("4: Confirm Selection")) {
                        return;

                    } else throw new Error("Error unexpected move found during suggest confirm");
            }
        }

    }

    private void cardSelection(Player p, String mode) {

        if(mode.equals("Refute")) {
            thisRefutedCard = p.getCardInHandAtIndex(getInput());
            refutedCards.add(thisRefutedCard);
            System.out.println(thisRefutedCard);
        } else if (mode.equals("Suggest Character")) {
            List<CharacterCard> chrClone = gameLoader.getChrCards();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < chrClone.size(); i++) {
                CharacterCard c = chrClone.get(i);
                output.append(i).append(": ").append(c.getCardName()).append(" ");
            }
            System.out.println(output.toString());
            selectedCharacter = chrClone.get(getInput());

        } else if (mode.equals("Suggest Weapon")) {
            List<WeaponCard> wepClone = gameLoader.getWeapCards();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < wepClone.size(); i++) {
                WeaponCard c = wepClone.get(i);
                output.append(i).append(": ").append(c.getCardName()).append(" ");
            }
            System.out.println(output.toString());
            selectedWeapon = wepClone.get(getInput());

        } else if (mode.equals("Suggest Room")) {
            List<RoomCard> roomClone = gameLoader.getRoomCards();
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < roomClone.size(); i++) {
                RoomCard c = roomClone.get(i);
                output.append(i).append(": ").append(c.getCardName()).append(" ");
            }
            System.out.println(output.toString());
            selectedRoom = roomClone.get(getInput());

        } else throw new Error("Error unexpected move found during select card select");
    }

    public List<String> suggestMenu() {
        List<String> currentOptions = new ArrayList<>();
        if (selectedCharacter == null) {
            currentOptions.add("1: Select Character");
        } else {
            currentOptions.add("1: Change Character");
        }

        if (selectedWeapon == null) {
            currentOptions.add("2: Select Weapon");
        } else {
            currentOptions.add("2: Change Weapon");
        }

        if (selectedRoom == null) {
            currentOptions.add("3: Select Room");
        } else {
            currentOptions.add("3: Change Room");
        }

        if (selectedCharacter != null && selectedWeapon != null && selectedRoom != null) {
            currentOptions.add("4: Confirm Selection");
        } else {
            currentOptions.add("4:");
        }

        if (currentOptions.isEmpty()) {
            throw new Error("unexpected current option size in suggest");
        }

        return currentOptions;

    }

    private void doRefute(Player p) {
        System.out.println(p.getCharacterName());
        System.out.println("Hand -");
        System.out.println(p.getCardsInHand());
        System.out.println("Select Command: ");
        System.out.println(refuteMenu(p));
        int input = getInput();
        switch (input) {
            case 1:
                if (refuteMenu(p).get(0).equals("1: Pass")) {
                    break;
                } else {
                    cardSelection(p,"Refute");
                }

        }
    }

    public List<String> refuteMenu(Player player) {
        List<String> currentOptions = new ArrayList<>();
        if (game.getCanRefute(player, selectedCharacter, selectedWeapon, selectedRoom)) {
            currentOptions.add("1: Refute");
        } else {
            currentOptions.add("1: Pass");
        }
        return currentOptions;
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
