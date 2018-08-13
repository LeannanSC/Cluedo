package Entities.Tiles;

public class HallwayTile extends Tile {

    public HallwayTile(String[] drawMethod) {
        super("Hallway Entities.Tiles.Tile", true, drawMethod);
    }

    // todo ask sophie about design
    protected static String[] getDesign(){
        String[] design = new String[3];
        design[0] = "*-*";
        design[1] = "| |";
        design[2] = "*-*";
        return design;
    }
}