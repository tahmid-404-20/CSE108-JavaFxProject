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
import util.Player;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class clubSearchScreenController implements Initializable {

    Club club;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();

    @FXML
    public Label titleLabel;

    @FXML
    private Label yearlySalary;

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


    public void maxSalaryButton(ActionEvent actionEvent) {
        reset();
        List<Player> players = club.getPlayersWithMaxSalary();

        for(Player player:players)
        {
            playerList.add(player);
        }
    }
    public void maxHeightButton(ActionEvent actionEvent) {
        reset();
        List<Player> players = club.getPlayersWithMaxHeight();

        for(Player player:players)
        {
            playerList.add(player);
        }
    }
    public void maxAgeButton(ActionEvent actionEvent) {
        reset();
        List<Player> players = club.getPlayersWithMaxAge();

        for(Player player:players)
        {
            playerList.add(player);
        }
    }
    public void totalYearlySalaryButton(ActionEvent actionEvent) {
        reset();
        Double salary = club.getYearlyIncome();
        yearlySalary.setText("Yearly Income: " + salary + " $");
    }

    public void setClub(Club club) {
        this.club = club;
        titleLabel.setText("Club Search -" + club.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
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

        playerTable.setItems(playerList);
    }
    void reset() {
        yearlySalary.setText("");
        playerList.clear();
    }
}
