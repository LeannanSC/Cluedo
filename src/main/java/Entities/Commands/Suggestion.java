package Entities.Commands;

public enum Suggestion {

    CHARACTER("Select Character"), WEAPON("Select Weapon"), ROOM("Select Room"), CONFIRM("Confirm Selection");
    private final String label;

    Suggestion(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}