package util;

import java.io.Serializable;
import java.util.List;

public class PlayerListPacket implements Serializable {

    String from;
    List<Player> playerList;

    public String getFrom() {
        return from;
    }
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
