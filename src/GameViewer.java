import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000,
            WINDOW_HEIGHT = 800,
            BUFFER_X = (int)(WINDOW_WIDTH * 0.2),
            BUFFER_Y = (int)(WINDOW_HEIGHT * 0.15),
            SIDE_LENGTH = (int)(WINDOW_WIDTH * 0.19),
            LABEL_OFFSET = (int)(WINDOW_WIDTH * 0.05);
    private final Game game;

    private Image welcome;
    private Image user1won;
    private Image user2won;
    private Image tie;
    private Image cardBack;

    public GameViewer(Game game) {
        this.game = game;

        welcome = new ImageIcon("Resources/welcome.png").getImage();
        user1won = new ImageIcon("Resources/user1won.png").getImage();
        user2won = new ImageIcon("Resources/user2won.png").getImage();
        tie = new ImageIcon("Resources/tie.png").getImage();
        // TODO
        cardBack = new ImageIcon("Resources/back.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ananya's Simplified Uno!");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        int state = game.getState();

        switch (state) {
            case Game.START:
                g.drawImage(welcome, 0, 0, this);
                break;
            case Game.WON:
                if (game.getWinner().equals("User")) {
                    g.drawImage(user1won, 0, 0, this);
                }
                else if (game.getWinner().equals("Computer")) {
                    g.drawImage(user2won, 0, 0, this);
                }
                else {
                    g.drawImage(tie, 0, 0, this);
                }
                break;
            case Game.PLAYING:
                // TODO
                int yPosition = WINDOW_HEIGHT / 2 - Card.CARD_HEIGHT / 2;
                int xPosition = WINDOW_WIDTH / 2 - 2 * Card.CARD_WIDTH;
                g.drawImage(cardBack, xPosition, yPosition, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);

                game.getTopCard().draw(g, WINDOW_WIDTH / 2 + Card.CARD_WIDTH, yPosition);

                ArrayList<Card> userHand = game.getUserHand();
                ArrayList<Card> compHand = game.getCompHand();

                for (int i = 0; i < userHand.size(); i++) {
                    int spacing = BUFFER_X + i * ((WINDOW_WIDTH - 2 * BUFFER_X) / userHand.size());
                    userHand.get(i).draw(g, spacing, WINDOW_HEIGHT - BUFFER_Y - Card.CARD_HEIGHT);

                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(i), spacing + Card.CARD_WIDTH / 2,
                            WINDOW_HEIGHT - BUFFER_Y + LABEL_OFFSET);
                }

                for (int i = 0; i < compHand.size(); i++) {
                    int spacing = BUFFER_X + i * ((WINDOW_WIDTH - 2 * BUFFER_X) / compHand.size());
                    g.drawImage(cardBack, spacing, BUFFER_Y, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);
                }

                break;
        }
    }
}
