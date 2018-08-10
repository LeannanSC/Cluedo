/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/



// line 33 "model.ump"
// line 150 "model.ump"
public class Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Card Attributes
  private String cardName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Card(String aCardName)
  {
    cardName = aCardName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCardName(String aCardName)
  {
    boolean wasSet = false;
    cardName = aCardName;
    wasSet = true;
    return wasSet;
  }

  public String getCardName()
  {
    return cardName;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "cardName" + ":" + getCardName()+ "]";
  }
}