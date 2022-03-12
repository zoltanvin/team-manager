package hu.alkfejl.view.controller;

import hu.alkfejl.controller.GameController;
import hu.alkfejl.controller.GameControllerImpl;
import hu.alkfejl.controller.PlayerController;
import hu.alkfejl.controller.PlayerControllerImpl;
import hu.alkfejl.model.Game;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Csapat eredményei felület.
 */
public class TeamResultsViewController implements Initializable {

    @FXML
    private Label labelTravelStart;
    @FXML
    private Label labelTravelArrive;
    @FXML
    private Label labelTravelType;
    @FXML
    private Button buttonBackToTeamMain;
    @FXML
    private Button buttonResultsOverall;
    @FXML
    private Button buttonResultsHome;
    @FXML
    private Button buttonResultsAway;
    @FXML
    private TableView<Game> tableViewResults;
    @FXML
    private TableView<Player> tableViewScorers;
    @FXML
    private ListView<Player> listViewActivePlayers;
    private final GameController gameController = new GameControllerImpl();
    private final PlayerController playerController = new PlayerControllerImpl();
    private Team team = new Team();
    private static boolean isAllGame = true, isHomeGame = false, isAwayGame = false;
    private ObservableList scorerPlayers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI();
    }

    /**
     * Összesített mérkőzések betöltése a táblázatba.
     */
    @FXML
    public void loadAllGamesInTable(ActionEvent event) {
        if (!isAllGame) {
            isAllGame = true;
            isHomeGame = false;
            isAwayGame = false;
            tableViewResults.setItems(team.getAllGames());
            tableViewResults.refresh();
        }
    }

    /**
     * Hazai mérkőzések betöltése a táblázatba.
     */
    @FXML
    public void loadHomeGamesInTable(ActionEvent event) {
        if (!isHomeGame) {
            isHomeGame = true;
            isAllGame = false;
            isAwayGame = false;
            tableViewResults.setItems(team.getHomeGames());
            tableViewResults.refresh();
        }
    }

    /**
     * Idegen mérkőzések betöltése a táblázatba.
     */
    @FXML
    public void loadAwayGamesInTable(ActionEvent event) {
        if (!isAwayGame) {
            isAwayGame = true;
            isHomeGame = false;
            isAllGame = false;
            tableViewResults.setItems(team.getAwayGames());
            tableViewResults.refresh();
        }
    }

    /**
     * UI elemek létrehozása, konfigurálása és feltöltése.
     */
    private void loadUI() {
        listViewActivePlayers.setCellFactory(param -> new ListCell<>() {
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

        TableColumn<Game, String> nameHomeTeamCol = new TableColumn<>("Hazai");
        nameHomeTeamCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHomeTeam().getName()));
        TableColumn<Game, String> nameAwayTeamCol = new TableColumn<>("Idegen");
        nameAwayTeamCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAwayTeam().getName()));
        TableColumn<Game, Integer> scoreHomeTeamCol = new TableColumn<>("Hazai gól");
        scoreHomeTeamCol.setCellValueFactory(new PropertyValueFactory<Game, Integer>("homeScore"));
        TableColumn<Game, Integer> scoreAwayTeamCol = new TableColumn<>("Idegen gól");
        scoreAwayTeamCol.setCellValueFactory(new PropertyValueFactory<Game, Integer>("awayScore"));
        TableColumn<Game, String> gameDateCol = new TableColumn<>("Dátum");
        gameDateCol.setCellValueFactory(new PropertyValueFactory<Game, String>("gameDate"));
        TableColumn<Game, String> locationCol = new TableColumn<>("Helyszín");
        locationCol.setCellValueFactory(new PropertyValueFactory<Game, String>("location"));
        TableColumn<Game, String> refereeCol = new TableColumn<>("Bíró");
        refereeCol.setCellValueFactory(new PropertyValueFactory<Game, String>("refereeName"));

        tableViewResults.setRowFactory(param -> {
            TableRow<Game> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!row.isEmpty()) {
                        Game game = row.getItem();
                        listViewActivePlayers.setItems(game.getActivePlayersByTeam());
                        scorerPlayers.clear();
                        for (Player scorer : game.getActivePlayersByTeam()) {
                            if (scorer.getGameScore() > 0) {
                                scorerPlayers.add(scorer);
                            }
                        }
                        tableViewScorers.setItems(scorerPlayers);
                        if (game.getAwayTeam().getName().equals(team.getName())) {
                            labelTravelStart.setText(game.getAwayTeamTravelInfo().getStartDate());
                            labelTravelArrive.setText(game.getAwayTeamTravelInfo().getArriveDate());
                            labelTravelType.setText(game.getAwayTeamTravelInfo().getType());
                        } else {
                            labelTravelStart.setText("-");
                            labelTravelArrive.setText("-");
                            labelTravelType.setText("-");
                        }
                    }
                }
            });
            return row;
        });

        TableColumn<Player, String> nameScorerPlayerCol = new TableColumn<>("Név");
        nameScorerPlayerCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameScorerPlayerCol.setPrefWidth(130);
        TableColumn<Player, Integer> playersScoreCol = new TableColumn<>("Gól");
        playersScoreCol.setCellValueFactory(new PropertyValueFactory<>("gameScore"));
        playersScoreCol.setPrefWidth(55);

        tableViewResults.getColumns().addAll(nameHomeTeamCol, nameAwayTeamCol, scoreHomeTeamCol, scoreAwayTeamCol, gameDateCol, locationCol, refereeCol);
        tableViewScorers.getColumns().addAll(nameScorerPlayerCol, playersScoreCol);
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
            Scene scene = buttonBackToTeamMain.getScene();
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
    public void initTeam(Team teamForInit) {
        teamForInit.copyTo(team);
        team.setAllGames(FXCollections.observableArrayList(gameController.getAllGamesByTeam(team.getId())));
        team.setHomeGames(FXCollections.observableArrayList(gameController.getAllHomeGamesByTeam(team.getId())));
        team.setAwayGames(FXCollections.observableArrayList(gameController.getAllAwayGamesByTeam(team.getId())));
        tableViewResults.setItems(team.getAllGames());
    }
}
