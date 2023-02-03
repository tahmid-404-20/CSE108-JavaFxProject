package server;

import club.Club;
import util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerReadThread implements Runnable {
    private List<Player> playersList;
    private List<Club> clubsList;
    public HashMap<String, NetworkUtil> clientMap;
    private List<Player> playersForSale;
    NetworkUtil networkUtil;
    boolean exit = false;

    public ServerReadThread(List<Player> playersList, List<Club> clubsList, HashMap<String, NetworkUtil> clientMap, List<Player> playersForSale, NetworkUtil networkUtil) {
        this.playersList = playersList;
        this.clubsList = clubsList;
        this.clientMap = clientMap;
        this.playersForSale = playersForSale;
        this.networkUtil = networkUtil;

        Thread thr = new Thread(this);
        thr.start();
    }


    @Override
    public void run() {

        while (!exit) {
            try {
                Object o = networkUtil.read();

                if (o instanceof Message) {
                    Message msg = (Message) o;
                    String from = msg.getFrom();
                    String text = msg.getText();

                    if (text.equalsIgnoreCase("Send My Players")) {
                        NetworkUtil nu = clientMap.get(from);
                        PlayerListPacket pl = null;

                        if (nu != null) {
                            pl = new PlayerListPacket();
                            pl.setFrom("Server");
                            List<Player> playersToBeSent = new ArrayList<>();

                            for (Club c : clubsList) {
                                if (from.equalsIgnoreCase(c.getName())) {

                                    for (Player player : c.getPlayers()) {
                                        if (!playersForSale.contains(player)) {
                                            playersToBeSent.add(player);
                                        }
                                    }
                                    pl.setPlayerList(playersToBeSent);
                                    break;
                                }
                            }
                        }

                        nu.write(pl);
                    }

                    if (text.equalsIgnoreCase("Send Transaction Update")) {
                        sendTransferUpdate(from);
                    }

                    if (text.equalsIgnoreCase("LogOut")) {
                        NetworkUtil nu = clientMap.get(from);

                        clientMap.remove(from);

                        System.out.println(from + " Logged out. Now active: ");
                        for (String key : clientMap.keySet()) {
                            System.out.println(key);
                        }

                        nu.closeConnection();
                        stop();
                    }
                } else if (o instanceof PlayerTransferPacket) {
                    PlayerTransferPacket pt = (PlayerTransferPacket) o;
                    String from = pt.getFrom();
                    String buyOrSell = pt.getBuyOrSell();
                    Player player = pt.getPlayer();

                    if (buyOrSell.equalsIgnoreCase("Sell")) {
                        playersForSale.add(player);
                        System.out.println(from + " is selling " + player.getName() + " for :" + player.getPrice() + "M");
                        sendTransferUpdate();
                    }

                    if (buyOrSell.equalsIgnoreCase("Buy")) {
                        synchronized (playersForSale) {
                            boolean found = false;
                            for (Player p : playersForSale) {
                                if (p.equals(player)) {
                                    found = true;
                                    playersForSale.remove(p);
                                    break;
                                }
                            }

                            if (found) {
                                int index = -1;

                                for (Player p : playersList) {
                                    if (p.equals(player)) {
                                        index = playersList.indexOf(p);
                                        playersList.remove(p);
                                        break;
                                    }
                                }

                                //Update the club of player
                                player.setClub(from);
                                playersList.add(index, player);
                                for(Club c:clubsList)
                                {
                                    if(c.getName().equalsIgnoreCase(player.getClub()))
                                    {
                                        c.addPlayer(player);
                                    }
                                }
                                System.out.println(from + " has bought " + player.getName() + " for :" + player.getPrice() + "M");
                                sendTransferUpdate();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void stop() {
        exit = true;
    }

    void sendTransferUpdate() throws IOException {
        Message msg = new Message();

        msg.setFrom("Server");
        msg.setText("Transfer took place");

        for (String key : clientMap.keySet()) {

            System.out.println("Sending: " + key);
            NetworkUtil nu = clientMap.get(key);
            nu.write(msg);

            for (Player player : playersForSale) {
                if (key.equalsIgnoreCase(player.getClub()))
                    continue;

                nu.write(player);
                System.out.println(player.getName());
            }
        }
    }

    void sendTransferUpdate(String from) throws IOException {
        NetworkUtil nu = clientMap.get(from);

        Message msg = new Message();
        msg.setFrom("Server");
        msg.setText("Transfer took place");
        nu.write(msg);

        //System.out.println("Sending: " + from);
        for (Player player : playersForSale) {
            if (from.equalsIgnoreCase(player.getClub()))
                continue;

            nu.write(player);
            //System.out.println(player.getName());
        }
    }
}
