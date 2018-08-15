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

	// Player Attributes
	private Point location;
	private final String characterName;
	private final String colour; // First character of colour represents the player on the board
	private int movesRemaining;

	// Player Associations
	private List<Card> cardsInHand;

	/**
	 * Constructor
	 *
	 * @param location      initial starting position of the player
	 * @param characterName The players characters name
	 * @param colour        The players characters colour
	 */
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

	/**
	 * Move player one tile in a direction, throws error if invalid
	 *
	 * @param direction which cardinal direction the player wants to move in
	 */
	public void move(String direction) {
		movesRemaining--;
		switch (direction) {

			case "North":
				location = new Point(location.x, location.y - 1);

				System.out.println("moved" + direction);
				break;

			case "South":
				location = new Point(location.x, location.y + 1);
				System.out.println("moved" + direction);
				break;

			case "East":
				location = new Point(location.x - 1, location.y);
				System.out.println("moved" + direction);
				break;

			case "West":
				location = new Point(location.x + 1, location.y);
				System.out.println("moved" + direction);
				break;

			default:
				throw new Error();
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