package Entities;

import Entities.Cards.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    //Player Attributes
    private Point location;
    private final String characterName;
    private final String colour;

    private int movesRemaining;

    //Player Associations
    private List<Card> cardsInHand;

    public Player(Point location, String characterName, String colour) {
        this.location = location;
        this.characterName = characterName;
        this.colour = colour;
        this.cardsInHand = new ArrayList<>();
        this.movesRemaining = 0;
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

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public void move(String direction) {
        movesRemaining--;
        switch (direction) {

            case "North":
                location = new Point(location.x,location.y - 1);

                break;

            case "South":
                location = new Point(location.x, location.y + 1);
                break;

            case "East":
                location = new Point(location.x + 1 , location.y);
                break;

            case "West":
                location = new Point(location.x - 1, location.y);
                break;

                default: throw new Error();
        }
    }

    public void draw() {
    }

    public int getMovesRemaining() {
        return movesRemaining;
    }

    public void setMovesRemaining(int moves) {
        movesRemaining = moves;
    }
}