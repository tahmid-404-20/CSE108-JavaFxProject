package util;

public class Country {
    private String name;
    private int playerCount;

    public Country(String name) {
        this.name = name;
        this.playerCount = 0;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getPlayerCount() {
        return playerCount;
    }

    public void addPlayer()
    {
        playerCount++;
    }
}
