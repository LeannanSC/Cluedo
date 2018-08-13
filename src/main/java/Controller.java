import Entities.Player;
import Views.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    //Controller Associations
    private Game game;
    private View view;

    public static void main(String[] args) {

        System.out.println("enter number of players(3-6): ");
        int numPlayer = getInput();
        Game game = new Game(numPlayer);

        boolean gameFinished = false;
        List<String> currentMovesAvailable = new ArrayList<>();

        while (!gameFinished) {
            for (Player p : game.getCurrentPlayers()) {
                System.out.println(getAvailMoves()); // fixme?
                doCommand(p, getInput());
            }

        }
    }

    private static int getInput() {
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());
        sc.close();
        return input;
    }

    public static List<String> getAvailMoves() {
        List<String> currentOptions = new ArrayList<>();
        // add conditions limiting options todo
        currentOptions.add("1: Roll Dice");
        currentOptions.add("2: Move North");
        currentOptions.add("3: Move South");
        currentOptions.add("4: Move East");
        currentOptions.add("5: Move West");
        currentOptions.add("6: Make Suggestion");
        currentOptions.add("7: Make Accusation");
        currentOptions.add("8: End Turn");
        return currentOptions;
    }


    private static void doCommand(Player p, int input) {
        switch (input){
            case 1: // Roll Dice
                break;

            case 2: // Move North

                break;

            case 3: // Move South
                break;

            case 4: // Move East
                break;

            case 5: // Move West
                break;

            case 6: // Make Suggestion
                break;

            case 7: // Make Accusation
                break;

            case 8: // End Turn
                break;

            default: // Default
                throw new Error("invalid input in doCommand");
        }
    }

    public void update() {

    }

    public void nextTurn() {

    }

    public void makeGuess() {

    }

    public void makeAccusation() {

    }

    public void rollDice() {

    }

}