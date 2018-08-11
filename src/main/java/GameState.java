public class GameState {

    //GameState Attributes
    private State currentState;

    public GameState() {
        this.currentState = State.START_TURN;
    }

    //todo ask sophie
    private enum State {
        START_TURN,
        ROLL_DICE,
        MAKE_GUESS,
        NEXT_PLAYER

    }

    public void advanceGameState() {
        int nextStateIndex = currentState.ordinal() + 1;
        currentState = State.values()[nextStateIndex];
    }

    public void endTurn(){
        currentState = State.NEXT_PLAYER;
    }

}