import java.awt.*;

public class Die
{
    /** Instance Variables **/

    private int numSides;

    /** Constructors **/

    public Die() {
        numSides = 6;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        int roll = (int)(Math.random() * numSides + 1);
        return roll;
    }

    /**
     * Returns a String in the following form:
     * "This is a n-sided die."
     */
    public String toString() {
        return ("This is a " + numSides + "-sided die.");
    }

    // checks if the guess is correct
    public boolean checkGuess(int guess, int roll) {
        if (guess == roll) {
            return true;
        }
        return false;
    }

//    public void draw(Graphics g) {
//        // TODO
//    }

}