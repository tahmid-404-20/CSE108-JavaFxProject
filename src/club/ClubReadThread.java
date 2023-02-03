package club;

import javafx.collections.ObservableList;
import util.*;

import java.io.IOException;
import java.util.List;

public class ClubReadThread implements Runnable{

    private String clubName;
    private List<Player> players;
    private ObservableList<Player> playersForSale;
    private NetworkUtil networkUtil;
    Thread thr;

    public ClubReadThread(String clubName, List<Player> players, ObservableList<Player> playersForSale, NetworkUtil networkUtil) {
        this.clubName = clubName;
        this.players = players;
        this.playersForSale = playersForSale;
        this.networkUtil = networkUtil;

        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Object o = networkUtil.read();

                if(o instanceof Message)
                {
                    Message msg = (Message) o;

                    if(msg.getFrom().equalsIgnoreCase("Server"))
                    {
                        if(msg.getText().equalsIgnoreCase("Transfer took place"))
                        {
                            playersForSale.clear();
                        }
                    }
                }

                if(o instanceof Player)
                {
                    Player player = (Player)o;
                    playersForSale.add(player);
                    //System.out.println(clubName + " received: " + player.getName() + " for sale by: " + player.getClub());
                }

                if(o instanceof PlayerListPacket)
                {
                    PlayerListPacket pl = (PlayerListPacket) o;

                    for(Player player:pl.getPlayerList())
                    {
                        players.add(player);
                    }

                    //System.out.println("From: " + pl.getFrom() + " received players");
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
