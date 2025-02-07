public class Player
{
    // Instance variables
    private String playerName;
    private int turns;
    private double score;

    // Constructor
    public Player(String name)
    {
        this.playerName = name;
        this.score = 0;
        this.turns = 0;
    }

    public int getTurns() {
        return turns;
    }

    // Methods
    public String toString()
    {
        return (playerName + "'s score is " + score + "!");
    }

    public void updateScore()
    {
        this.score += 1;
    }

    public void updateTurns() {
        this.turns += 1;
    }
}