import Entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    Game game;
    GameLoader gameLoader;
    Random rng;
    boolean diceRolledThisTurn = false;
    Player currentPlayer;

    private Controller() {
        rng = new Random();
        System.out.println("Loading Assets...");
        gameLoader = new GameLoader(rng);

        System.out.println("Please Enter number of players(3-6): ");
        game = new Game(getInput(), gameLoader);
        currentPlayer = game.getCurrentPlayers().get(0);


        boolean gameFinished = false;
        while (!gameFinished) {
            System.out.println(getAvailMoves()); // fixme?
            System.out.println("Select Command");
            doCommand(currentPlayer, getInput());
        }
    }

    private static int getInput() {
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());
        sc.close();
        return input;
    }

    public List<String> getAvailMoves() {
        List<String> currentOptions = new ArrayList<>();
        // add conditions limiting options todo
        if (diceRolledThisTurn) {
            currentOptions.add("1: Roll Dice");
        } else {
            currentOptions.add("1:");
        }


        currentOptions.add("2: Move North");
//    } else {
//        currentOptions.add("2:");
//    }

        currentOptions.add("3: Move South");
//    } else {
//        currentOptions.add("3:");
//    }

        currentOptions.add("4: Move East");
        //    } else {
//        currentOptions.add("4:");
//    }

        currentOptions.add("5: Move West");
        //    } else {
//        currentOptions.add("5:");
//    }

        currentOptions.add("6: Make Suggestion");
        //    } else {
//        currentOptions.add("6:");
//    }

        currentOptions.add("7: Make Accusation");
        //    } else {
//        currentOptions.add("7:");
//    }

        currentOptions.add("8: End Turn");
        //    } else {
//        currentOptions.add("8:");
//    }
        if (currentOptions.size() != 8) { throw new Error("Options incorrect size");}
        return currentOptions;
    }


    private void doCommand(Player p, int input) {
        switch (input) {
            case 1: // Roll Dice
                if (getAvailMoves().get(0).equals("1: Roll Dice")) {
                    p.setMovesRemaining(rollDice());

                } else if (getAvailMoves().contains("1:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 2: // Move North
                if (getAvailMoves().get(1).equals("2: Move North")) {
                p.move("North");

                } else if (getAvailMoves().contains("2:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 3: // Move South
                if (getAvailMoves().get(2).equals("3: Move South")) {
                    p.move("South");

                } else if (getAvailMoves().contains("3:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 4: // Move East
                if (getAvailMoves().get(3).equals("4: Move East")) {
                    p.move("East");

                } else if (getAvailMoves().contains("4:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 5: // Move West
                if (getAvailMoves().get(4).equals("5: Move West")) {
                    p.move("West");

                } else if (getAvailMoves().contains("5:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 6: // Make Suggestion
                if (getAvailMoves().get(5).equals("6:  Make Suggestion")) {
                    makeSuggestion(p);//todo

                } else if (getAvailMoves().contains("6:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 7: // Make Accusation
                if (getAvailMoves().get(6).equals("7: Make Accusation")){
                    makeAccusation(p);//todo

                } else if (getAvailMoves().contains("7:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            case 8: // End Turn
                if (getAvailMoves().get(7).equals("8: End Turn")){
                    nextTurn(p);//todo
                    System.out.println("Turn Ended");

                } else if (getAvailMoves().contains("8:")){
                    System.out.println("That move is unavailable please select another");
                } else throw new Error("Error unexpected move found during roll dice");

                break;

            default: // Default
                throw new Error("invalid input in doCommand");
        }
    }

    public void update() {
    }

    public void nextTurn(Player p) {
        int nextPlayer = game.getCurrentPlayers().indexOf(currentPlayer) + 1;
        if (nextPlayer > 3) { nextPlayer = 0;}
        currentPlayer = game.getCurrentPlayers().get(nextPlayer);
        diceRolledThisTurn = false;
        System.out.println(p.getCharacterName() + "'s Turn");

    }

    public void makeSuggestion(Player p) {
        System.out.println("not implemented");//fixme

    }

    public void makeAccusation(Player p) {
        System.out.println("not implemented");//fixme

    }

    public int rollDice() {
        int d1 = rng.nextInt(6) + 1; // 0 -> 5 so + 1
        int d2 = rng.nextInt(6) + 1;

        int moves = d1 + d2;
        if (d1 > 6 || d2 > 6 || moves > 12) {
            throw new Error("Dice rolled too high");
        }
        diceRolledThisTurn = true;
        return moves;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Cluedo");
        System.out.println("Starting Game...");
        new Controller();
    }

}