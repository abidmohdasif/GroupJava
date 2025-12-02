// 12/02/25
// Abid, Karim, Marcus, Jack
// This is an Arcade class that serves as a base
// The four games are black jack, rock paper scissors, heads and tails, and tic tac toe

public abstract class Arcade {
    protected int wins;
    protected int losses;
    protected int coins;
    protected boolean multiplayer;
    protected String gameName;

    // Constructor sets the default values
    public Arcade(String gameName) {
        this.coins = 50;
        this.wins = 0;
        this.losses = 0;
        this.multiplayer = false;
        this.gameName = gameName;
    }
    
    // Getter Methods
    public int getWins(){
        return this.wins;
    }
    public int getLosses(){
        return this.losses;
    }
    public int getCoins(){
        return this.coins;
    }
    public boolean getMultiplayer(){
        return this.multiplayer;
    }
    public String getName(){
        return this.gameName;
    }

    // Setter Methods
    public void setWins(int wins){
        this.wins += wins;
    }
    public void setLosses(int losses){
        this.losses += losses;
    }
    public void setCoins(int coins){
        this.coins -= coins;
    }
    public void setMultiplayer(boolean multiplayer){
        this.multiplayer = multiplayer;
    }
    public void setGameName(String gameName){
        this.gameName = gameName;
    }
    
    // Abstract Methods
    public abstract void playGame();
    
    public void displayStats(){
        System.out.println("Game: " + this.gameName);
        System.out.println("Wins: " + this.wins);
        System.out.println("Loses: " + this.losses);
        System.out.println("Coins: " + this.coins);
    }
}

//public class Arcade {
//    public static void main(String[] args){
//        
//    }
//}