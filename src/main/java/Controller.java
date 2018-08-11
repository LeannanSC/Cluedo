/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 98 "model.ump"
// line 215 "model.ump"
public class Controller
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Controller Associations
  private Board board;
  private View view;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Controller(Board aBoard, View aView)
  {
    if (!setBoard(aBoard))
    {
      throw new RuntimeException("Unable to create Controller due to aBoard");
    }
    if (!setView(aView))
    {
      throw new RuntimeException("Unable to create Controller due to aView");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_GetOne */
  public View getView()
  {
    return view;
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

  public void delete()
  {
    board = null;
    view = null;
  }

  // line 102 "model.ump"
   public void update(){
    
  }

  // line 105 "model.ump"
   public void nextPhase(){
    
  }

  // line 108 "model.ump"
   public void nextTurn(){
    
  }

  // line 111 "model.ump"
   public void makeGuess(){
    
  }

  // line 114 "model.ump"
   public void makeAccusation(){
    
  }

  // line 117 "model.ump"
   public void rollDice(){
    
  }

}