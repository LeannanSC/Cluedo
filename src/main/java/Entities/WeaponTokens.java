package Entities;

public class WeaponTokens {

    //Entities.WeaponTokens Attributes
    private String name;
    private String colour;

    public WeaponTokens(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public boolean setName(String name) {
        boolean wasSet = false;
        this.name = name;
        wasSet = true;
        return wasSet;
    }

    public boolean setColour(String colour) {
        boolean wasSet = false;
        this.colour = colour;
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


