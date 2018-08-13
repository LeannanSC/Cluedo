package Entities.Cards;

public abstract class Card {
    //Entities.Cards.Card Attributes
    private String cardName;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public boolean setCardName(String cardName) {
        boolean wasSet = false;
        this.cardName = cardName;
        wasSet = true;
        return wasSet;
    }

    public String getCardName() {
        return cardName;
    }

    public String toString() {
        return super.toString() + "[" +
                "cardName" + ":" + getCardName() + "]";
    }
}