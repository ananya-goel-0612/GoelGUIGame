import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;
    private static final int TOOLBAR_HEIGHT = 23;
    private final Game game;

    private Image[] cardImages;
    private Image welcome;

    public GameViewer(Game game) {
        this.game = game;

        cardImages = new Image[6];
        welcome = new ImageIcon("Resources/welcome.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Guess The Number!");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        //g.drawImage(welcome, 0, TOOLBAR_HEIGHT, this);
        // TODO: FINISH
        //if (game.getPlayer() == null) {
            g.drawImage(welcome, 0, TOOLBAR_HEIGHT, this);
       // }
    }
}
