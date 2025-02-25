import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> cards;
    private int cardsLeft;

    // Initializes the deck of cards
    public Deck(String[] types, String[] colors, GameViewer window) {
        // Initialize cards
        this.cards = new ArrayList<Card>();
        for (String color : colors) {
            for (int i = 0; i < types.length; i++) {
                // Add a new card with different ranks and values to each suit
                cards.add(new Card(types[i], color, window));
            }
        }
        this.cardsLeft = cards.size();
        // Shuffle deck after you add everything to cards
        shuffle();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // Deals a card from the deck
    public Card deal() {
        // Returns null if there's no cards left
        if (isEmpty()) {
            return null;
        }
        return cards.get(--cardsLeft);
    }

    // Shuffles the deck of cards
    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            // Generates a random integer r (using Math.random) between 0 and i, inclusive
            int r = (int) (Math.random() * (i + 1));
            // Exchanges cards[i] and cards[r]
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
    }
}