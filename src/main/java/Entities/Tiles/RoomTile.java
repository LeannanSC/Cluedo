package Entities.Tiles;

public class RoomTile extends Tile {
    private boolean hasEntry;
    //todo ask sophie about room representation
    public RoomTile(String name, String[] drawMethod) {
        super(name, true, drawMethod);
    }

}