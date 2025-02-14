import javax.swing.*;
import java.awt.*;

public class Card {

    // Rank = type (number, skip, draw)
    private String type;
    // Suit = color
    private String color;
    // Value = index of card
    private int index;
    private Image image;
    private GameViewer window;
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = (int)(100*1.7106038292);

    public Card(String type, String color, int index, GameViewer window) {
        this.type = type;
        this.color = color;
        this.index = index;
        this.window = window;
        // TODO: LOAD ALL IMAGES
        image = new ImageIcon("Resources/Cards/" + color + type).getImage();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // This is where each of the cards gets printed out so that the
    // User can choose what card to play
    public String toString() {
        return color + " " + type;
    }

    public void draw(Graphics g, int xTop, int yTop) {
        g.drawImage(image, xTop,yTop, CARD_WIDTH,CARD_HEIGHT, window);
    }
}
