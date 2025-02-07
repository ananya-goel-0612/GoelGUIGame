public class Player
{
    // Instance variables
    private String playerName;
    private double score;

    // Constructor
    public Player(String name)
    {
        this.playerName = name;
        this.score = 0;
    }

    // Methods
    public String toString()
    {
        return (playerName + "'s score is " + score + "!");
    }

    public void updateScore()
    {
        score += 1;
    }
}