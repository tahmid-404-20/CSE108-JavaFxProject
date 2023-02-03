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

public class buySellChooseController {

    private Club club;

    @FXML
    private VBox mainContent;
    @FXML
    public Label titleLabel;

    public void buyAPlayerButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buyPlayerScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        buyPlayerSceneController controller = loader.getController();
        controller.setClub(this.club);

        mainContent.getChildren().setAll(root);
    }

    public void sellAPlayerButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sellPlayerScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        sellPlayerSceneController controller = loader.getController();
        controller.setClub(this.club);

        mainContent.getChildren().setAll(root);
    }

    public void setClub(Club club) {
        this.club = club;
        titleLabel.setText("Buy or Sell Player - " + club.getName());
    }
}
