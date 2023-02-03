package gui;

import club.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class searchByPositionSceneController implements Initializable {

    Club club;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();

    @FXML
    private TextField positionField;

    @FXML
    private Label errorLabel;

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

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
        prepareTable();
    }

    public void searchButton(ActionEvent actionEvent) {

        String position = Main.prepareString(positionField.getText());

        playerList.clear();

        if(position == null)
        {
            errorLabel.setText("Position can't be null");
            return;
        }

        errorLabel.setText("");

        List<Player> temp = searchByPosition(position);

        if(temp.size() == 0)
        {
            errorLabel.setText("No such player with this position");
            return;
        }

        for(Player player:temp)
        {
            playerList.add(player);
        }

    }

    private List<Player> searchByPosition(String positionName) {
        List<Player> playerList = new ArrayList();

        for(Player player:club.getPlayers())
        {
            if(positionName.equalsIgnoreCase(player.getPosition()))
            {
                playerList.add(player);
            }
        }

        return  playerList;
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

    void reset(){
        positionField.setText("");
        errorLabel.setText("");
        playerList.clear();
    }
}
