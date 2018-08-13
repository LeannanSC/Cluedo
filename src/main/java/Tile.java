abstract class Tile {

    //Tile Attributes
    private final String name;
    private final boolean canAccess;
    private String[] drawMethod = new String[3];

    public Tile(String name, boolean canAccess, String[] drawMethod) {
        this.name = name;
        this.canAccess = canAccess;
        this.drawMethod = drawMethod;
    }

    public String getName() {
        return name;
    }

    public boolean getCanAccess() {
        return canAccess;
    }

    public String[] getDrawMethod() {
        return drawMethod;
    }

    public String toString() {
        return super.toString() + "[" +
                "name" + ":" + getName() + "," +
                "canAccess" + ":" + getCanAccess() + "]";
    }
}