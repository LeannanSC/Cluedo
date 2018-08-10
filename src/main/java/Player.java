/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 66 "model.ump"
// line 191 "model.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private Point location;
  private String characterName;
  private String colour;

  //Player Associations
  private List<Card> cardsInHand;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(Point aLocation, String aCharacterName, String aColour, Card... allCardsInHand)
  {
    location = aLocation;
    characterName = aCharacterName;
    colour = aColour;
    cardsInHand = new ArrayList<Card>();
    boolean didAddCardsInHand = setCardsInHand(allCardsInHand);
    if (!didAddCardsInHand)
    {
      throw new RuntimeException("Unable to create Player, must have 6 cardsInHand");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(Point aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setCharacterName(String aCharacterName)
  {
    boolean wasSet = false;
    characterName = aCharacterName;
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

  public Point getLocation()
  {
    return location;
  }

  public String getCharacterName()
  {
    return characterName;
  }

  public String getColour()
  {
    return colour;
  }
  /* Code from template association_GetMany */
  public Card getCardsInHand(int index)
  {
    Card aCardsInHand = cardsInHand.get(index);
    return aCardsInHand;
  }

  public List<Card> getCardsInHand()
  {
    List<Card> newCardsInHand = Collections.unmodifiableList(cardsInHand);
    return newCardsInHand;
  }

  public int numberOfCardsInHand()
  {
    int number = cardsInHand.size();
    return number;
  }

  public boolean hasCardsInHand()
  {
    boolean has = cardsInHand.size() > 0;
    return has;
  }

  public int indexOfCardsInHand(Card aCardsInHand)
  {
    int index = cardsInHand.indexOf(aCardsInHand);
    return index;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfCardsInHand()
  {
    return 6;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCardsInHand()
  {
    return 6;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfCardsInHand()
  {
    return 6;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setCardsInHand(Card... newCardsInHand)
  {
    boolean wasSet = false;
    ArrayList<Card> verifiedCardsInHand = new ArrayList<Card>();
    for (Card aCardsInHand : newCardsInHand)
    {
      if (verifiedCardsInHand.contains(aCardsInHand))
      {
        continue;
      }
      verifiedCardsInHand.add(aCardsInHand);
    }

    if (verifiedCardsInHand.size() != newCardsInHand.length || verifiedCardsInHand.size() != requiredNumberOfCardsInHand())
    {
      return wasSet;
    }

    cardsInHand.clear();
    cardsInHand.addAll(verifiedCardsInHand);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    cardsInHand.clear();
  }

  // line 75 "model.ump"
   public void move(){
    
  }

  // line 78 "model.ump"
   public void draw(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "characterName" + ":" + getCharacterName()+ "," +
            "colour" + ":" + getColour()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "location" + "=" + (getLocation() != null ? !getLocation().equals(this)  ? getLocation().toString().replaceAll("  ","    ") : "this" : "null");
  }
}