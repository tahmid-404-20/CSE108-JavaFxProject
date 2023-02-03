package club;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Message;
import util.NetworkUtil;
import util.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Club {

    private String name;
    private List<Player> players;
    //private Player[] players;
    private int playerCount;
    private NetworkUtil networkUtil;
    private ObservableList<Player> playersForSale;

    //Constructor
    public Club() {
        //players = new Player[7];
        players = new ArrayList<>();
        playersForSale = FXCollections.observableArrayList();
        playerCount = 0;
    }

    public Club(String name, NetworkUtil networkUtil) throws IOException {
        //players = new Player[7];
        players = new ArrayList<>();
        playersForSale = FXCollections.observableArrayList();
        this.name = name;
        this.networkUtil = networkUtil;

        new ClubReadThread(this.name,players,playersForSale,networkUtil);

        Message msg =  new Message();
        // Obtain Players
        msg.setFrom(this.name);
        msg.setText("Send My Players");
        this.networkUtil.write(msg);
        //obtain players for sale
        msg.setText("Send Transaction Update");
        this.networkUtil.write(msg);
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }

    //Getters
    public String getName() {
        return name;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public ObservableList<Player> getPlayersForSale() {
        return playersForSale;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void addPlayer(Player player) {
        players.add(player);
        playerCount++;
        //players[playerCount++] = new Player(player);
    }
    public void removePlayer(Player player)
    {
        for(Player p:players)
        {
            if(p.equals(player))
            {
                players.remove(p);
                playerCount--;
                break;
            }
        }
    }

    //Club Search Menu Functions
    public List<Player> getPlayersWithMaxSalary() {
        double maxSalary = -1.0;

        for (Player player:players) {
            if (maxSalary < player.getWeeklySalary()) {
                maxSalary = player.getWeeklySalary();
            }
        }

        List<Player> maxList = new ArrayList();

        for (Player player:players) {
            if (maxSalary == player.getWeeklySalary()) {
                maxList.add(player);
            }
        }
        return maxList;
    }
    public List<Player> getPlayersWithMaxAge() {
        int maxAge = -1;

        for (Player player:players) {
            if (maxAge < player.getAgeInYears()) {
                maxAge = player.getAgeInYears();
            }
        }

        List<Player> maxList = new ArrayList();

        for (Player player:players) {
            if (maxAge == player.getAgeInYears()) {
                maxList.add(player);
            }
        }

        return maxList;
    }
    public List<Player> getPlayersWithMaxHeight() {
        double maxHeight = -1.0;

        for (Player player:players) {
            if (maxHeight < player.getHeightInMeters()) {
                maxHeight = player.getHeightInMeters();
            }
        }

        List<Player> maxList = new ArrayList();

        for (Player player:players) {
            if (maxHeight == player.getHeightInMeters()) {
                maxList.add(player);
            }
        }

        return maxList;
    }
    public double getYearlyIncome() {
        double totalSalary = 0.0;

        for (Player player:players) {
            totalSalary += player.getWeeklySalary();
        }

        return (totalSalary / 7.0) * 365;
    }
}
