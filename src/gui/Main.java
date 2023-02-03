package gui;

import club.Club;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    //public static final String SERVER_IP = "127.0.0.1";
    //public static final int SEVER_PORT = 44444;

    private Stage stage;
    private Club club;

    //public static List<Player> playersList = new ArrayList();
    //public static List<Club> clubsList = new ArrayList();

    public static String prepareString(String str) {
        str = str.trim();
        String []tokens = str.split(" ");
        str = new String();

        for(String token:tokens)
        {
            if(token.length() == 0)
            {
                continue;
            }
            str = str + token + " ";
        }

        str = str.trim();

        if(str.length()==0)
            return null;

        return str;
    }

    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Football Player Database Management System");
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LogInScreenController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    public  void showMainPage() throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MainScreenController controller = loader.getController();
        controller.setClub(club);
        controller.setMain(this);

        // Set the primary stage
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

