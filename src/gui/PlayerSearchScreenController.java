package gui;

import club.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSearchScreenController implements Initializable {

    Club club;

    @FXML
    public VBox playerSearchContent;
    @FXML
    public Label titleLabel;

    public void searchByPlayerNameButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchByPlayerNameScreen.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchByPlayerNameScreenController controller = loader.getController();
        controller.setClub(this.club);

        playerSearchContent.getChildren().setAll(root);
    }

    public void searchByClubCountryButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchByClubCountryScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchByClubCountrySceneController controller = loader.getController();
        controller.setClub(this.club);

        playerSearchContent.getChildren().setAll(root);
    }

    public void searchByPositionButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchByPositionScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchByPositionSceneController controller = loader.getController();
        controller.setClub(this.club);

        playerSearchContent.getChildren().setAll(root);
    }

    public void searchBySalaryRangeButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchBySalaryRangeScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchBySalaryRangeSceneController controller = loader.getController();
        controller.setClub(this.club);

        playerSearchContent.getChildren().setAll(root);
    }

    public void countryWiseCountButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CountryWiseCountScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CountryWiseCountSceneController controller = loader.getController();
        controller.setClub(this.club);

        playerSearchContent.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setClub(Club club) {
        this.club = club;
        titleLabel.setText("Player Search - " + this.club.getName());
    }
}
