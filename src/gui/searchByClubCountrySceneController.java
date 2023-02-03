package gui;

import club.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class searchByClubCountrySceneController implements Initializable {

    Club club;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();

    @FXML
    private TextField countryField;

    @FXML
    private Label countryLabel;

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player, String> nameCol;

    @FXML
    private TableColumn<Player, String> countryCol;

    @FXML
    private TableColumn<Player, Integer> ageCol;

    @FXML
    private TableColumn<Player, Double> heightCol;

    @FXML
    private TableColumn<Player, String> clubCol;

    @FXML
    private TableColumn<Player, String> positionCol;

    @FXML
    private TableColumn<Player, Integer> numberCol;

    @FXML
    private TableColumn<Player, Double> weeklySalaryCol;

    @FXML
    private Label notFoundLabel;

    public void searchButton(ActionEvent actionEvent) {

        playerList.clear();
        String country = Main.prepareString(countryField.getText());

        if(country == null)
        {
            countryLabel.setText("Country can't be null");
            return;
        }

        countryLabel.setText("");

        List<Player> players = searchByClubCountry(country);

        if(players.size() == 0)
        {
            notFoundLabel.setText("Player Not Found");
            return;
        }

        notFoundLabel.setText("");

        for(Player player: players)
        {
            playerList.add(player);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
        prepareTable();
    }

    public void setClub(Club club) {
        this.club = club;
    }

    private List<Player> searchByClubCountry(String countryName) {

        List<Player> playerList = new ArrayList();

        for(Player player:club.getPlayers())
        {
            if(countryName.equalsIgnoreCase(player.getCountry()))
            {
                playerList.add(player);
            }
        }
        return playerList;
    }
    void prepareTable() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("ageInYears"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("heightInMeters"));
        clubCol.setCellValueFactory(new PropertyValueFactory<>("club"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        weeklySalaryCol.setCellValueFactory(new PropertyValueFactory<>("weeklySalary"));

        playerTable.setItems(playerList);
    }
    void reset() {
        countryField.setText("");
        countryLabel.setText("");
        notFoundLabel.setText("");
        playerList.clear();
    }
}
