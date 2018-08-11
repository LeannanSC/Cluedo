/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 82 "model.ump"
// line 203 "model.ump"
public class GameState
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameState Attributes
  private enum currentState;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameState(enum aCurrentState)
  {
    currentState = aCurrentState;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentState(enum aCurrentState)
  {
    boolean wasSet = false;
    currentState = aCurrentState;
    wasSet = true;
    return wasSet;
  }

  public enum getCurrentState(){
    return currentState;
  }

  public void delete()
  {}

  // line 87 "model.ump"
   public void advanceGameState(){

  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "currentState" + "=" + (getCurrentState() != null ? !getCurrentState().equals(this)  ? getCurrentState().toString().replaceAll("  ","    ") : "this" : "null");
  }
}