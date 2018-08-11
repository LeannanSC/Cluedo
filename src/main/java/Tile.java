/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4160.f573280ad modeling language!*/


import java.util.List;

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
	private String[] drawMethod = new String[3];

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public Tile(String aName, boolean aCanAccess)
	{
		name = aName;
		canAccess = aCanAccess;
		drawMethod[0] = "*-*";
		drawMethod[1] = "| |";
		drawMethod[2] = "*-*";
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

	public void setDrawMethod(String[] drawMethod) {
		this.drawMethod = drawMethod;
	}

	public String[] getDrawMethod(){
		return drawMethod;
	}

	public void delete(){}

	public String toString()
	{
		return super.toString() + "["+
				"name" + ":" + getName()+ "," +
				"canAccess" + ":" + getCanAccess()+ "]";
	}
}