import Entities.Player;
import Entities.Tiles.HallwayTile;
import Entities.Tiles.RoomTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Controller class handles the user inputs and runs the game overall
 */
public class Controller {

    Game game;
    GameLoader gameLoader;
    Player currentPlayer;
    boolean rolledThisTurn = false;
    boolean movedThisTurn = true;

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
                    nextTurn(currentPlayer);
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

    private int getInput() {

        try {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            int input = Integer.parseInt(line);
            return input;
        } catch (Exception e) {
            throw new Error("Input Parsing error");
        }

    }

    public List<String> getAvailMoves() {
        List<String> currentOptions = new ArrayList<>();
        // add conditions limiting options todo

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
            }else {
                currentOptions.add("7:");
            }

            currentOptions.add("8: Show Board Info");

            if (currentOptions.size() != 8) {
                throw new Error("Options incorrect size");
            }
            return currentOptions;
        }


        private void doCommand (Player p,int input){

            switch (input) {

                case 1: // Move North
                    if (getAvailMoves().get(0).equals("1: Move North")) {
                        game.movePlayer(currentPlayer, "North");
                        movedThisTurn = true;
                        update();
                    } else if (getAvailMoves().contains("1:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during move north");

                    break;

                case 2: // Move South
                    if (getAvailMoves().get(1).equals("2: Move South")) {
                        game.movePlayer(currentPlayer, "South");
                        movedThisTurn = true;
                        update();
                    } else if (getAvailMoves().contains("2:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during mov south");

                    break;

                case 3: // Move East
                    if (getAvailMoves().get(2).equals("3: Move East")) {
                        game.movePlayer(currentPlayer, "East");
                        movedThisTurn = true;
                        update();
                    } else if (getAvailMoves().contains("3:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during move east");

                    break;

                case 4: // Move West
                    if (getAvailMoves().get(3).equals("4: Move West")) {
                        game.movePlayer(currentPlayer, "West");
                        movedThisTurn = true;
                        update();
                    } else if (getAvailMoves().contains("4:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during move west");

                    break;

                case 5: // Make Suggestion
                    if (getAvailMoves().get(4).equals("5:  Make Suggestion")) {
                        makeSuggestion(p);//todo

                    } else if (getAvailMoves().contains("5:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during make suggestion");

                    break;

                case 6: // Make Accusation
                    if (getAvailMoves().get(5).equals("6: Make Accusation")) {
                        makeAccusation(p);//todo

                    } else if (getAvailMoves().contains("6:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during make accusation");

                    break;

                case 7: // End Turn
                    if (getAvailMoves().get(6).equals("7: End Turn")) {
                        nextTurn(p);
                        System.out.println("Turn Ended");

                    } else if (getAvailMoves().contains("7:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during end turn");

                    break;

                case 8: // board info
                    if (getAvailMoves().get(7).equals("8: Show Board Info")) {
                        //todo print board info
                        System.out.println("Board Inrormation");

                    } else if (getAvailMoves().contains("8:")) {
                        System.out.println("That move is unavailable please select another");
                    } else throw new Error("Error unexpected move found during show board info");

                    break;
                default: // Default
                    throw new Error("invalid input in doCommand");
            }
        }

        public void update () {
            game.draw();
        }

        public void nextTurn (Player p){
            int nextPlayer = game.getCurrentPlayers().indexOf(currentPlayer) + 1;

            if (nextPlayer > game.getCurrentPlayers().size() - 1) {
                nextPlayer = 0;
            }
            currentPlayer.setMovesRemaining(0);
            currentPlayer = game.getCurrentPlayers().get(nextPlayer);
            game.placesMoved = new ArrayList<>();
            rolledThisTurn = false;
            movedThisTurn = false;
        }

        public void makeSuggestion (Player p){
            System.out.println("not implemented");//fixme
        }

        public void makeAccusation (Player p) {
            System.out.println("not implemented");//fixme
        }

        public static void main (String[]args){
            System.out.println("Welcome to Cluedo");
            System.out.println("Starting Game...");
            new Controller();
        }

    }
