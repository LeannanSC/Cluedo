/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 44 "model.ump"
// line 164 "model.ump"
abstract class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private String name;
  private boolean canAccess;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(String aName, boolean aCanAccess)
  {
    name = aName;
    canAccess = aCanAccess;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCanAccess(boolean aCanAccess)
  {
    boolean wasSet = false;
    canAccess = aCanAccess;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getCanAccess()
  {
    return canAccess;
  }

  public void delete()
  {}

  // line 50 "model.ump"
   public void draw(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "canAccess" + ":" + getCanAccess()+ "]";
  }
}