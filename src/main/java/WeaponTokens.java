/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 37 "model.ump"
// line 159 "model.ump"
public class WeaponTokens
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeaponTokens Attributes
  private String name;
  private String colour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeaponTokens(String aName, String aColour)
  {
    name = aName;
    colour = aColour;
  }

  // WORK DAMN YOU

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

  public boolean setColour(String aColour)
  {
    boolean wasSet = false;
    colour = aColour;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getColour()
  {
    return colour;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "colour" + ":" + getColour()+ "]";
  }
}


