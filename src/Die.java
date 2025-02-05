public class Die
{
    /** Instance Variables **/

    private int numSides;

    /** Constructors **/

    public Die(int numSides) {
        // TODO: Complete this constructor
        // NOTE: if the user enters an int less than 2
        // set numSides to 6.
        if (numSides < 2)
        {
            this.numSides = 6;
        }
        else
        {
            this.numSides = numSides;
        }
    }

    public Die() {
        numSides = 6;
    }

    /** Methods **/

    /**
     * Returns the number of sides on the Die.
     */
    public int getSides() {
        return numSides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        // TODO: complete roll()
        int roll = (int)(Math.random() * numSides + 1);
        return roll;
    }

    /**
     * Rolls the dice the numRolls times
     * and returns the max value of the rolls
     */
    public int getMaxRoll(int numRolls) {
        // TODO: complete getMaxRoll()
        int currentRoll;
        int maxRoll = 0;
        for (int i = 0; i < numRolls; i++)
        {
            currentRoll = roll();

            if (currentRoll > maxRoll)
            {
                maxRoll = currentRoll;
            }
        }
        return maxRoll;
    }

    /**
     * Returns a String in the following form:
     * "This is a n-sided die."
     */
    public String toString() {
        // TODO: complete toString()
        return ("This is a " + numSides + "-sided die.");
    }

    // checks if the guess is correct
    public boolean checkGuess(int guess, int roll) {
        if (guess == roll) {
            return true;
        }
        return false;
    }

}