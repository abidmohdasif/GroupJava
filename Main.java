import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("\n===== Arcade Main Menu =====");
            System.out.println("1. Rock Paper Scissors");
            System.out.println("2. Tic Tac Toe ");
            System.out.println("3. Coin Flip ");
            System.out.println("4. Blackjack ");
            System.out.println("5. Exit Arcade");
            System.out.println("Choose a game (1-5): ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    RPS rps = new RPS();
                    rps.playGame();
                    rps.displayStats();
                    break;

                case 2:
                    
                    break;

                case 3:
                    
                    break;

                case 4:
                   
                    break;

                case 5:
                    System.out.println("\nThank you for playing the Arcade!");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }

        input.close();
    }
}