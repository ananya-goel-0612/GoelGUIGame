import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card> hand;

    // Initializes each player
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    // If the player needs to draw a card, a new card is added to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }
}
