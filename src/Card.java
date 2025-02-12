import javax.swing.*;
import java.awt.*;

public class Card {

    // Type (which number it is)
    private String type;
    // Color
    private String color;
    // Index of card
    private int index;
    private Image image;

    public Card(String type, String color, int index) {
        this.type = type;
        this.color = color;
        this.index = index;
        // TODO: LOAD ALL IMAGES
        image = new ImageIcon("Resources/" + color + type).getImage();
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
}
