/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 105 "model.ump"
// line 219 "model.ump"
public class Controller
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Controller Associations
  private View view;
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Controller(View aView, Board aBoard)
  {
    if (!setView(aView))
    {
      throw new RuntimeException("Unable to create Controller due to aView");
    }
    if (!setBoard(aBoard))
    {
      throw new RuntimeException("Unable to create Controller due to aBoard");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public View getView()
  {
    return view;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setView(View aNewView)
  {
    boolean wasSet = false;
    if (aNewView != null)
    {
      view = aNewView;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBoard(Board aNewBoard)
  {
    boolean wasSet = false;
    if (aNewBoard != null)
    {
      board = aNewBoard;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    view = null;
    board = null;
  }

  // line 109 "model.ump"
   public void update(){
    
  }

  // line 112 "model.ump"
   public void nextPhase(){
    
  }

  // line 115 "model.ump"
   public void nextTurn(){
    
  }

  // line 118 "model.ump"
   public void makeGuess(){
    
  }

  // line 121 "model.ump"
   public void makeAccusation(){
    
  }

  // line 124 "model.ump"
   public void rollDice(){
    
  }

}