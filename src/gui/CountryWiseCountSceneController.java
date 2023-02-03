package gui;

import club.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Country;
import util.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CountryWiseCountSceneController implements Initializable {

    Club club;
    private ObservableList<Country> countryList = FXCollections.observableArrayList();

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, String> countryCol;

    @FXML
    private TableColumn<Country, Integer> noPlayerCol;

    public void showResultButton(ActionEvent actionEvent) {
        countryList.clear();
        List<Country> temp = findCountryWisePlayerCount();

        for(Country c:temp)
        {
            countryList.add(c);
        }
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryList.clear();

        countryCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        noPlayerCol.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
        countryTable.setItems(countryList);
    }

    private List<Country> findCountryWisePlayerCount()  {
        List<Country> countryList = new ArrayList();

        for(Player player:club.getPlayers())
        {
            String countryName = player.getCountry();
            boolean found = false;
            for(Country c:countryList)
            {
                if(countryName.equalsIgnoreCase(c.getName()))
                {
                    c.addPlayer();
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                Country country = new Country(player.getCountry());
                country.addPlayer();
                countryList.add(country);
            }
        }

        return countryList;
    }

}
