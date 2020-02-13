/*

 */
package blackJack;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mark
 */
public class CardDeck {

    private ArrayList<PlayingCard> cards;

    public CardDeck() {
        this.cards = new ArrayList<PlayingCard>();
    }

    public void generateDeck() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                this.cards.add(new PlayingCard(suit, value));
            }
        }
    }

    public void shuffle() {
        ArrayList<PlayingCard> deckShuffle = new ArrayList<PlayingCard>();
        Random random = new Random();
        int randomCardStart = 0;
        int originalSize = this.cards.size();
        for (int x = 0; x < originalSize; x++) {
            randomCardStart = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
            deckShuffle.add(this.cards.get(randomCardStart));
            this.cards.remove(randomCardStart);
        }
        this.cards = deckShuffle;
    }

    @Override
    public String toString() {
        String cardListOutput = "";

        for (PlayingCard oneCard : this.cards) {
            cardListOutput += "\n" + " " + oneCard.toString();

        }
        return cardListOutput;
    }

    public void moveAllToDeck(CardDeck moveTo) {
        int thisDeckSize = this.cards.size();
        //put cards in moveTo deck
        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }
        //empty out the deck
        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }

    public void removeCard(int x) {
        this.cards.remove(x);
    }

    public PlayingCard getCard(int x) {
        return this.cards.get(x);
    }

    public void addCard(PlayingCard addCard) {
        this.cards.add(addCard);
    }

    public void draw(CardDeck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int deckSize() {
        return this.cards.size();
    }

    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;
        for (PlayingCard aCard : this.cards) {
            switch (aCard.getValue()) {
                case ACE:
                    totalValue += 1;
                    break;
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;

            }
        }
        for (int x = 0; x < aces; x++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}
