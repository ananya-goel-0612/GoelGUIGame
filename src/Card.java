import javax.swing.*;
import java.awt.*;

public class Card {

    // Rank = type (number, skip, draw)
    private final String type;
    // Suit = color
    private final String color;

    private final Image image;
    private final GameViewer window;
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = 171;

    public Card(String type, String color, GameViewer window) {
        this.type = type;
        this.color = color;
        this.window = window;
        image = new ImageIcon("Resources/Cards/" + color + type + ".png").getImage();
    }


    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    // This is where each of the cards gets printed out so that the
    // User can choose what card to play
    public String toString() {
        return color + " " + type;
    }

    public void draw(Graphics g, int xTop, int yTop) {
        g.drawImage(this.image, xTop,yTop, CARD_WIDTH,CARD_HEIGHT, window);
    }
}
