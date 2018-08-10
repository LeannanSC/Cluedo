/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 38 "model.ump"
// line 156 "model.ump"
public class WeaponTokens
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeaponTokens Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeaponTokens(String aName)
  {
    name = aName;
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

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}