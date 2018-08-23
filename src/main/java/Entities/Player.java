package Entities;

import Entities.Cards.*;
import Entities.Commands.Action;

import java.awt.*;
import java.util.ArrayList;
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

	private boolean isOut = false;

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

	public void move(Action direction) {
		movesRemaining--;
		switch (direction) {

			case NORTH:
				location = new Point(location.x, location.y - 1);
				break;

			case SOUTH:
				location = new Point(location.x, location.y + 1);
				break;

			case EAST:
				location = new Point(location.x + 1, location.y);
				break;

			case WEST:
				location = new Point(location.x - 1, location.y);
				break;

			default:
				throw new Error();
		}
	}

	// -------------------
	// GETTERS AND SETTERS
	// -------------------

	public Point getLocation() {
		return location;
	}

	public String getCharacterName() {
		return characterName;
	}

	public String getColour() {
		return colour;
	}

	public List<Card> getCardsInHand() {
		return cardsInHand;
	}

	public int getMovesRemaining() {
		return movesRemaining;
	}

	public void setMovesRemaining(int moves) {
		movesRemaining = moves;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean out) {
		isOut = out;
	}
}