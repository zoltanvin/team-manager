package hu.alkfejl.view.controller;

import hu.alkfejl.controller.PlayerController;
import hu.alkfejl.controller.PlayerControllerImpl;
import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import hu.alkfejl.utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Üzenet küldő felület.
 */
public class SendMessageViewController implements Initializable {

    @FXML
    private ComboBox<Player> comboBoxSenderPlayer;
    @FXML
    private ComboBox<Player> comboBoxReceiverPlayer;
    @FXML
    private TextField textFieldMessageTitle;
    @FXML
    private TextArea textAreaMessageContent;
    @FXML
    private Button buttonSendMessage;
    @FXML
    private Button buttonCancel;
    private Team team = new Team();
    private Message message = new Message();
    private final PlayerController playerController = new PlayerControllerImpl();
    private static boolean viewMessage = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI();
    }

    /**
     * Üzenet küldése és validációk.
     */
    @FXML
    public void sendMessage(ActionEvent event) {
        if (!textFieldMessageTitle.getText().isEmpty() && !textAreaMessageContent.getText().isEmpty() && !viewMessage) {
            if (!comboBoxSenderPlayer.getSelectionModel().getSelectedItem().getName().equals(comboBoxReceiverPlayer.getSelectionModel().getSelectedItem().getName())) {
                Message message = new Message(
                        comboBoxSenderPlayer.getSelectionModel().getSelectedItem(),
                        comboBoxReceiverPlayer.getSelectionModel().getSelectedItem(),
                        textFieldMessageTitle.getText(),
                        textAreaMessageContent.getText()
                );
                boolean result = playerController.sendMessage(message);
                if (result) {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                } else {
                    Utils.showWarning("Nem sikerült a mentés!");
                }
            } else {
                Utils.showWarning("A feladó és címzett nem lehet ugyan az a személy!");
            }
        } else if (viewMessage) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Utils.showWarning("A tárgy és tartalom nem lehet üres!");
        }
    }

    /**
     * Üzenet küldő ablak bezárása.
     */
    @FXML
    public void closeSendMessage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * UI elemek feltöltése.
     */
    private void loadUI() {
        comboBoxSenderPlayer.setCellFactory(param -> new ListCell<Player>() {
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
        comboBoxSenderPlayer.setButtonCell(comboBoxSenderPlayer.getCellFactory().call(null));
        comboBoxReceiverPlayer.setCellFactory(param -> new ListCell<Player>() {
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
        comboBoxReceiverPlayer.setButtonCell(comboBoxReceiverPlayer.getCellFactory().call(null));
    }

    /**
     * Átadott csapat adatainak betöltése használathoz és comboboxok feltöltése adatokkal.
     */
    public void initTeam(Team team) {
        team.copyTo(this.team);
        comboBoxSenderPlayer.setItems(team.getPlayers());
        comboBoxSenderPlayer.getSelectionModel().selectFirst();
        comboBoxReceiverPlayer.setItems(team.getPlayers());
        comboBoxReceiverPlayer.getSelectionModel().selectFirst();
    }

    /**
     * Átadott üzenet adatainak betöltése használathoz és UI elemek feltöltése az üzenet adataival.
     */
    public void initMessageForViewMessage(Message message) {
        message.copyTo(this.message);
        comboBoxSenderPlayer.setItems(FXCollections.observableArrayList(message.getSenderPlayer()));
        comboBoxSenderPlayer.getSelectionModel().selectFirst();
        comboBoxReceiverPlayer.setItems(FXCollections.observableArrayList(message.getReceiverPlayer()));
        comboBoxReceiverPlayer.getSelectionModel().selectFirst();
        textFieldMessageTitle.setText(message.getTitle());
        textFieldMessageTitle.setEditable(false);
        textAreaMessageContent.setText(message.getMessageContent());
        textAreaMessageContent.setEditable(false);
        buttonSendMessage.setText("OK");
    }

    public static void viewMessageStart() {
        viewMessage = true;
    }

    public static void viewMessageEnd() {
        viewMessage = false;
    }
}
