package Entities;

/**
 * Attributes to display weapons on the board
 */
public class WeaponToken {

	//WeaponToken Attributes
	private String name;
	private String icon;

	/**
	 * Constructor
	 *
	 * @param name   The name of the weapon
	 * @param colour The icon, the first character of which is represented on the board
	 */
	public WeaponToken(String name, String colour) {
		this.name = name;
		this.icon = colour;
	}

	public boolean setName(String name) {
		boolean wasSet = false;
		this.name = name;
		wasSet = true;
		return wasSet;
	}

	public boolean setIcon(String icon) {
		boolean wasSet = false;
		this.icon = icon;
		wasSet = true;
		return wasSet;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public void delete() {
	}

	public String toString() {
		return super.toString() + "[" +
				"name" + ":" + getName() + "," +
				"icon" + ":" + getIcon() + "]";
	}
}


