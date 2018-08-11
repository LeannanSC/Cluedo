import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    //Player Attributes
    private Point location;
    private String characterName;
    private String colour;

    //Player Associations
    private List<Card> cardsInHand;

    public Player(Point aLocation, String aCharacterName, String aColour) {
        location = aLocation;
        characterName = aCharacterName;
        colour = aColour;
        cardsInHand = new ArrayList<>();
    }

    public boolean setLocation(Point aLocation) {
        boolean wasSet = false;
        location = aLocation;
        wasSet = true;
        return wasSet;
    }

    public Point getLocation() {
        return location;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getColour() {
        return colour;
    }

    public Card getCardInHandAtIndex(int index) {
        return cardsInHand.get(index);
    }

    public List<Card> getCardsInHand() {
        return Collections.unmodifiableList(cardsInHand);
    }


    public void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public void move() {

    }

    public void draw() {
    }


    public String toString() {
        return super.toString() + "[" +
                "characterName" + ":" + getCharacterName() + "," +
                "colour" + ":" + getColour() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "location" + "=" + (getLocation() != null ? !getLocation().equals(this) ? getLocation().toString().replaceAll("  ", "    ") : "this" : "null");
    }
}