package hu.alkfejl.view.controller;

import hu.alkfejl.controller.PlayerController;
import hu.alkfejl.controller.PlayerControllerImpl;
import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Csapaton belüli üzenet menedzselés kezdőlapja.
 */
public class MessageMainViewController implements Initializable {

    @FXML
    private TableView<Player> tableViewSent;
    @FXML
    private TableView<Player> tableViewInbox;
    @FXML
    private MenuItem menuItemSendMessage;
    @FXML
    private Button buttonBackTeamMain;
    private final PlayerController playerController = new PlayerControllerImpl();
    private Team team = new Team();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI();
    }

    /**
     * UI elemek létrehozása és/vagy konfigurálása.
     */
    private void loadUI() {
        menuItemSendMessage.setOnAction(event -> {
            sendMessage(team);
        });

        TableColumn<Player, String> nameSentCol = new TableColumn<>("Név");
        nameSentCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        nameSentCol.setPrefWidth(150);
        TableColumn<Player, String> messageTitleSentCol = new TableColumn<>("Levél tárgya");
        messageTitleSentCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMessage().getTitle()));
        TableColumn<Player, Void> actionsSentCol  = new TableColumn<>("Levél megtekintése");
        actionsSentCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonManage = new Button("Megtekintés");
                {
                    buttonManage.getStyleClass().add("main-btn");

                    buttonManage.setOnAction(event -> {
                        Player player = getTableView().getItems().get(getIndex());
                        checkMessage(player.getMessage());
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.setSpacing(10);
                        container.getChildren().addAll(buttonManage);
                        setGraphic(container);
                    }
                }
            };
        });

        TableColumn<Player, String> nameReceivedCol = new TableColumn<>("Név");
        nameReceivedCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        nameReceivedCol.setPrefWidth(150);
        TableColumn<Player, String> messageTitleReceivedCol = new TableColumn<>("Levél tárgya");
        messageTitleReceivedCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMessage().getTitle()));
        TableColumn<Player, Void> actionsReceivedCol  = new TableColumn<>("Levél megtekintése");
        actionsReceivedCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonManage = new Button("Megtekintés");
                {
                    buttonManage.getStyleClass().add("main-btn");

                    buttonManage.setOnAction(event -> {
                        Player player = getTableView().getItems().get(getIndex());
                        checkMessage(player.getMessage());
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();
                        container.setSpacing(10);
                        container.getChildren().addAll(buttonManage);
                        setGraphic(container);
                    }
                }
            };
        });
        tableViewSent.getColumns().addAll(nameSentCol, messageTitleSentCol, actionsSentCol);
        tableViewInbox.getColumns().addAll(nameReceivedCol, messageTitleReceivedCol, actionsReceivedCol);
    }

    /**
     * Üzenet küldő ablak megnyitása.
     */
    private void sendMessage(Team team) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/SendMessage.fxml"));
            Parent root = loader.load();
            SendMessageViewController controller = loader.getController();
            controller.initTeam(team);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Üzenet küldése");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            refreshTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Táblázat adatainak frissítése.
     */
    private void refreshTables() {
        tableViewSent.getItems().clear();
        tableViewInbox.getItems().clear();
        tableViewSent.setItems(FXCollections.observableArrayList(playerController.getMessageSenderPlayers(team.getId())));
        tableViewInbox.setItems(FXCollections.observableArrayList(playerController.getMessageReceiverPlayers(team.getId())));
        tableViewSent.refresh();
        tableViewInbox.refresh();
    }

    /**
     * Üzenet megtekintő ablak megnyitása.
     */
    private void checkMessage(Message message) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/SendMessage.fxml"));
            Parent root = loader.load();
            SendMessageViewController controller = loader.getController();
            SendMessageViewController.viewMessageStart();
            controller.initMessageForViewMessage(message);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Üzenet megtekintése");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            SendMessageViewController.viewMessageEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vissza a csapat menedzselés kezdőlapjára.
     */
    @FXML
    public void loadBackTeamMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/TeamMain.fxml"));
            Parent root = loader.load();
            TeamMainViewController controller = loader.getController();
            controller.initTeam(team);
            Scene scene = buttonBackTeamMain.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Átadott csapat adatainak betöltése használathoz és táblázat feltöltése adatokkal.
     */
    public void initTeam(Team team) {
        team.copyTo(this.team);
        refreshTables();
    }
}
