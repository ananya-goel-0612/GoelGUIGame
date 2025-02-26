import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Deck deck;
    private final ArrayList<Player> players;
    private int state;

    private String winner;

    public static final int START = 0;
    public static final int PLAYING = 1;
    public static final int WON = 2;
    public static final int TIE = 3;

    private static final int NUM_CARDS = 5;

    private final GameViewer window;

    private Card topCard;

    // Initializes the game
    public Game(String[] playerNames, String[] types, String[] colors) {
        this.state = START;
        // Window is initialized first and the welcome image is drawn
        this.window = new GameViewer(this);

        // Initialize deck
        deck = new Deck(types, colors, window);
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
    public void printStartingInstructions() {
        System.out.println("Welcome to UNO!");
        System.out.println("This is a two-player game, with a User and a Computer.");
        System.out.println("Players take turns matching the top card's color or number.");
        System.out.println("First to discard all cards wins!");
        System.out.println("Are you ready to begin? (answer y or n)");
    }

    // Prints out the instructions for each round
    public void printGameInstructions(Player currentPlayer) {
        // Print out whose turn it is
        System.out.println(currentPlayer.getName() + "'s turn.");
        // Print out the current player's hand
        System.out.println("Hand: " + currentPlayer.getHand());
        // Print out the top card
        System.out.println("Top card: " + topCard);
        // Have them draw a card or play a card
        System.out.println("Choose a card to play or draw a card (enter index or 'd'): ");
    }

    // If the player needs to draw a card, a card is drawn and added to their hand
    public void drawCard(Player currentPlayer) {
        // Draw a card
        Card drawnCard = deck.deal();
        // Print out the drawn card and add it to the player's hand
        // As long as the drawn card is not null
        if (drawnCard != null) {
            System.out.println(currentPlayer.getName() + " drew: " + drawnCard);
            currentPlayer.addCard(drawnCard);
        }
        // Otherwise print out a message saying that the deck is empty
        else {
            System.out.println("Deck is empty. Skipping draw.");
        }
    }

    // Proceeds if the user is ready to start the game, after they've read the instructions
    public boolean startGame() {
        printStartingInstructions();
        // Create scanner to get user input for their choice of cards
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        return answer.equalsIgnoreCase("y");
    }

    // Runs the game if the user decided to proceed
    public void initializeGame() {
        if (!startGame()) {
            return;
        }

        // Get the starting top card for the deck
        topCard = deck.deal();
        this.state = PLAYING;
        System.out.println("Starting card: " + topCard);

        runGame();
    }

    // Method where the game is actually run
    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        window.repaint();

        Player user = players.get(0);
        Player computer = players.get(1);

        while (true) {
            printGameInstructions(user);

            // Checks to make sure the deck isn't null or empty before proceeding
            if (deck.isEmpty()) {
                isWon("TIE");
                scanner.close();
                window.repaint();
                return;
            }

            // The user takes their turn and the top card is set to whatever card they played
            topCard = userTurn(user);
            // If the user only has one card left, UNO is printed out
            checkUno(user);
            window.repaint();

            // The computer takes its turn and the top card is set to the card it plays
            topCard = computerTurn(computer);
            // Prints UNO if the computer has one card left
            checkUno(computer);
            window.repaint();

            // If a player's hand is empty, then they won
            if (user.getHand().isEmpty()) {
                isWon(user);
                scanner.close();
                window.repaint();
                return;
            }
            else if (computer.getHand().isEmpty()) {
                isWon(computer);
                scanner.close();
                window.repaint();
                return;
            }
        }
    }

    // Prints out if the player has an UNO (one card left)
    public void checkUno(Player player) {
        if (player.getHand().size() == 1) {
            System.out.println(player.getName() + " says UNO!");
        }
    }

    // Method for the computer to take its turn
    public Card computerTurn(Player computer) {
        ArrayList<Card> hand = computer.getHand();
        // If any card in its hand is a match to the top card, that card is played
        for (int i = 0, n = hand.size(); i < n; i++) {
            if (isValidMove(hand.get(i))) {
                topCard = playCard(computer, i);
                return topCard;
            }
        }

        // If no match was found, the computer draws a card
        drawCard(computer);
        return topCard;
    }

    // Method for the user to take their turn
    public Card userTurn(Player user) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // If they choose to draw a card, draw a card and add it to their hand
        if (input.equalsIgnoreCase("d")) {
            drawCard(user);
        }
        // Otherwise check that their choice of card is valid/special card
        else {
            int index;
            // Makes sure the user inputs a valid input
            do {
                index = getValidCardIndex(input, user.getHand());
            }
            while (index == -1);

            // Once valid, the top card is set to the user's choice
            topCard = playCard(user, index);
        }
        return topCard;
    }

    // If a player won, prints out the winner and sets the state of the game equal to WON
    public void isWon(Player currentPlayer) {
        this.state = WON;
        winner = currentPlayer.getName();
        System.out.println(winner + " wins!");
    }

    // If the deck was empty, the game was a tie and that is printed out
    // The state of the game is set to TIE
    public void isWon(String tie) {
        this.state = TIE;
        System.out.println("Deck is empty. It's a tie.");
    }

    // Makes sure the user inputs a valid index for their card of choice
    // If it's not valid, it will return -1
    public int getValidCardIndex(String input, ArrayList<Card> hand) {
        // Found this documentation for converting from strings to integers
        // On Stack Overflow
        // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
        try {
            int index = Integer.parseInt(input);
            if (index >= 0 && index < hand.size()) {
                return index;
            }
            else {
                System.out.println("Index out of bounds. Valid range: 0 to " + (hand.size() - 1));
                return -1;
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
    public boolean isValidMove(Card playedCard) {
        return playedCard.getType().equals(topCard.getType()) ||
                playedCard.getColor().equals(topCard.getColor());
    }

    // Removes a card from the player's hand once they've played it
    // Returns the card that they played
    public Card playCard(Player currentPlayer, int index) {
        Card playedCard = currentPlayer.getHand().get(index);
        if (isValidMove(playedCard)) {
            topCard = currentPlayer.getHand().remove(index);
            System.out.println(currentPlayer.getName() + " played: " + topCard);
        }
        else {
            System.out.println("Invalid move. Skipping turn.");
        }
        return topCard;
    }

    public int getState() {
        return state;
    }

    public String getWinner() {
        return winner;
    }

    public ArrayList<Card> getUserHand() {
        return players.get(0).getHand();
    }

    public ArrayList<Card> getCompHand() {
        return players.get(1).getHand();
    }

    public Card getTopCard() {
        return topCard;
    }

    // Main function
    public static void main(String[] args) {
        // All the card values for UNO
        String[] types = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] colors = {"Red", "Green", "Blue", "Yellow"};

        String[] playerNames = {"User", "Computer"};
        Game unoGame = new Game(playerNames, types, colors);
        // Starts the game by printing out the instructions
        unoGame.initializeGame();
    }
}
