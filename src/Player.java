public class Player
{
    // Instance variables
    private String playerName;
    private double score;

    // Constructors
    public Player(String name, int turns)
    {
        this.playerName = name;
        this.score = 0;
    }


    public Player(String name)
    {
        this.playerName = name;
        this.score = 0;
    }

    public Player()
    {
        this.playerName = "Guest";
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