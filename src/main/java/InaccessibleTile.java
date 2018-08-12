public class InaccessibleTile extends Tile {

    public InaccessibleTile(String[] drawMethod) {
        super("Inaccessible Tile", false, drawMethod);
    }


    // todo ask sophie about design
    protected static String[] getDesign(){
        String[] design = new String[3];
        design[0] = "xxx";
        design[1] = "xxx";
        design[2] = "xxx";
        return design;
    }
}