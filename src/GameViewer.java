import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;
    private static final int TOOLBAR_HEIGHT = 23;
    private final Game game;

    private Image[] diceImages;
    private Image closedBox;
    private Image openBox;
    private Image welcome;

    public GameViewer(Game game) {
        this.game = game;

        diceImages = new Image[6];
        diceImages[0] = new ImageIcon("Resources/1.png").getImage();
        diceImages[1] = new ImageIcon("Resources/2.png").getImage();
        diceImages[2] = new ImageIcon("Resources/3.png").getImage();
        diceImages[3] = new ImageIcon("Resources/4.png").getImage();
        diceImages[4] = new ImageIcon("Resources/5.png").getImage();
        diceImages[5] = new ImageIcon("Resources/6.png").getImage();

        closedBox = new ImageIcon("Resources/box.png").getImage();
        openBox = new ImageIcon("Resources/openBox.png").getImage();
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
