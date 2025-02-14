import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;
    private static final int TOOLBAR_HEIGHT = 23;
    private final Game game;

    private Image welcome;
    private Image user1won;
    private Image user2won;
    private Image tie;

    public GameViewer(Game game) {
        this.game = game;

        welcome = new ImageIcon("Resources/welcome.png").getImage();
        user1won = new ImageIcon("Resources/user1won.png").getImage();
        user2won = new ImageIcon("Resources/user2won.png").getImage();
        tie = new ImageIcon("Resources/tie.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Guess The Number!");
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
                // TODO
                if (game.getWinner().equals("User 1")) {
                    g.drawImage(user1won, 0, 0, this);
                }
                else if (game.getWinner().equals("User 2")) {
                    g.drawImage(user2won, 0, 0, this);
                }
                else {
                    g.drawImage(tie, 0, 0, this);
                }
            case Game.PLAYING:
                // TODO
                g.setColor(Color.black);
                g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
                break;
        }
    }
}
