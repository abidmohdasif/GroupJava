import java.util.Scanner;
import java.util.Random;

// Extends the provided Arcade class
public class titato extends Arcade {

    // Sets the Game Name via the super constructor
    public titato() {
        super("Tic Tac Toe");
    }

    // Starts the Game
    @Override
    public void playGame() {
        Scanner input = new Scanner(System.in);
        Random random = new Random(); // Used for the AI moves

        System.out.println("\nWelcome to " + getName());
        System.out.println("Cost to play: 1 coin per match.");
        System.out.println("Current Coins: " + getCoins());

        // Check if player can afford to start
        if (getCoins() < 1) {
            System.out.println("Not enough coins to play!");
            return;
        }

        boolean keepPlaying = true;
        
        while (keepPlaying && getCoins() >= 1) {
            setCoins(1); // Deduct the cost (1 coin)
            System.out.println("Game is Starting. Coins left: " + getCoins());

            // Initialize the board (3x3 grid)
            char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
            };

            boolean gameRunning = true;
            boolean playerTurn = true; // Player starts first

            while (gameRunning) {
                printBoard(board);

                if (playerTurn) {
                    // --- Player Logic ---
                    System.out.print("Enter row (1-3) and column (1-3) e.g. '1 1': ");
                    int row = -1;
                    int col = -1;

                    // Input validation
                    if (input.hasNextInt()) {
                        row = input.nextInt() - 1; // Adjust to 0-index
                        col = input.nextInt() - 1;
                        input.nextLine(); // Clear buffer
                    } else {
                        input.nextLine(); // Clear bad input
                        System.out.println("Invalid input. Use numbers.");
                        continue;
                    }

                    // Move validation
                    if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
                        System.out.println("Invalid move, try again.");
                        continue;
                    }

                    board[row][col] = 'X';

                    // Check Player Win
                    if (checkWinner(board, 'X')) {
                        printBoard(board);
                        System.out.println("You win this round!");
                        setWins(1);
                        gameRunning = false;
                    } else if (isBoardFull(board)) {
                        printBoard(board);
                        System.out.println("It's a tie!");
                        gameRunning = false;
                    } else {
                        playerTurn = false; // Switch to AI
                    }

                } else {
                    // --- AI Logic ---
                    System.out.println("Computer is thinking...");
                    int aiRow, aiCol;
                    // Simple random AI
                    do {
                        aiRow = random.nextInt(3);
                        aiCol = random.nextInt(3);
                    } while (board[aiRow][aiCol] != ' ');

                    board[aiRow][aiCol] = 'O';
                    System.out.println("Computer chose: " + (aiRow + 1) + " " + (aiCol + 1));

                    // Check AI Win
                    if (checkWinner(board, 'O')) {
                        printBoard(board);
                        System.out.println("You lose this round!");
                        setLosses(1);
                        gameRunning = false;
                    } else if (isBoardFull(board)) {
                        printBoard(board);
                        System.out.println("It's a tie!");
                        gameRunning = false;
                    } else {
                        playerTurn = true; // Switch to Player
                    }
                }
            }

            // End of match status
            displayStats();

            // Check coins before offering replay
            if (getCoins() < 1) {
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

    // Helper method to display the grid
    private void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Helper method to check win conditions
    private boolean checkWinner(char[][] board, char symbol) {
        // Rows & Cols
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        // Diagonals
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    // Helper to check for ties
    private boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}