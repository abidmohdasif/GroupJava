// 12/4/25 
// Jack Daubman 
// Blackjack game for the arcade 
// Win is +10 coins Loss is -5 coins 
// Users play the game by inputting 4 in the Main and play hands until they wish to cashout by pressing "n" after a hand

import java.util.*;

public class Blackjack extends Arcade {
    // Data members specific to Blackjack
    protected ArrayList<Card> playerHand;
    protected ArrayList<Card> dealerHand;
    protected ArrayList<Card> deck;
    
    // Inner Card class to represent playing cards
    private class Card {
        private String suit;
        private String rank;
        private int value;
        
        public Card(String suit, String rank, int value) {
            this.suit = suit;
            this.rank = rank;
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
        
        public String toString() {
            return rank + " of " + suit;
        }
    }
    
    // Constructor
    public Blackjack() {
        super("Blackjack");
        this.playerHand = new ArrayList<>();
        this.dealerHand = new ArrayList<>();
        this.deck = new ArrayList<>();
    }
    
    // Method to create and shuffle deck
    private void createDeck() {
        deck.clear();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        
        // Create deck with all cards
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                deck.add(new Card(suit, ranks[i], values[i]));
            }
        }
        
        // Shuffle deck
        Collections.shuffle(deck);
    }
    
    // Method to deal a card
    private Card dealCard() {
        if (deck.isEmpty()) {
            createDeck();
        }
        return deck.remove(0);
    }
    
    // Method to calculate hand value
    private int calculateHandValue(ArrayList<Card> hand) {
        int total = 0;
        int aceCount = 0;
        
        // Sum up all card values and count aces
        for (Card card : hand) {
            total += card.getValue();
            if (card.getValue() == 11) {
                aceCount++;
            }
        }
        
        // Adjust for aces if total is over 21
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        
        return total;
    }
    
    // Method to display a hand
    private void displayHand(ArrayList<Card> hand, String owner, boolean hideFirst) {
        System.out.print(owner + "'s hand: ");
        for (int i = 0; i < hand.size(); i++) {
            if (i == 0 && hideFirst) {
                System.out.print("[Hidden]");
            } else {
                System.out.print("[" + hand.get(i) + "]");
            }
            if (i < hand.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    // Method to show the rules
    public void getRules() {
        System.out.println("\nBlackjack Rules:");
        System.out.println("1. Get as close to 21 as possible without going over");
        System.out.println("2. Face cards (Jack, Queen, King) are worth 10");
        System.out.println("3. Aces are worth 11 or 1 (adjusted automatcially)");
        System.out.println("4. 'Hit' to draw a card, 'Stand' to keep your hand");
        System.out.println("5. Dealer must hit until 17 or higher");
        System.out.println("6. Win: +10 coins | Loss: -5 coins");
    }
    
    // Override playGame method from Arcade
    @Override
    public void playGame() {
        Scanner input = new Scanner(System.in);
        Main.clear();
        
        // Display rules
        getRules();
        
        // Welcome message
        System.out.println("\n----- Welcome to Blackjack -----");
        System.out.println("Current coins: " + this.coins);
        
        boolean playAgain = true;
        
        while (playAgain) {
            // Initialize new game
            createDeck();
            playerHand.clear();
            dealerHand.clear();
            
            // Deal initial cards
            Main.load(2, "Dealing cards...");
            playerHand.add(dealCard());
            dealerHand.add(dealCard());
            playerHand.add(dealCard());
            dealerHand.add(dealCard());
            
            // Display initial hands
            System.out.println("\n--- Cards Dealt ---");
            displayHand(dealerHand, "Dealer", true);
            displayHand(playerHand, "Player", false);
            System.out.println("Your total: " + calculateHandValue(playerHand));
            
            int playerScore = calculateHandValue(playerHand);
            
            // Player's turn
            boolean playerTurn = true;
            while (playerTurn && playerScore < 21) {
                System.out.print("\n(h)it or (s)tand? ");
                String choice = input.next().toLowerCase();
                
                if (choice.equals("h")) {
                    Card newCard = dealCard();
                    playerHand.add(newCard);
                    playerScore = calculateHandValue(playerHand);
                    
                    System.out.println("You drew: [" + newCard + "]");
                    displayHand(playerHand, "Player", false);
                    System.out.println("Your total: " + playerScore);
                    
                    if (playerScore > 21) {
                        System.out.println("\nBUST! You lose 5 coins.");
                        this.losses++;
                        setCoins(5);
                        playerTurn = false;
                    }
                } else if (choice.equals("s")) {
                    playerTurn = false;
                } else {
                    System.out.println("Invalid input. Enter 'h' or 's'.");
                }
            }
            
            // Dealer's turn (only if player didn't bust)
            if (playerScore <= 21) {
                Main.load(2, "Dealer's turn...");
                System.out.println("\n--- Dealer's Turn ---");
                displayHand(dealerHand, "Dealer", false);
                
                int dealerScore = calculateHandValue(dealerHand);
                System.out.println("Dealer's total: " + dealerScore);
                
                // Dealer hits until 17 (normal dealer hit limit) or higher
                while (dealerScore < 17) {
                    System.out.println("\nDealer hits...");
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    Card newCard = dealCard();
                    dealerHand.add(newCard);
                    dealerScore = calculateHandValue(dealerHand);
                    
                    System.out.println("Dealer drew: [" + newCard + "]");
                    System.out.println("Dealer's total: " + dealerScore);
                }
                
                // Determine winner
                System.out.println("\n--- Results ---");
                System.out.println("Your total: " + playerScore);
                System.out.println("Dealer's total: " + dealerScore);
                
                if (dealerScore > 21) {
                    System.out.println("\nDealer busts! You win 10 coins!");
                    this.wins++;
                    this.coins += 10;
                } else if (playerScore > dealerScore) {
                    System.out.println("\nYou win 10 coins!");
                    this.wins++;
                    this.coins += 10;
                } else if (playerScore < dealerScore) {
                    System.out.println("\nDealer wins! You lose 5 coins.");
                    this.losses++;
                    setCoins(5);
                } else {
                    System.out.println("\nIt's a tie! No coins lost or gained.");
                }
            }
            
            System.out.println("Current coins: " + this.coins);
            
            // Ask to play again
            boolean validInput = false;
            while (!validInput) {
                System.out.print("\nPlay again? (y/n): ");
                String choice = input.next().toLowerCase();
                
                if (choice.equals("y")) {
                    Main.clear();
                    validInput = true;
                } else if (choice.equals("n")) {
                    playAgain = false;
                    validInput = true;
                    Main.clear();
                } else {
                    System.out.println("Invalid input. Enter 'y' or 'n'.");
                }
            }
        }
    }
}
