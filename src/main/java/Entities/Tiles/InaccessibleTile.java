package Entities.Tiles;

public class InaccessibleTile extends Tile {

    public InaccessibleTile() {
        super("Inaccessible Tile", false);}

    @Override
    public String[] draw() {
        return new String[]{"XXX", "XXX", "XXX"};
    }

}