package server;

import club.Club;
import gui.LogInScreenController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.NetworkUtil;
import util.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server{

    private static final String INPUT_FILE_NAME = "players.txt";

    private List<Player> playersList;
    private List<Player> playersForSale;
    private ServerSocket serverSocket;
    private List<Club> clubsList;
    private HashMap<String, NetworkUtil> clientMap;

    Server() {
        clientMap = new HashMap<>();
        playersList = new ArrayList<>();
        playersForSale = new ArrayList<>();
        clubsList = new ArrayList<>();
        try {
            readFromFile();
            arrangeIntoClubs();
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }
    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();

        boolean found = false;

        for(Club club:clubsList)
        {
            if(clientName.equalsIgnoreCase(club.getName()))
            {
                found = true;
                break;
            }
        }

        if(found)
        {
            boolean isLoggedIn = false;

            for(String key: clientMap.keySet())
            {
                if(key.equalsIgnoreCase(clientName))
                {
                    isLoggedIn = true;
                    break;
                }
            }

            if(!isLoggedIn)
            {
                clientMap.put(clientName, networkUtil);
                System.out.println("Received: " + clientName);
                new ServerReadThread(playersList, clubsList, clientMap, playersForSale, networkUtil);
                networkUtil.write("LogIn Successful");
            } else {
                networkUtil.write("Already LoggedIn from another interface!");
                networkUtil.closeConnection();
            }
        }

        else
        {
            networkUtil.write("No such club with this name!!");
            networkUtil.closeConnection();
        }
    }

    private void readFromFile() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while(true)
        {
            Player player = new Player();
            String line = br.readLine();
            if(line == null) break;
            String[] tokens = line.split(",");

            player.setName(tokens[0]);
            player.setCountry(tokens[1]);
            player.setAgeInYears(Integer.parseInt(tokens[2]));
            player.setHeightInMeters(Double.parseDouble(tokens[3]));
            player.setClub(tokens[4]);
            player.setPosition(tokens[5]);
            player.setNumber(Integer.parseInt(tokens[6]));
            player.setWeeklySalary(Double.parseDouble(tokens[7]));

            playersList.add(player);
        }
        br.close();
    }
    private void arrangeIntoClubs() {
        if(playersList.size() != 0)
        {
            for(Player player:playersList)
            {
                String clubName = player.getClub();
                boolean found = false;
                for(Club club:clubsList)
                {
                    if(clubName.equalsIgnoreCase(club.getName()))
                    {
                        club.addPlayer(player);
                        found = true;
                    }
                }

                if(!found)
                {
                    Club newClub = new Club();
                    newClub.setName(clubName);
                    newClub.addPlayer(player);
                    clubsList.add(newClub);
                }
            }
        }
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
