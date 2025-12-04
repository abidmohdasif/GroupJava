/*
 * Marcus Alexio Prado
 * Date Created: 12/3/25
 * Last Modified:
 * Purpose: Coin Toss Game
*/

import java.util.*;

public class Coin extends Arcade {
    protected String[] coinCombo;

    public Coin(){
        super("Coin Toss");
        this.coinCombo = new String[3];
    }

    public String getCoinFlip(){
        int randomNum = (int)(Math.random() * 2);
        return (randomNum == 1) ? "Heads" : "Tails";
    }

    public String[] getCoinCombo(){
        for (int i = 0; i < 3; i++){
            this.coinCombo[i] = getCoinFlip();
        }
        return this.coinCombo;
    }

    public boolean compare(String coin1, String coin2){
        return (coin1 == coin2);
    }

    public void getRules(){
        System.out.println("\nCoin Toss Concept & Rules:");
        System.out.println("1. Fight Against an enemy by flipping coins to get a specific combination");
        System.out.println("2. Each round sets a randomized coin combo (Ex. Head, Tail, Tail)");
        System.out.println("3. You and the enemy will flip your coin three times");
        System.out.println("4. If you or the enemy has the exact coin combo, gain 1 point. If both achieve the exact coin combo, no one gets a point");
        System.out.println("5. First to three points wins the game");
    }

    @Override
    public void playGame(){

        //Ouput rules
        getRules();

        Scanner input = new Scanner(System.in);     
          
        //Starter Introduction
        System.out.println("\n----- Welcome to Coin Toss -----");
        System.out.println("Cost to play: 3 coins per match.");     

        //Check amount of coins
        if (this.coins < 1) {
            System.out.println("Not enough coins to play!");
            return;            
        }       

        //Set and ouput # of coins
        setCoins(3);
        System.out.println("Remaining coins: " + this.coins);      

        boolean play = true;    

        while (play){
            String[] coinCombo = getCoinCombo();

            System.out.print("\nCoin Combo: [");
            for (int i = 0; i < 3; i++){
                if (i == 2){
                    System.out.print(coinCombo[i]);
                } else {
                    System.out.print(coinCombo[i] + ", ");
                }   
            }
            System.out.print("]");

            boolean player = true;
            int coinNum = 1;

            while (player) {
                System.out.println("\n\nEnter 'Flip' to flip coin " + coinNum++ +  ": ");
                String hidden = input.next();
                System.out.println(hidden);
                if (!(hidden == "Flip")){
                    System.out.print("ERROR: Enter Correct Input");
                    continue;
                }
                System.out.print("\nCoin Result: " + getCoinFlip());


                if (coinNum == 4){
                    player = false;
                }
            }
            

            play = false;
            //System.out.println(getCoinCombo());
        }

        
    }
}