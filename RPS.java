// Abid Asif
// Rock Paper Scissors
// 12//03/25
import java.util.Scanner;
import java.util.Random;

// Gets the Arcade class
public class RPS extends Arcade {

// sets the Game Name
    public RPS() {
        super("Rock Paper Scissors"); 
    }
// Starts the Game
    @Override
    public void playGame() {
        Scanner input = new Scanner(System.in);
        Random random = new Random(); // This gets random for the "AI"

        System.out.println("Welcome to Rock Paper Scissors");
        System.out.println("Cost to play: 1 coins per match.");

        if (coins < 1) {
            System.out.println("Not enough coins to play!");
            return;
        }

        // Starts the game
        boolean keepPlaying = true;
        while (keepPlaying && coins >= 1) {
            setCoins(1);
            System.out.println("Game is Starting Coins left: " + coins);
            System.out.print("Enter your choice rock, paper, scissors or 'quit' to exit: ");
            String playerChoice = input.nextLine().toLowerCase(); // Gets the players input and makes it lowercase
            // Quits the game
            if (playerChoice.equals("quit")) {
                keepPlaying = false;
                continue;
            }
            // Error Handling
            if (!playerChoice.equals("rock") && !playerChoice.equals("paper") && !playerChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            // Gets a "AI" input
            String[] choices = {"rock", "paper", "scissors"};
            String computerChoice = choices[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);
            // Logic behind the game
            if (playerChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if ((playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
                       (playerChoice.equals("paper") && computerChoice.equals("rock")) ||
                       (playerChoice.equals("scissors") && computerChoice.equals("paper"))) {
                System.out.println("You win this round!");
                setWins(1);
            } else {
                System.out.println("You lose this round!");
                setLosses(1);
            }
            // Check to see if the player can continue playing
            if (coins < 1) {
                System.out.println("Not enough coins to continue playing!");
                break;
            }
            // If they want to play again
            System.out.print("Do you want to play again? (yes/no): ");
            String again = input.nextLine().toLowerCase();
            if (!again.equals("yes")) {
                keepPlaying = false;
            }
        }
    }
}