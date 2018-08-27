import Entities.Cards.Card;
import Entities.Cards.RoomCard;
import Entities.Commands.Refutation;
import Entities.Player;
import Entities.Commands.Action;
import Entities.Commands.Suggestion;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class. Controller class handles the user inputs and runs the game overall
 */
public class Controller {

    private Game game;
    private View view;
    boolean gameFinished;

    /**
     * The game is run in this
     * received guidance from Sanjay Govind to learn how to
     * make the code more manageable as it was becoming messy
     */
    private Controller(View view) {
        game = new Game();
        this.view = view;
        game.initGame(view.getInput(game.gameLoader.getChrCards().size()));

        gameFinished = false;
        gameloop();
    }

    public void gameloop() {

        redraw();

        while (!gameFinished) {

            if (game.currentPlayer.isOut()) {
                view.printError("you have made a false accusation, you may only make refutations");
                endTurnSequence();
            }

            if (!game.rolledThisTurn) {
                game.rollDice();
            }

            if (!game.checkMovesRemaining()) {
                view.printError("you have run out of steps and are unable to continue this turn");
                endTurnSequence();
                continue;
            }

            view.printNewTurnText(game.currentPlayer);
            doCommand();
        }
    }


    /**
     * Checks if the move the player wants to do is a valid move. If so, then proceed.
     *
     */
    private void doCommand() {

        view.printCommandMenu(game.currentPlayer, game);
        int input = view.getInput(Action.values().length);
        Action selected = Action.values()[input - 1];

        if (!this.game.canDoAction(selected, game.currentPlayer)) {
            view.printError("That move is unavailable please select another");
            return;
        }

        switch (selected) {
            case NORTH:
            case SOUTH:
            case EAST:
            case WEST:
                game.movePlayer(game.currentPlayer, selected);
                redraw();
                break;
            case SUGGESTION:
                makeSuggestion();
                break;
            case ACCUSATION:
                makeAccusation();
                break;
            case TURN:
                endTurnSequence();
                break;
            case INFO:
                view.printBoardInfo();
                break;
        }
    }

    private void doSuggest() {
        view.printSuggestionMenu(game);
        int input = view.getInput(Suggestion.values().length);
        Suggestion selected = Suggestion.values()[input - 1];

        if (!this.game.canDoSuggestion(selected)) {
            view.printError("That move is unavailable please select another");
            return;
        }

        game.currentPlayer.setMovesRemaining(0);
        switch (selected) {
            case CHARACTER:

                view.printAllCharacterCards(game);

                int inputChr = view.getInput(game.gameLoader.getChrCards().size());

                game.selectedCharacter = game.gameLoader.getChrCards().get(inputChr - 1);
                break;

            case WEAPON:

                view.printAllWeaponCards(game);

                int inputWep = view.getInput(game.gameLoader.getWeapCards().size());
                game.selectedWeapon = game.gameLoader.getWeapCards().get(inputWep - 1);
                break;

            case ROOM:
                view.printAllRoomCards(game);
                int inputRoom = view.getInput(game.gameLoader.getRoomCards().size());
                game.selectedRoom = game.gameLoader.getRoomCards().get(inputRoom - 1);
                break;

            case CONFIRM:
                game.suggestedThisTurn = true;
                break;
        }
    }

    private void doRefute(Player player) {
        view.printRefutationMenu(player, game);
        int input = view.getInput(Refutation.values().length);

        Refutation selected = Refutation.values()[input - 1];

        if (!this.game.canDoRefutation(selected, player)) {
            view.printError("That move is unavailable please select another");
            doRefute(player);
            return;
        }

        switch (selected) {
            case REFUTE:
                view.printRefuteCardSelectionMenu(player);
                int input2 = view.getInput(player.getCardsInHand().toArray().length);
                Card potentialRefutedCard = player.getCardsInHand().get(input2 - 1);

                if (!(potentialRefutedCard.getCardName().equals(game.selectedCharacter.getCardName())
                        || potentialRefutedCard.getCardName().equals(game.selectedWeapon.getCardName())
                        || potentialRefutedCard.getCardName().equals(game.selectedRoom.getCardName()))) {
                    view.printError("Card is not suggested, select another card");
                    doRefute(player);
                    return;
                }
                game.thisRefutedCard = potentialRefutedCard;
                game.refutedCards.add(game.thisRefutedCard);
                break;

            case PASS:
                break;
        }
    }

    public void makeSuggestion() {
        String tileName = game.getBoard()[game.currentPlayer.getLocation().x][game.currentPlayer.getLocation().y].getName();
        for (RoomCard roomCard : game.gameLoader.getRoomCards()) {
            String roomName = roomCard.getCardName();

            if (tileName.equals(roomName)) {
                game.selectedRoom = roomCard;
                break;
            }
        }

        while (!game.suggestedThisTurn) {
            doSuggest();
        }

        List<Player> clone = new ArrayList<>(game.getCurrentPlayers());
        clone.remove(game.currentPlayer);
        for (Player p : clone) {
            doRefute(p);
            game.thisRefutedCard = null;
        }

        if (game.refutedCards.size() > 0) {
            view.printPassInstruction(game.currentPlayer);
            if (view.getInput(1) == 1) ;
            view.printRefutations(game);
        }
    }

    public void makeAccusation() {
        while (!game.suggestedThisTurn) {
            doSuggest();
        }

        if (game.checkSolution()) {
            gameFinished = true;
            view.printWinText(game.currentPlayer);
            return;
        } else {
            game.currentPlayer.setOut(true);
            game.getBoard()[game.currentPlayer.getLocation().x][game.currentPlayer.getLocation().y].setPlayer(null);
            view.printError("you have made a false accusation, you may only make refutations");
            endTurnSequence();
        }
    }


    /**
     * Update the visuals
     */
    public void redraw() {
         view.redraw(game);
    }


    public void endTurnSequence() {
        view.printPassInstruction(game.getNextPlayer(game.currentPlayer));
        if (view.getInput(1) == 1) {
            game.changeTurn(game.currentPlayer);
            if (allOut()){
                gameFinished = true;
                view.printLoseText(game.solutionCards);
                System.exit(0);
            }
            view.printEndTurnText();
            redraw();
        } else endTurnSequence();
    }

    private boolean allOut() {
        for (Player p: game.getCurrentPlayers()) {
            if (!p.isOut()){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Controller(new TextView());
    }

}
