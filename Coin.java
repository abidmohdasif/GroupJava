/*
 * Marcus Alexio Prado
 * Date Created: 12/3/25
 * Last Modified: 12/4/25
 * Purpose: Coin Toss Game
*/

import java.util.*;

public class Coin extends Arcade {
    //Variables to keep
    protected String[] coinCombo;
    protected String[] playerCoins;
    protected String[] enemyCoins;

    //Constructor
    public Coin(){
        super("Coin Toss");
    }

    /* Getter Methods */
    //Returns a randomized coin flip result
    public String getCoinFlip(){
        int randomNum = (int)(Math.random() * 2);
        return (randomNum == 1) ? "Heads" : "Tails";
    }
    //Returns a list of coin flips
    public String[] getCoinList(){
        String[] sample = new String[3]; 
        for (int i = 0; i < 3; i++){
            sample[i] = getCoinFlip();
        }
        return sample;
    }
    //Returns output format of a coin list
    public void outputCoinLists(String[] coins, String title){
        System.out.print("\n" + title + ": [");
        for (int i = 0; i < 3; i++){ 
            if (i == 2){
                System.out.print(coins[i]);
            } else {
                System.out.print(coins[i] + ", ");
            }   
        }
        System.out.print("]");        
    }
    //Returns the rules of the game
    public void getRules(){
            System.out.println("Coin Toss Concept & Rules:");
            System.out.println("1. Fight Against an enemy by flipping coins to get a specific combination");
            System.out.println("2. Each round sets a randomized coin combo (Ex. Head, Tail, Tail)");
            System.out.println("3. You and the enemy will flip your coin three times");
            System.out.println("4. If you or the enemy has the exact coin combo, gain 1 point. If both achieve the exact coin combo, no one gets a point");
            System.out.println("5. First to three points wins the game");
        }

    //Compares two coin lists and returns results if they equal each other 
    public boolean compareList(String[] list1, String[] list2){
        int checker = 0;
        for (int i = 0; i < list1.length; i++){
            if (list1[i].equals(list2[i])){
                checker += 1;
            }
        }
        return (checker == 3);
    }

    //Abstract method playGame
    @Override
    public void playGame(){
        //Clear Screen
        Main.clear();
        //Ouput Rules
        getRules();

        //Creating Scanner
        Scanner input = new Scanner(System.in);     
        //Starter Introduction
        System.out.println("\n----- Welcome to Coin Toss -----");
        System.out.println("Cost to play: 3 coins per match.");     

        //Check amount of coins & output answer
        if (this.coins < 3) {
            System.out.println("Not enough coins to play!");
            return;            
        } else {
            setCoins(3);
            System.out.println("Remaining coins: " + this.coins);  
        }

        int roundCounter = 1;

        //Game Round
        boolean play = true;    
        while (play && this.coins >= 6){
            //OUtput Round Counter
            System.out.println("\nRound: "+ String.valueOf(roundCounter++));

            //Create Coin Combo
            this.coinCombo = getCoinList();
            //Output Coin Combo
            outputCoinLists(coinCombo, "Coin Combo");

            //Predetermine round results
            this.playerCoins = getCoinList();
            this.enemyCoins = getCoinList();

            //Player Turn
            boolean player = true;
            while (player) {
                //Print Statement to flip coins
                System.out.println("\nEnter 'flip' to flip coins: ");
                String ghost1 = input.next().toLowerCase();
                //Restart Turn when player inputs incorrectly
                if (!(ghost1.equals("flip"))){
                    System.out.print("ERROR: Enter Correct Input");
                    continue;
                }

                //Fliping Coin Animation
                String coins = "";
                for (int j = 0; j < 3; j++){
                    coins = coins + this.playerCoins[j] + " | ";
                    Main.load(3,coins);
                }

                //Output Player Coins
                outputCoinLists(this.playerCoins, "Player Coins");
                player = !player;
            }
            
            //User input to reveal results
            boolean enemy = true;
            while (enemy){
                //Output statement to reveal coins
                System.out.println("\nEnter 'go' to reveal your results with the enemy: ");
                String ghost2 = input.next().toLowerCase();
                //Restart statement when player inputs incorrectly
                if (!(ghost2.equals("go"))){
                    System.out.print("ERROR: Enter Correct Input");
                    continue;
                }
                enemy = !enemy;
            }

            //Result Loading Animation
            Main.load(4, "Revealing Results...");
            
            //Coin Flip Results
            outputCoinLists(this.coinCombo, "Coin Combo");
            outputCoinLists(this.playerCoins, "Player Coin");
            outputCoinLists(this.enemyCoins, "Enemy Coin");

            //Interpreting Coin Flip lists
            /*
             * 1. Coin combo equals player and enemy, draw
             * 2. Coin combo equals player, win
             * 3. Coin combo equals enemy, lose
             * 4. Coin combo does not equal to any, neutral
             */
            if (compareList(this.coinCombo, this.playerCoins) & compareList(this.coinCombo, this.enemyCoins)){
                System.out.println("\n\nIt is a draw!");
            } else if (compareList(this.coinCombo, this.playerCoins)){
                System.out.println("\n\nYou won the game!");
                this.wins += 1;
                play = !play;
                continue;
            } else if (compareList(this.coinCombo, this.enemyCoins)){
                System.out.println("\n\nYou lost the game!");
                this.losses += 1;
                play = !play;
                continue;
            } else {
                System.out.println("\n\nNo one got it!");
            }

            //User input for next round or end game
            boolean nextRound = true;
            while (nextRound){
                //Output statement of user input
                System.out.println("\n(y/n) Continue to the next round? ");
                String next = input.next().toLowerCase();
                /*
                 * 1. User says yes, continue
                 * 2. User says no, end game
                 * 3. User inputs incorrectly, restart statement
                 */
                switch (next){
                    case "y":
                        nextRound = !nextRound;
                        Main.clear();
                        break;
                    case "n":
                        nextRound = !nextRound;
                        play = !play;
                        Main.clear();
                        break;
                    default:
                        System.out.println("ERROR: Enter correct input");
                }
            }
        }

        //User input for new game or leave game
        boolean nextGame = true;
        while (nextGame){
            //Output statement of user input
            System.out.println("\n(y/n) Play a new game? (Saying no will bring you to the arcade menu)");
            String game = input.next().toLowerCase();
            /*
             * 1. User says yes, create new game
             * 2. User says no, go to Arcade Menu
             * 3. User inputs incorrectly, restart statement
             */
            switch (game){
                case "y":
                    this.playGame();
                    nextGame = !nextGame;
                    break;
                case "n":
                    nextGame = !nextGame;
                    break;
                default:
                    System.out.println("ERROR: Enter correct input");
            }
        }
    }
}
