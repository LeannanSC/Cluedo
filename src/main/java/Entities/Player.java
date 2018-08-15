package Entities;

import Entities.Cards.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the Player.
 */
public class Player {

	//Player Attributes
	private Point location;
	private final String characterName;
	private final String colour; // The first letter is used for board representation
	private int movesRemaining;

	//Player Associations
	private List<Card> cardsInHand;

	/**
	 * Constructor
	 *
	 * @param location      Starting location of the player
	 * @param characterName The name of the players character
	 * @param colour        The colour of the character
	 */
	public Player(Point location, String characterName, String colour) {
		this.location = location;
		this.characterName = characterName;
		this.colour = colour;
		this.cardsInHand = new ArrayList<>();
		this.movesRemaining = 0;
	}

	/**
	 * Adds a card to the players hand. Used in initialisation
	 *
	 * @param card The card to be added to the hand
	 */
	public void addCardToHand(Card card) {
		this.cardsInHand.add(card);
	}

	/**
	 * Moves the player in the intended direction
	 *
	 * @param direction The cardinal direction the player wants to move in
	 */
	public void move(String direction) {
		movesRemaining--;
		switch (direction) {

			case "North":
				location = new Point(location.x, location.y - 1);

				break;

			case "South":
				location = new Point(location.x, location.y + 1);
				break;

			case "East":
				location = new Point(location.x + 1, location.y);
				break;

			case "West":
				location = new Point(location.x - 1, location.y);
				break;

			default:
				throw new Error();
		}
	}

	public void draw() {
		// FIXME: Unused empty method
	}

	// -------------------
	// GETTERS AND SETTERS
	// -------------------
	public boolean setLocation(Point aLocation) {
		// FIXME: Unused method
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
		// FIXME: Unused method
		return cardsInHand.get(index);
	}

	public List<Card> getCardsInHand() {
		return Collections.unmodifiableList(cardsInHand);
	}

	public int getMovesRemaining() {
		return movesRemaining;
	}

	public void setMovesRemaining(int moves) {
		movesRemaining = moves;
	}
}