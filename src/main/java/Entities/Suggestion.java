package Entities;

public enum Suggestion {

    CHARACTER("Select Character"), WEAPON("Move South"), ROOM("Move East"), CONFIRM("Move East");
    private final String label;

    Suggestion(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}