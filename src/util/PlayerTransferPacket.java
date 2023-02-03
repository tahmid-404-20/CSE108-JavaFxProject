package util;

import java.io.Serializable;

public class PlayerTransferPacket implements Serializable {
    String from;
    String buyOrSell;
    Player player;

    //Setters
    public void setFrom(String from) {
        this.from = from;
    }
    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    //Getters
    public String getFrom() {
        return from;
    }
    public String getBuyOrSell() {
        return buyOrSell;
    }
    public Player getPlayer() {
        return player;
    }
}
