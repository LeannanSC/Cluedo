/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.*;

// line 13 "model.ump"
// line 139 "model.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private int width;
  private int height;

  //Board Associations
  private GameState gameState;
  private List<WeaponTokens> weaponTokens;
  private List<Tile> tiles;
  private List<Player> players;
  private List<Card> allCards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board()
  {
    width = 24;
    height = 25;
    if (!setGameState(new GameState())){
      throw new RuntimeException("Unable to create Board due to aGameState");
    }

    // create weapon tokens
    List<WeaponTokens> temp = new ArrayList<>();
    temp.add();

    weaponTokens = new ArrayList<WeaponTokens>();
    boolean didAddWeaponTokens = setWeaponTokens(allWeaponTokens);
    if (!didAddWeaponTokens)
    {
      throw new RuntimeException("Unable to create Board, must have 6 weaponTokens");
    }
    tiles = new ArrayList<Tile>();
    players = new ArrayList<Player>();
    boolean didAddPlayers = setPlayers(allPlayers);
    if (!didAddPlayers)
    {
      throw new RuntimeException("Unable to create Board, must have 3 to 6 players");
    }
    allCards = new ArrayList<Card>();
    boolean didAddAllCards = setAllCards(allAllCards);
    if (!didAddAllCards)
    {
      throw new RuntimeException("Unable to create Board, must have 21 allCards");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(int aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }
  /* Code from template association_GetOne */
  public GameState getGameState()
  {
    return gameState;
  }
  /* Code from template association_GetMany */
  public WeaponTokens getWeaponToken(int index)
  {
    WeaponTokens aWeaponToken = weaponTokens.get(index);
    return aWeaponToken;
  }

  public List<WeaponTokens> getWeaponTokens()
  {
    List<WeaponTokens> newWeaponTokens = Collections.unmodifiableList(weaponTokens);
    return newWeaponTokens;
  }

  public int numberOfWeaponTokens()
  {
    int number = weaponTokens.size();
    return number;
  }

  public boolean hasWeaponTokens()
  {
    boolean has = weaponTokens.size() > 0;
    return has;
  }

  public int indexOfWeaponToken(WeaponTokens aWeaponToken)
  {
    int index = weaponTokens.indexOf(aWeaponToken);
    return index;
  }
  /* Code from template association_GetMany */
  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetMany */
  public Card getAllCard(int index)
  {
    Card aAllCard = allCards.get(index);
    return aAllCard;
  }

  public List<Card> getAllCards()
  {
    List<Card> newAllCards = Collections.unmodifiableList(allCards);
    return newAllCards;
  }

  public int numberOfAllCards()
  {
    int number = allCards.size();
    return number;
  }

  public boolean hasAllCards()
  {
    boolean has = allCards.size() > 0;
    return has;
  }

  public int indexOfAllCard(Card aAllCard)
  {
    int index = allCards.indexOf(aAllCard);
    return index;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGameState(GameState aNewGameState)
  {
    boolean wasSet = false;
    if (aNewGameState != null)
    {
      gameState = aNewGameState;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfWeaponTokens()
  {
    return 6;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeaponTokens()
  {
    return 6;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfWeaponTokens()
  {
    return 6;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setWeaponTokens(WeaponTokens... newWeaponTokens)
  {
    boolean wasSet = false;
    ArrayList<WeaponTokens> verifiedWeaponTokens = new ArrayList<WeaponTokens>();
    for (WeaponTokens aWeaponToken : newWeaponTokens)
    {
      if (verifiedWeaponTokens.contains(aWeaponToken))
      {
        continue;
      }
      verifiedWeaponTokens.add(aWeaponToken);
    }

    if (verifiedWeaponTokens.size() != newWeaponTokens.length || verifiedWeaponTokens.size() != requiredNumberOfWeaponTokens())
    {
      return wasSet;
    }

    weaponTokens.clear();
    weaponTokens.addAll(verifiedWeaponTokens);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTiles()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    tiles.add(aTile);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    if (tiles.contains(aTile))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 3;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPlayers()
  {
    return 6;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() < maximumNumberOfPlayers())
    {
      players.add(aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (!players.contains(aPlayer))
    {
      return wasRemoved;
    }

    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }

    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setPlayers(Player... newPlayers)
  {
    boolean wasSet = false;
    ArrayList<Player> verifiedPlayers = new ArrayList<Player>();
    for (Player aPlayer : newPlayers)
    {
      if (verifiedPlayers.contains(aPlayer))
      {
        continue;
      }
      verifiedPlayers.add(aPlayer);
    }

    if (verifiedPlayers.size() != newPlayers.length || verifiedPlayers.size() < minimumNumberOfPlayers() || verifiedPlayers.size() > maximumNumberOfPlayers())
    {
      return wasSet;
    }

    players.clear();
    players.addAll(verifiedPlayers);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfAllCards()
  {
    return 21;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAllCards()
  {
    return 21;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfAllCards()
  {
    return 21;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setAllCards(Card... newAllCards)
  {
    boolean wasSet = false;
    ArrayList<Card> verifiedAllCards = new ArrayList<Card>();
    for (Card aAllCard : newAllCards)
    {
      if (verifiedAllCards.contains(aAllCard))
      {
        continue;
      }
      verifiedAllCards.add(aAllCard);
    }

    if (verifiedAllCards.size() != newAllCards.length || verifiedAllCards.size() != requiredNumberOfAllCards())
    {
      return wasSet;
    }

    allCards.clear();
    allCards.addAll(verifiedAllCards);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    gameState = null;
    weaponTokens.clear();
    tiles.clear();
    players.clear();
    allCards.clear();
  }

  // line 20 "model.ump"
   public void draw(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "width" + ":" + getWidth()+ "," +
            "height" + ":" + getHeight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameState = "+(getGameState()!=null?Integer.toHexString(System.identityHashCode(getGameState())):"null");
  }
}