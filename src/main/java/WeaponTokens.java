public class WeaponTokens {

    //WeaponTokens Attributes
    private String name;
    private String colour;

    public WeaponTokens(String aName, String aColour) {
        name = aName;
        colour = aColour;
    }

    public boolean setName(String aName) {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setColour(String aColour) {
        boolean wasSet = false;
        colour = aColour;
        wasSet = true;
        return wasSet;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public void delete() {
    }


    public String toString() {
        return super.toString() + "[" +
                "name" + ":" + getName() + "," +
                "colour" + ":" + getColour() + "]";
    }
}


