package gui;

import club.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.NetworkUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInScreenController implements Initializable {

    Main main;

    final String SERVER_IP = "127.0.0.1";
    final int SEVER_PORT = 44444;

    @FXML
    private TextField nameField;

    @FXML
    private Label errorLabel;

    public void logInButton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String clubName = Main.prepareString(nameField.getText());

        if (clubName == null) {
            errorLabel.setText("Club Name can't be null");
            return;
        }

        errorLabel.setText("");

        NetworkUtil networkUtil = new NetworkUtil(SERVER_IP, SEVER_PORT);
        networkUtil.write(clubName);

        String response = (String) networkUtil.read();

        if (response.equalsIgnoreCase("LogIn Successful")) {
            Club club = new Club(clubName, networkUtil);
            main.setClub(club);
            main.showMainPage();
        } else {
            errorLabel.setText(response);
            return;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }

    private void reset() {
        nameField.setText("");
        errorLabel.setText("");
    }

    public void setMain(Main main) {
        this.main = main;
    }
    public void resetButton(ActionEvent actionEvent) {
        reset();
    }
}
