package hu.alkfejl.view.controller;

import hu.alkfejl.controller.TeamController;
import hu.alkfejl.controller.TeamControllerImpl;
import hu.alkfejl.model.Team;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Almalmazás kezdőlapja.
 */
public class MainViewController implements Initializable {

    @FXML
    private StackPane parentContainer;
    @FXML
    private TableView<Team> tableViewTeams;
    private final TeamController teamController = new TeamControllerImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI();
    }

    /**
     * Táblázat létrehozása és feltöltése a megfelelő elemekkel.
     */
    private void loadUI() {
        TableColumn<Team, String> nameCol = new TableColumn<>("Csapat név");
        nameCol.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        nameCol.setMinWidth(250);
        TableColumn<Team, Integer> playersCol = new TableColumn<>("Játékosok száma");
        playersCol.setCellValueFactory(new PropertyValueFactory<Team, Integer>("playerCount"));
        playersCol.setMinWidth(250);
        TableColumn<Team, Void> actionsCol = new TableColumn<>("Menedzselés");
        actionsCol.setMinWidth(200);
        actionsCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonManage = new Button("Menedzsel");

                {
                    buttonManage.getStyleClass().add("main-btn");

                    buttonManage.setOnAction(event -> {
                        Team team = getTableView().getItems().get(getIndex());
                        manageTeam(team);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(buttonManage);
                    }
                }
            };
        });
        tableViewTeams.setItems(FXCollections.observableArrayList(teamController.getAllTeams()));
        tableViewTeams.getColumns().addAll(nameCol, playersCol, actionsCol);
    }

    /**
     * Csapat menedzselő felületének betöltése és a kiválasztott csapat átadása a felületnek.
     */
    private void manageTeam(Team team) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/TeamMain.fxml"));
            Parent root = loader.load();
            TeamMainViewController controller = loader.getController();
            controller.initTeam(team);

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
