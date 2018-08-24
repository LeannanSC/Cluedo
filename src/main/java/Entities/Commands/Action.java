package Entities.Commands;

public enum Action {
    NORTH("Move North"), SOUTH("Move South"), EAST("Move East"), WEST("Move West"), SUGGESTION("Make Suggestion"), ACCUSATION("Make Accusation"), TURN("End Turn"), INFO("Show Board Info");
    private final String label;

    Action(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}


