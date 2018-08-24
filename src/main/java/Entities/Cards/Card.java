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

	public String getCardName() {
		return cardName;
	}

	public String toString() {
		return getCardName();
	}
}