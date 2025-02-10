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

    public Card(String type, String color, int index) {
        this.type = type;
        this.color = color;
        this.index = index;
        // TODO: LOAD ALL IMAGES
        image = new ImageIcon("Resources/______").getImage();
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

    // This is where each of the cards gets printed out so that the
    // User can choose what card to play
    public String toString() {
        return color + " " + type;
    }
}
