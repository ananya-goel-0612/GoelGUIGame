import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private int state;

    private String winner;

    public static final int START = 0;
    public static final int PLAYING = 1;
    public static final int WON = 2;

    private final int NUM_CARDS = 5;

    private GameViewer window;

    public Game(String[] playerNames, String[] types, String[] colors, int[] indices) {
        this.state = START;
        this.window = new GameViewer(this);

        // Initialize deck
        deck = new Deck(types, colors, indices, window);
        // Get the players
        players = new ArrayList<Player>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        // Deal initial cards (NUM_CARDS cards per player for uno)
        for (Player player : players) {
            for (int i = 0; i < NUM_CARDS; i++) {
                player.addCard(deck.deal());
            }
        }
    }

    // Prints out the game instructions when the game is started
    public void printInstructions() {
        System.out.println("Welcome to UNO!");
        System.out.println("This is a two-player game, with a User 1 and a User 2");
        System.out.println("Players take turns matching the top card's color or number.");
        System.out.println("First to discard all cards wins!");
        System.out.println("Are you ready to begin? (answer y or n)");
    }

    public void playGame() {
        printInstructions();
        // Create scanner to get user input for their choice
        // Of cards
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("y")) {
            return;
        }

        this.state = PLAYING;
        window.repaint();

        // Get the starting top card for the deck
        Card topCard = deck.deal();
        System.out.println("Starting card: " + topCard);
        int currentPlayerIndex = 0;

        // Basically the game loop runs until a player's hand is empty
        // Or if all the cards have been used
        // Which is when there will be a return statement
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);

            // Print out whose turn it is
            System.out.println(currentPlayer.getName() + "'s turn.");
            // Print out the current player's hand
            System.out.println("Your hand: " + currentPlayer.getHand());
            // Print out the top card
            System.out.println("Top card: " + topCard);
            // Have them draw a card or play a card
            System.out.println("Choose a card to play or draw a card (enter index or 'd'): ");
            String input = scanner.nextLine();

            // Do the game stuff here
            // If they choose to draw a card, draw a card and add it to their hand
            if (input.equalsIgnoreCase("d")) {
                // Draw a card
                Card drawnCard = deck.deal();
                // Print out the drawn card and add it to the player's hand
                // As long as the drawn card is not null
                if (drawnCard != null) {
                    System.out.println("You drew: " + drawnCard);
                    currentPlayer.addCard(drawnCard);
                }
                // Otherwise print out a message saying that the deck is empty
                else {
                    System.out.println("Deck is empty. Skipping draw.");
                }
            }
            // Otherwise check that their choice of card is valid/special card
            else {
                int index = getValidCardIndex(input, currentPlayer.getHand());
                // Check that the input is a valid number
                if (index == -1) {
                    System.out.println("Invalid input. Skipping turn.");
                }
                else {
                    Card playedCard = currentPlayer.getHand().get(index);
                    if (isValidMove(playedCard, topCard)) {
                        // Remove the played card from their hand
                        // Set the top card equal to the played card
                        topCard = playCard(currentPlayer, index);
                        // Tell the user what card they played
                    }
                    // If it reaches here, the move was invalid
                    else {
                        System.out.println("Invalid move. Skipping turn.");
                    }
                }
            }

            // Check for win condition
            // If someone's hand is empty, then they won
            if (currentPlayer.getHand().isEmpty()) {
                this.state = WON;
                winner = currentPlayer.getName();
                System.out.println(currentPlayer.getName() + " wins!");
                scanner.close();
                return;
            }

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }


    public int getValidCardIndex(String input, ArrayList<Card> hand) {
        // Found this documentation for converting from strings to ints
        // On Stack Overflow
        // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
        try {
            int index = Integer.parseInt(input);
            if (index >= 0 && index < hand.size()) {
                return index;
            }
            else {
                System.out.println("Index out of bounds. Valid range: 0 to " + (hand.size() - 1));
            }
        }
        catch (NumberFormatException e) {
            // This means that the input is not a valid number
            System.out.println("Invalid input. Please enter a number.");
        }
        // Return -1 if input is invalid
        return -1;
    }

    // Checks if the numbers or colors of the top card and played card match
    // Returns true or false
    public boolean isValidMove(Card playedCard, Card topCard) {
        return playedCard.getType().equals(topCard.getType()) ||
                playedCard.getColor().equals(topCard.getColor());
    }

    // Removes a card from the player's hand once they've played it
    // Prints out which card they played
    // Returns the card that they played
    public Card playCard(Player player, int index) {
        Card playedCard = player.getHand().remove(index);
        System.out.println("You played: " + playedCard);
        return playedCard;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getWinner() {
        return winner;
    }

    // Main function
    public static void main(String[] args) {
        // All the card values for uno
        String[] types = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        int[] indices = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        String[] playerNames = {"User 1", "User 2"};
        Game unoGame = new Game(playerNames, types, colors, indices);
        unoGame.playGame();
    }
}
