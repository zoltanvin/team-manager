package hu.alkfejl.view.controller;

import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Menedzselni kívánt csapat kezdőlapja.
 */
public class TeamMainViewController implements Initializable {

    @FXML
    private Label labelTeamName;
    @FXML
    private Button buttonBackMain;
    @FXML
    private Button buttonMessage;
    @FXML
    private ListView<Player> listViewPlayersInTeam;
    private Team team = new Team();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Csapaton belüli üzeneteket menedzselő felület betöltése.
     */
    @FXML
    public void loadMessageMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/MessageMain.fxml"));
            Parent root = loader.load();
            Scene scene = buttonBackMain.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();
            MessageMainViewController controller = loader.getController();
            controller.initTeam(team);

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Csapat eredményeiért felelős felület betöltése.
     */
    @FXML
    public void loadResults(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/TeamResults.fxml"));
            Parent root = loader.load();
            Scene scene = buttonBackMain.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();
            TeamResultsViewController controller = loader.getController();
            controller.initTeam(team);

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Csapat mérkőzésének hozzáadásáért felelős felület betöltése.
     */
    @FXML
    public void addGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/TeamAddGame.fxml"));
            Parent root = loader.load();
            Scene scene = buttonBackMain.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();
            TeamAddGameViewController controller = loader.getController();
            controller.initTeam(team);

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vissza a kezdőlapra.
     */
    @FXML
    public void loadBackMain(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/Main.fxml"));
            Scene scene = buttonBackMain.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Átadott csapat adatainak betöltése használathoz.
     */
    public void initTeam(Team teamForInit) {
        teamForInit.copyTo(team);
        labelTeamName.setText(team.getName());
        if (team.getPlayers() != null) {
            listViewPlayersInTeam.setItems(team.getPlayers());
            listViewPlayersInTeam.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Player player, boolean empty) {
                    super.updateItem(player, empty);
                    if (empty || player == null || player.getName() == null) {
                        setText(null);
                    } else {
                        setText(player.getName());
                    }
                }
            });
        }
    }
}
