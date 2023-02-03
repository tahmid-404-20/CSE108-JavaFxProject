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
import javafx.scene.control.cell.PropertyValueFactory;
import util.NetworkUtil;
import util.Player;
import util.PlayerTransferPacket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class buyPlayerSceneController implements Initializable {

    private Club club;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();

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
    private TableColumn<Player, Double> priceCol;

    @FXML
    private Label errorLabel;

    public void buyButton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        Player playerToBuy = playerTable.getSelectionModel().getSelectedItem();

        if (playerToBuy == null) {
            errorLabel.setText("Select a player first!");
            return;
        }

        errorLabel.setText("");

        PlayerTransferPacket pt = new PlayerTransferPacket();
        pt.setFrom(club.getName());
        pt.setBuyOrSell("Buy");
        pt.setPlayer(playerToBuy);

        NetworkUtil networkUtil = club.getNetworkUtil();
        networkUtil.write(pt);

        playerToBuy = playerTable.getSelectionModel().getSelectedItem();

        if(playerToBuy != null) {
            playerToBuy.setClub(club.getName());
            club.addPlayer(playerToBuy);
            //System.out.println(club.getName() + ": " + playerToBuy.getName());
        } else
        {
            errorLabel.setText("This player is already bought!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }

    public void setClub(Club club) {
        this.club = club;

        playerList.clear();
        playerList = club.getPlayersForSale();

        /*System.out.println("For sale: ");
        for (Player player : playerList) {
            System.out.println(player.getName() + ": " + player.getClub());
        }*/

        prepareTable();
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
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        playerTable.setItems(playerList);
    }

    void reset() {
        errorLabel.setText("");
    }
}
