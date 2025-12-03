// Abid Asif
// Rock Paper Scissors
// 12//03/25
import java.util.Scanner;
import java.util.Random;

public class RPS extends Arcade {

    public RPS() {
        super("Rock Paper Scissors");  // sets gameName from parent
    }

    @Override
    public void playGame() {
        try (Scanner input = new Scanner(System.in)) {
            Random random = new Random();

            System.out.println("\n--- Welcome to Rock Paper Scissors ---");
            System.out.println("Cost to play: 1 coins per match.");

        if (coins < 1) {
            System.out.println("Not enough coins to play!");
            return;
        }

        // Deduct the cost to play
        setCoins(1); // subtracts 1 from coins
        System.out.println("Starting game... Coins left: " + coins);

        boolean keepPlaying = true;
        while (keepPlaying && coins >= 5) {
            System.out.print("Enter your choice (rock, paper, scissors) or 'quit' to exit: ");
            String playerChoice = input.nextLine().toLowerCase();

            if (playerChoice.equals("quit")) {
                keepPlaying = false;
                continue;
            }

            if (!playerChoice.equals("rock") && !playerChoice.equals("paper") && !playerChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            String[] choices = {"rock", "paper", "scissors"};
            String computerChoice = choices[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);

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

            if (coins < 5) {
                System.out.println("Not enough coins to continue playing!");
                break;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String again = input.nextLine().toLowerCase();
            if (!again.equals("yes")) {
                keepPlaying = false;
                }
            }
        }
    }
}