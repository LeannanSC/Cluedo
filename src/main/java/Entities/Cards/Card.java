package Entities.Cards;

/**
 * Abstract class for all the card types
 */
public abstract class Card {

	//Card Attributes
	private String cardName;

	/**
	 * Constructor
	 *
	 * @param cardName The name of the card
	 */
	public Card(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * Set the card name
	 *
	 * @param cardName the new name of the card
	 * @return if the card was properly set
	 */
	public boolean setCardName(String cardName) {
		// FIXME: Unused method
		boolean wasSet = false;
		this.cardName = cardName;
		wasSet = true;
		return wasSet;
	}

	public String getCardName() {
		return cardName;
	}

	public String toString() {
		return getCardName();
	}
}