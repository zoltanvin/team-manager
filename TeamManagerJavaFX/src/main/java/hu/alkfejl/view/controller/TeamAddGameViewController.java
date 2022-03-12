package hu.alkfejl.view.controller;

import hu.alkfejl.controller.*;
import hu.alkfejl.model.Game;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;
import hu.alkfejl.model.Travel;
import hu.alkfejl.utils.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Csapat mérkőzésének rögzítő felület.
 */
public class TeamAddGameViewController implements Initializable {

    @FXML
    private ComboBox<Team> comboBoxTeamHome;
    @FXML
    private ComboBox<Team> comboBoxTeamAway;
    @FXML
    private ComboBox<String> comboBoxAwayTeamTravelType;
    @FXML
    private Spinner<Integer> spinnerTeamHomeScore;
    @FXML
    private Spinner<Integer> spinnerTeamAwayScore;
    @FXML
    private TableView<Player> tableViewTeamHomeActivePlayers;
    @FXML
    private TableView<Player> tableViewTeamAwayActivePlayers;
    @FXML
    private TableView<Player> tableViewTeamHomeScorerPlayers;
    @FXML
    private TableView<Player> tableViewTeamAwayScorerPlayers;
    @FXML
    private TextField textFieldLocation;
    @FXML
    private TextField textFieldReferee;
    @FXML
    private DatePicker datePickerGameDate;
    @FXML
    private DatePicker datePickerAwayTeamTravelStartDate;
    @FXML
    private DatePicker datePickerAwayTeamTravelArriveDate;
    @FXML
    private Button buttonSaveGame;
    @FXML
    private Button buttonChangeTeam;
    @FXML
    private Button buttonCancel;

    private ObservableList<Team> teams = FXCollections.observableArrayList();
    private final TeamController teamController = new TeamControllerImpl();
    private final GameController gameController = new GameControllerImpl();
    private final TravelController travelController = new TravelControllerImpl();
    private Team team = new Team();
    private static boolean teamIsHome = true;
    private static boolean isChange = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI();
    }

    /**
     * Mérkőzés mentése és validációk.
     */
    @FXML
    private void saveGame(ActionEvent event) {
        Team homeTeam = comboBoxTeamHome.getSelectionModel().getSelectedItem();
        Team awayTeam = comboBoxTeamAway.getSelectionModel().getSelectedItem();

        if (!textFieldLocation.getText().isEmpty() && !textFieldReferee.getText().isEmpty() && datePickerGameDate.getValue() != null) {
            if (checkIfTeamHasActivePlayers(homeTeam) && checkIfTeamHasActivePlayers(awayTeam)) {
                if (datePickerAwayTeamTravelStartDate.getValue() != null && datePickerAwayTeamTravelArriveDate.getValue() != null) {
                    if (datePickerAwayTeamTravelStartDate.getValue().isBefore(datePickerAwayTeamTravelArriveDate.getValue()) || datePickerAwayTeamTravelStartDate.getValue().isEqual(datePickerAwayTeamTravelArriveDate.getValue())) {
                        if ((datePickerGameDate.getValue().isAfter(datePickerAwayTeamTravelStartDate.getValue()) || datePickerGameDate.getValue().isEqual(datePickerAwayTeamTravelStartDate.getValue()))
                                && (datePickerGameDate.getValue().isAfter(datePickerAwayTeamTravelArriveDate.getValue()) || datePickerGameDate.getValue().isEqual(datePickerAwayTeamTravelArriveDate.getValue()))) {
                            if (((countTeamGoals(homeTeam) == 0 && spinnerTeamHomeScore.getValue() == 0) || (checkIfTeamHasGoal(homeTeam) && spinnerTeamHomeScore.getValue() > 0))
                                    && ((countTeamGoals(awayTeam) == 0 && spinnerTeamAwayScore.getValue() == 0) || (checkIfTeamHasGoal(awayTeam) && spinnerTeamAwayScore.getValue() > 0))) {
                                if (countTeamGoals(homeTeam) == spinnerTeamHomeScore.getValue() && countTeamGoals(awayTeam) == spinnerTeamAwayScore.getValue()) {
                                    if (!checkIfTeamHasScorerWithZeroGoal(homeTeam) && !checkIfTeamHasScorerWithZeroGoal(awayTeam)) {
                                        Travel travelInfo = new Travel(
                                                awayTeam,
                                                datePickerAwayTeamTravelStartDate.getConverter().toString(datePickerAwayTeamTravelStartDate.getValue()),
                                                datePickerAwayTeamTravelArriveDate.getConverter().toString(datePickerAwayTeamTravelArriveDate.getValue()),
                                                comboBoxAwayTeamTravelType.getSelectionModel().getSelectedItem()
                                        );
                                        int resultTravel = travelController.addTravel(travelInfo);
                                        travelInfo.setId(resultTravel);

                                        Game game = new Game(
                                                homeTeam,
                                                awayTeam,
                                                datePickerGameDate.getConverter().toString(datePickerGameDate.getValue()),
                                                textFieldReferee.getText(),
                                                textFieldLocation.getText(),
                                                spinnerTeamHomeScore.getValue(),
                                                spinnerTeamAwayScore.getValue(),
                                                travelInfo
                                        );

                                        boolean resultGame = gameController.addGame(game);
                                        if (resultTravel != -1 && resultGame) {
                                            loadBackTeamMain();
                                        } else {
                                            Utils.showWarning("Nem sikerült a mentés!");
                                        }
                                    } else {
                                        Utils.showWarning("Nem lehet 0 gólos gólszerző!");
                                    }
                                } else {
                                    Utils.showWarning("Eltérés a gólszerzők góljainak száma és az eredmény között!");
                                }
                            } else {
                                Utils.showWarning("Nincs gól gólszerző nélkül! / Ne csak a gólszerzőnél adja meg a gólt!");
                            }
                        } else {
                            Utils.showWarning("Az utazás időpontjai nem lehetnek a mérkőzés időpontja után!");
                        }
                    } else {
                        Utils.showWarning("Az utazás érkezési időpontja nem lehet az indulási időpont előtt!");
                    }
                } else {
                    Utils.showWarning("Az utazás indulási és érkezési időpontja nem lehet üres!");
                }
            } else {
                Utils.showWarning("Kötelező mindkét csapatnak játszó játékost megadni!");
            }
        } else {
            Utils.showWarning("A helyszín, bíró és időpont nem lehet üres!");
        }
    }

    /**
     * Hazai és idegen csapat felcserélése.
     */
    @FXML
    private void changeTeam(ActionEvent event) {
        isChange = true;
        if (teamIsHome) {
            comboBoxTeamAway.valueProperty().set(null);
            comboBoxTeamHome.valueProperty().set(null);
            comboBoxTeamAway.setItems(FXCollections.observableArrayList(team));
            comboBoxTeamHome.setItems(teams);
            tableViewTeamHomeActivePlayers.refresh();
            tableViewTeamHomeScorerPlayers.refresh();
            tableViewTeamAwayActivePlayers.refresh();
            tableViewTeamAwayScorerPlayers.refresh();
            comboBoxTeamAway.getSelectionModel().selectFirst();
            comboBoxTeamHome.getSelectionModel().selectFirst();
            teamIsHome = false;
        } else {
            comboBoxTeamAway.valueProperty().set(null);
            comboBoxTeamHome.valueProperty().set(null);
            comboBoxTeamHome.setItems(FXCollections.observableArrayList(team));
            comboBoxTeamAway.setItems(teams);
            tableViewTeamHomeActivePlayers.refresh();
            tableViewTeamHomeScorerPlayers.refresh();
            tableViewTeamAwayActivePlayers.refresh();
            tableViewTeamAwayScorerPlayers.refresh();
            comboBoxTeamHome.getSelectionModel().selectFirst();
            comboBoxTeamAway.getSelectionModel().selectFirst();
            teamIsHome = true;
        }
        isChange = false;
    }

    /**
     * UI elemek létrehozása, konfigurálása és feltöltése.
     */
    private void loadUI() {
        comboBoxAwayTeamTravelType.setItems(FXCollections.observableArrayList(Travel.TRAVEL_TYPES));
        comboBoxAwayTeamTravelType.getSelectionModel().selectFirst();
        comboBoxTeamHome.setCellFactory(param -> new ListCell<Team>() {
            @Override
            protected void updateItem(Team team, boolean empty) {
                super.updateItem(team, empty);
                if (empty || team == null || team.getName() == null) {
                    setText(null);
                } else {
                    setText(team.getName());
                }
            }
        });
        comboBoxTeamHome.setButtonCell(comboBoxTeamHome.getCellFactory().call(null));
        comboBoxTeamAway.setCellFactory(param -> new ListCell<Team>() {
            @Override
            protected void updateItem(Team team, boolean empty) {
                super.updateItem(team, empty);
                if (empty || team == null || team.getName() == null) {
                    setText(null);
                } else {
                    setText(team.getName());
                }
            }
        });
        comboBoxTeamAway.setButtonCell(comboBoxTeamAway.getCellFactory().call(null));
        comboBoxTeamHome.valueProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observableValue, Team oldValue, Team newValue) {
                if (oldValue != null && !isChange) {
                    resetTeamPlayers(oldValue);
                }
                if (newValue != null) {
                    tableViewTeamHomeActivePlayers.setItems(newValue.getPlayers());
                    tableViewTeamHomeScorerPlayers.setItems(newValue.getScorers());
                }
            }
        });
        comboBoxTeamAway.valueProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observableValue, Team oldValue, Team newValue) {
                if (oldValue != null && !isChange) {
                    resetTeamPlayers(oldValue);
                }
                if (newValue != null) {
                    tableViewTeamAwayActivePlayers.setItems(newValue.getPlayers());
                    tableViewTeamAwayScorerPlayers.setItems(newValue.getScorers());
                }
            }
        });

        spinnerTeamHomeScore.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0, 1));
        spinnerTeamAwayScore.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0, 1));

        TableColumn<Player, String> nameActivePlayersHomeCol = new TableColumn<>("Név");
        nameActivePlayersHomeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameActivePlayersHomeCol.setEditable(false);
        nameActivePlayersHomeCol.setPrefWidth(130);
        TableColumn<Player, Boolean> isActiveActivePlayersHomeCol = new TableColumn<>("Aktív?");
        isActiveActivePlayersHomeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        isActiveActivePlayersHomeCol.setCellFactory(CheckBoxTableCell.forTableColumn(isActiveActivePlayersHomeCol));
        isActiveActivePlayersHomeCol.setPrefWidth(50);
        TableColumn<Player, Void> addScorerHomeActionCol = new TableColumn<>("Hozzáad");
        addScorerHomeActionCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonAddScorer = new Button("Gól");

                {
                    buttonAddScorer.getStyleClass().add("main-btn");

                    buttonAddScorer.setOnAction(event -> {
                        Player scorer = getTableView().getItems().get(getIndex());
                        if (scorer.isActive()) {
                            addAndRefreshHomeScorerTable(scorer);
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(buttonAddScorer);
                    }
                }
            };
        });
        addScorerHomeActionCol.setPrefWidth(60);

        TableColumn<Player, String> nameActivePlayersAwayCol = new TableColumn<>("Név");
        nameActivePlayersAwayCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameActivePlayersAwayCol.setEditable(false);
        nameActivePlayersAwayCol.setPrefWidth(130);
        TableColumn<Player, Boolean> isActiveActivePlayersAwayCol = new TableColumn<>("Aktív?");
        isActiveActivePlayersAwayCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        isActiveActivePlayersAwayCol.setCellFactory(CheckBoxTableCell.forTableColumn(isActiveActivePlayersAwayCol));
        isActiveActivePlayersAwayCol.setPrefWidth(50);
        TableColumn<Player, Void> addScorerAwayActionCol = new TableColumn<>("Hozzáad");
        addScorerAwayActionCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonAddScorer = new Button("Gól");

                {
                    buttonAddScorer.getStyleClass().add("main-btn");

                    buttonAddScorer.setOnAction(event -> {
                        Player scorer = getTableView().getItems().get(getIndex());
                        if (scorer.isActive()) {
                            addAndRefreshAwayScorerTable(scorer);
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(buttonAddScorer);
                    }
                }
            };
        });
        addScorerAwayActionCol.setPrefWidth(60);

        TableColumn<Player, String> nameScorerPlayersHomeCol = new TableColumn<>("Név");
        nameScorerPlayersHomeCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameScorerPlayersHomeCol.setEditable(false);
        nameScorerPlayersHomeCol.setPrefWidth(130);
        TableColumn<Player, Integer> scoreScorerPlayersHomeCol = new TableColumn<>("Gól szám");
        scoreScorerPlayersHomeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        scoreScorerPlayersHomeCol.setCellValueFactory(new PropertyValueFactory<>("gameScore"));
        scoreScorerPlayersHomeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Player, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, Integer> playerCell) {
                Player actualPlayer = (Player) playerCell.getTableView().getItems().get(playerCell.getTablePosition().getRow());
                actualPlayer.setGameScore(playerCell.getNewValue());
            }
        });
        scoreScorerPlayersHomeCol.setPrefWidth(60);
        TableColumn<Player, Void> removeScorerHomeActionCol = new TableColumn<>("Töröl");
        removeScorerHomeActionCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonRemoveScorer = new Button("X");

                {
                    buttonRemoveScorer.getStyleClass().add("main-btn");

                    buttonRemoveScorer.setOnAction(event -> {
                        Player scorer = getTableView().getItems().get(getIndex());
                        removeAndRefreshHomeScorerTable(scorer);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(buttonRemoveScorer);
                    }
                }
            };
        });
        removeScorerHomeActionCol.setPrefWidth(50);

        TableColumn<Player, String> nameScorerPlayersAwayCol = new TableColumn<>("Név");
        nameScorerPlayersAwayCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameScorerPlayersAwayCol.setEditable(false);
        nameScorerPlayersAwayCol.setPrefWidth(130);
        TableColumn<Player, Integer> scoreScorerPlayersAwayCol = new TableColumn<>("Gól szám");
        scoreScorerPlayersAwayCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        scoreScorerPlayersAwayCol.setCellValueFactory(new PropertyValueFactory<>("gameScore"));
        scoreScorerPlayersAwayCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Player, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, Integer> playerCell) {
                Player actualPlayer = (Player) playerCell.getTableView().getItems().get(playerCell.getTablePosition().getRow());
                actualPlayer.setGameScore(playerCell.getNewValue());
            }
        });
        scoreScorerPlayersAwayCol.setPrefWidth(60);
        TableColumn<Player, Void> removeScorerAwayActionCol = new TableColumn<>("Töröl");
        removeScorerAwayActionCol.setCellFactory(param -> {
            return new TableCell<>() {
                private final Button buttonRemoveScorer = new Button("X");

                {
                    buttonRemoveScorer.getStyleClass().add("main-btn");

                    buttonRemoveScorer.setOnAction(event -> {
                        Player scorer = getTableView().getItems().get(getIndex());
                        removeAndRefreshAwayScorerTable(scorer);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(buttonRemoveScorer);
                    }
                }
            };
        });
        removeScorerAwayActionCol.setPrefWidth(50);

        tableViewTeamHomeActivePlayers.getColumns().addAll(nameActivePlayersHomeCol, isActiveActivePlayersHomeCol, addScorerHomeActionCol);
        tableViewTeamAwayActivePlayers.getColumns().addAll(nameActivePlayersAwayCol, isActiveActivePlayersAwayCol, addScorerAwayActionCol);
        tableViewTeamHomeScorerPlayers.getColumns().addAll(nameScorerPlayersHomeCol, scoreScorerPlayersHomeCol, removeScorerHomeActionCol);
        tableViewTeamAwayScorerPlayers.getColumns().addAll(nameScorerPlayersAwayCol, scoreScorerPlayersAwayCol, removeScorerAwayActionCol);
    }

    /**
     * Vissza a csapat menedzselés kezdőlapjára.
     */
    @FXML
    private void closeTeamAddGame(ActionEvent event) {
        loadBackTeamMain();
    }

    /**
     * Vissza a csapat menedzselés kezdőlapjára.
     */
    private void loadBackTeamMain() {
        teamIsHome = true;
        resetTeamPlayers(comboBoxTeamHome.getSelectionModel().getSelectedItem());
        resetTeamPlayers(comboBoxTeamAway.getSelectionModel().getSelectedItem());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/TeamMain.fxml"));
            Parent root = loader.load();
            TeamMainViewController controller = loader.getController();
            controller.initTeam(team);
            Scene scene = buttonCancel.getScene();
            StackPane parentContainer = (StackPane) scene.getRoot();

            parentContainer.getChildren().clear();
            parentContainer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Csapat játékosainak aktivitása false-ra, gólok számainak pedig 0-ra állítása.
     */
    private void resetTeamPlayers(Team team) {
        for (Player player : team.getPlayers()) {
            player.setActive(false);
            player.setGameScore(0);
        }
        team.getScorers().clear();
    }

    /**
     * Csapat góljainak számolása.
     */
    private int countTeamGoals(Team team) {
        int sum = 0;
        for (Player scorer : team.getScorers()) {
            sum += scorer.getGameScore();
        }
        return sum;
    }

    /**
     * Ellenőrzés, hogy csapaton belül van e aktív játékos.
     */
    private boolean checkIfTeamHasActivePlayers(Team team) {
        for (Player player : team.getPlayers()) {
            if (player.isActive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ellenőrzés, hogy a csapatnak van e gólja.
     */
    private boolean checkIfTeamHasGoal(Team team) {
        for (Player player : team.getPlayers()) {
            if (player.getGameScore() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ellenőrzés, hogy van e 0 gólos gólszerző.
     */
    private boolean checkIfTeamHasScorerWithZeroGoal(Team team) {
        for (Player player : team.getScorers()) {
            if (player.getGameScore() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Játékos hozzáadása hazai gólszerzők táblázathoz, majd táblázat frissítése.
     */
    private void addAndRefreshHomeScorerTable(Player scorer) {
        Team homeTeam = comboBoxTeamHome.getSelectionModel().getSelectedItem();
        if (!homeTeam.getScorers().contains(scorer)) {
            homeTeam.getScorers().add(scorer);
        }
        tableViewTeamHomeScorerPlayers.refresh();
    }

    /**
     * Játékos törlése hazai gólszerzők táblázatból, majd táblázat frissítése.
     */
    private void removeAndRefreshHomeScorerTable(Player scorer) {
        Team homeTeam = comboBoxTeamHome.getSelectionModel().getSelectedItem();
        if (homeTeam.getScorers().contains(scorer)) {
            homeTeam.getScorers().remove(scorer);
        }
        tableViewTeamHomeScorerPlayers.refresh();
    }

    /**
     * Játékos hozzáadása idegen gólszerzők táblázathoz, majd táblázat frissítése.
     */
    private void addAndRefreshAwayScorerTable(Player scorer) {
        Team awayTeam = comboBoxTeamAway.getSelectionModel().getSelectedItem();
        if (!awayTeam.getScorers().contains(scorer)) {
            awayTeam.getScorers().add(scorer);
        }
        tableViewTeamAwayScorerPlayers.refresh();
    }

    /**
     * Játékos törlése idegen gólszerzők táblázatból, majd táblázat frissítése.
     */
    private void removeAndRefreshAwayScorerTable(Player scorer) {
        Team awayTeam = comboBoxTeamAway.getSelectionModel().getSelectedItem();
        if (awayTeam.getScorers().contains(scorer)) {
            awayTeam.getScorers().remove(scorer);
        }
        tableViewTeamHomeScorerPlayers.refresh();
    }

    /**
     * Átadott csapat adatainak betöltése használathoz és comboboxok feltöltése adatokkal.
     */
    public void initTeam(Team team) {
        team.copyTo(this.team);
        List<Team> teamList = teamController.getAllTeams();
        for (Team teamCheck : teamList) {
            if (teamCheck.getId() == team.getId()) {
                teamList.remove(teamCheck);
                break;
            }
        }
        teams.addAll(teamList);
        comboBoxTeamHome.setItems(FXCollections.observableArrayList(team));
        comboBoxTeamHome.getSelectionModel().selectFirst();
        comboBoxTeamAway.setItems(teams);
        comboBoxTeamAway.getSelectionModel().selectFirst();
    }
}
