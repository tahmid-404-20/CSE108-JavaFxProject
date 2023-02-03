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

public class searchBySalaryRangeSceneController implements Initializable {

    Club club;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();

    @FXML
    private Label minimumLabel;

    @FXML
    private Label maximumLabel;

    @FXML
    private Label notFoundLabel;

    @FXML
    private TextField minimumField;

    @FXML
    private TextField maximumField;

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


    public void searchButton(ActionEvent actionEvent) {

        playerList.clear();
        minimumLabel.setText("");
        maximumLabel.setText("");
        notFoundLabel.setText("");

        boolean isNull = false;
        boolean isDouble = true;

        String minStr = Main.prepareString(minimumField.getText());
        String maxStr = Main.prepareString(maximumField.getText());


        if(minStr == null)
        {
            minimumLabel.setText("Minimum Salary can't be null");
            isNull = true;
        }

        if(maxStr == null)
        {
            maximumLabel.setText("Maximum Salary can't be null");
            isNull = true;
        }

        if(isNull){
            return;
        }

        Double minSalary = 0.0, maxSalary = 0.0;

        try{
            minSalary = Double.parseDouble(minStr);
        } catch(Exception e)
        {
            minimumLabel.setText("Must be a real number!!");
            isDouble = false;
        }

        try{
            maxSalary = Double.parseDouble(maxStr);
        } catch(Exception e)
        {
            maximumLabel.setText("Must be a real number!!");
            isDouble = false;
        }

        if(!isDouble){
            return;
        }

        List<Player> temp = searchBySalaryRange(minSalary,maxSalary);

        if(temp.size() == 0)
        {
            notFoundLabel.setText("Not Found!");
            return;
        }

        for(Player player:temp)
        {
            playerList.add(player);
        }
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
        prepareTable();
    }

    private List<Player> searchBySalaryRange(double minSalary, double maxSalary) {

        List<Player> playerList = new ArrayList();

        for(Player player:club.getPlayers())
        {
            double salary = player.getWeeklySalary();
            if(salary >= minSalary && salary <= maxSalary)
            {
                playerList.add(player);
            }
        }

        return playerList;
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

        minimumLabel.setText("");
        maximumLabel.setText("");
        notFoundLabel.setText("");
        minimumField.setText("");
        maximumField.setText("");

        playerList.clear();
    }
}
