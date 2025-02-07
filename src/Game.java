import java.util.Scanner;

public class Game
{
    private Player p1;
    private Die dice;
    private GameViewer window;
    private boolean isGameOver;

    public Game() {
        this.window = new GameViewer(this);
        p1 = getPlayer();
        dice = new Die();
        isGameOver = false;
    }

    public void printInstructions()
    {
        // prints all the instructions at the beginning
        System.out.println("Welcome to Guess-That-Number!");
        System.out.println("The game is pretty simple. When you roll the dice," +
                "you have to guess the number it rolls.");
        System.out.println("If you're correct, you'll get a point!");
        System.out.println("You can choose to play again or quit after each roll.");
        System.out.println("Enter your name in the console to begin!");
    }

    public void playRounds()
    {
        Scanner check = new Scanner(System.in);
        // has the user roll the die and then guess the number they rolled
        for (int i = 0; i < 5; i++)
        {
            System.out.println("Rolling the die!");
            int roll = dice.roll();
            System.out.println("What do you think it rolled? ");
            int guess = check.nextInt();
            // tells the user if they're right or wrong
            if (!dice.checkGuess(guess, roll))
            {
                System.out.println("Whoops, that's not correct. The correct " +
                        "answer was " + roll);
            }
            else
            {
                System.out.println("Correct!");
                p1.updateScore();
            }

            // Adds a space
            System.out.println();
        }
    }

    public Player getPlayer()
    {
        // gets the player's name
        Scanner player = new Scanner(System.in);
        System.out.println("What is your name? ");
        String name = player.nextLine();

        // Initializes the new player
        return new Player(name);
    }

    // the main code to run the game
    public void playGame()
    {
        printInstructions();
        getPlayer();
        playRounds();
        System.out.println(p1.toString());
    }

    // main function that calls the game
    public static void main(String[] args)
    {
        Game game1 = new Game();
        game1.playGame();
    }
}