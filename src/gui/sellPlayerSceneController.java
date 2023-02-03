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
import util.NetworkUtil;
import util.Player;
import util.PlayerTransferPacket;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class sellPlayerSceneController implements Initializable {

    Club club;
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
    private TextField priceField;

    @FXML
    private Label errorLabel;

    public void sellButton(ActionEvent actionEvent) throws IOException {

        String priceStr = Main.prepareString(priceField.getText());

        if(priceStr == null)
        {
            errorLabel.setText("Put the price first!");
            return;
        }

        Double price = 0.0;
        try{
            price = Double.parseDouble(priceStr);
        } catch (Exception e)
        {
            errorLabel.setText("Price must be real value");
            return;
        }

        errorLabel.setText("");

        Player playerForSale = playerTable.getSelectionModel().getSelectedItem();

        if(playerForSale == null)
        {
            errorLabel.setText("Select a Player First");
            return;
        }

        errorLabel.setText("");

        playerForSale.setPrice(price);

        PlayerTransferPacket pt = new PlayerTransferPacket();
        pt.setFrom(club.getName());
        pt.setBuyOrSell("Sell");
        pt.setPlayer(playerForSale);

        NetworkUtil networkUtil = club.getNetworkUtil();
        networkUtil.write(pt);

        club.removePlayer(playerForSale);
        playerList.remove(playerForSale);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
        prepareTable();
    }

    public void setClub(Club club) {
        this.club = club;

        List<Player> players = this.club.getPlayers();
        playerList.clear();
        for(Player player:players)
        {
            playerList.add(player);
        }
    }
    void reset() {
        priceField.setText("");
        errorLabel.setText("");
        playerList.clear();
    }

    void prepareTable()
    {
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
}
