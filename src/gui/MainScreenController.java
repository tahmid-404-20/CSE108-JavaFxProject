package gui;

import club.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import util.Message;
import util.NetworkUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class MainScreenController implements Initializable {

    Main main;
    Club club;

    @FXML
    public VBox mainContent;

    public void playerSearchButton(ActionEvent actionEvent) throws IOException {
        showPlayerSearchMenu();
    }

    public void clubSearchButton(ActionEvent actionEvent) throws IOException {
        showClubSearchMenu();
    }

    public void buySellPlayerButton(ActionEvent actionEvent) throws IOException {
        showBuySellMenu();
    }

    public void exitButton(ActionEvent actionEvent) throws Exception {
        // File save korte hoile server e request dite hobe
        NetworkUtil nu = club.getNetworkUtil();

        Message msg = new Message();
        msg.setFrom(club.getName());
        msg.setText("LogOut");
        nu.write(msg);

        nu.closeConnection();

        exit(0);
    }

    private void showPlayerSearchMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PlayerSearchScreen.fxml"));

        Parent root = loader.load();
        // Loading the controller
        PlayerSearchScreenController controller = loader.getController();
        controller.setClub(this.club);

        mainContent.getChildren().setAll(root);
    }

    private void showClubSearchMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clubSearchScreen.fxml"));
        Parent root = loader.load();

        // Loading the controller
        clubSearchScreenController controller = loader.getController();
        controller.setClub(this.club);

        mainContent.getChildren().setAll(root);
    }

    private void showBuySellMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buySellChoose.fxml"));

        Parent root = loader.load();
        // Loading the controller
        buySellChooseController controller = loader.getController();
        controller.setClub(this.club);

        mainContent.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setClub(Club club) {
        this.club = club;

        //System.out.println("MainScreencontroller --> club: " + this.club.getName());
    }
}
