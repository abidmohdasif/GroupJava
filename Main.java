import java.util.Scanner;

public class Main {
    /* Clear Method
     * Clears the console screen
     */
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /* Load Method
     * Takes in time arguement and message arguement
     * Recreates a loading screen with a load symbol and a bonus message for different loading screens
     */
    public static void load(int time, String message){
        //Create loading symbol
        String[] load = new String[4];
        load[0] = "\\";
        load[1] = "|";
        load[2] = "/";
        load[3] = "--";

        //Clear screen
        clear();

        //For the length of given time, run loading screen
        for (int i = 0; i < time; i++){
            //Output every frame of loading symbol with message
            for (String j : load){
                try {
                    System.out.print(j + " Loading...\n");
                    System.out.println(message);
                    Thread.sleep(100);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clear();
            }
        }
    }

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
            System.out.print("Choose a game (1-5): ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    RPS rps = new RPS();
                    rps.playGame();
                    rps.displayStats();
                    break;

                case 2:
                    Coin coin = new Coin();
                    coin.playGame();
                    coin.displayStats();
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