import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000,
            WINDOW_HEIGHT = 800,
            BUFFER_X = (int)(WINDOW_WIDTH * 0.2),
            BUFFER_Y = (int)(WINDOW_HEIGHT * 0.15),
            LABEL_OFFSET = (int)(WINDOW_WIDTH * 0.05); // Credit to Isha Gupta for the values
    private final Game game;

    private final Image welcome;
    private final Image userWon;
    private final Image computerWon;
    private final Image tie;
    private final Image cardBack;
    private final Image table;

    // Initializes the front-end
    public GameViewer(Game game) {
        // Back-end is passed into the front-end as an instance variable
        this.game = game;

        // All the images get loaded in
        welcome = new ImageIcon("Resources/welcome.png").getImage();
        userWon = new ImageIcon("Resources/userWon.png").getImage();
        computerWon = new ImageIcon("Resources/computerWon.png").getImage();
        tie = new ImageIcon("Resources/tie.png").getImage();
        cardBack = new ImageIcon("Resources/back.png").getImage();
        table = new ImageIcon("Resources/table.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ananya's Simplified Uno!");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        int state = game.getState();

        switch (state) {
            case Game.START:
                // Draws the welcome image that has the instructions for the game on it
                g.drawImage(welcome, 0, 0, this);
                break;
            case Game.WON:
                // Displays which player won once the game state has been set to WON
                if (game.getWinner() == null) {
                    g.drawImage(tie, 0, 0, this);
                }
                else if (game.getWinner().equals("User")) {
                    g.drawImage(userWon, 0, 0, this);
                }
                else if (game.getWinner().equals("Computer")) {
                    g.drawImage(computerWon, 0, 0, this);
                }
                break;
            case Game.PLAYING:
                // Draws the background first so that all the cards get drawn on top
                g.drawImage(table, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

                int yPosition = WINDOW_HEIGHT / 2 - Card.CARD_HEIGHT / 2;
                int xPosition = WINDOW_WIDTH / 2 - 2 * Card.CARD_WIDTH;
                // Draws the deck that's face down
                g.drawImage(cardBack, xPosition, yPosition, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);

                // Draws the top card (the card that's in play)
                game.getTopCard().draw(g, WINDOW_WIDTH / 2 + Card.CARD_WIDTH, yPosition);

                ArrayList<Card> userHand = game.getUserHand();
                ArrayList<Card> compHand = game.getCompHand();

                for (int i = 0; i < userHand.size(); i++) {
                    // Dynamic spacing for the cards, depending on how many cards there are
                    // Credit to Isha Gupta for the formulas
                    int spacing = BUFFER_X + i * ((WINDOW_WIDTH - 2 * BUFFER_X) / userHand.size());
                    // Draws each card of the user's hand
                    userHand.get(i).draw(g, spacing, WINDOW_HEIGHT - BUFFER_Y - Card.CARD_HEIGHT);

                    // Also draws the index of each card for the user's convenience
                    g.setColor(Color.WHITE);
                    g.drawString(Integer.toString(i), spacing + Card.CARD_WIDTH / 2,
                            WINDOW_HEIGHT - BUFFER_Y + LABEL_OFFSET);
                }

                // Draws all the computer's cards face down
                for (int i = 0; i < compHand.size(); i++) {
                    int spacing = BUFFER_X + i * ((WINDOW_WIDTH - 2 * BUFFER_X) / compHand.size());
                    g.drawImage(cardBack, spacing, BUFFER_Y, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);
                }
                break;
        }
    }
}
