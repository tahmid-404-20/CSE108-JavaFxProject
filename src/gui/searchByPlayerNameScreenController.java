package gui;

import club.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class searchByPlayerNameScreenController implements Initializable {

    Club club;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea resultTextField;

    @FXML
    private Label errorLabel;


    private void reset() {
        errorLabel.setText("");
        resultTextField.setText("");
        nameField.setText("");
    }

    public void searchButton(ActionEvent actionEvent) {
        String playerName = Main.prepareString(nameField.getText());
        if (playerName == null) {
            errorLabel.setText("Name can't be null for search");
            resultTextField.setText("");
            return;
        }

        Player player = searchByPlayerName(playerName);
        if (player == null) {
            resultTextField.setText("No such player with this name");
        } else {
            resultTextField.setText(player.showInfo());
        }
        errorLabel.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }

    private Player searchByPlayerName(String playerName) {

        for (Player player : club.getPlayers()) {
            if (playerName.equalsIgnoreCase(player.getName())) {
                return player;
            }
        }

        return null;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
