package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Team {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty playerCount = new SimpleIntegerProperty();
    private ListProperty<Player> players = new SimpleListProperty<>();
    private ListProperty<Player> scorers = new SimpleListProperty<>();
    private ListProperty<Game> allGames = new SimpleListProperty<>();
    private ListProperty<Game> homeGames = new SimpleListProperty<>();
    private ListProperty<Game> awayGames = new SimpleListProperty<>();

    public Team() {
    }

    public Team(int id, String name, List<Player> players) {
        this.id.set(id);
        this.name.set(name);
        this.players.set(FXCollections.observableArrayList(players));
        this.scorers.set(FXCollections.observableArrayList());
        this.playerCount.set(players.size());
    }

    public Team(String name, List<Player> players) {
        this.name.set(name);
        this.players.set(FXCollections.observableArrayList(players));
        this.scorers.set(FXCollections.observableArrayList());
        this.playerCount.set(players.size());
    }

    /**
     * Csapat adatainak átmásolása egy másik csapat objektumba.
     */
    public void copyTo(Team target) {
        target.setId(this.getId());
        target.setName(this.getName());
        target.setPlayers(this.getPlayers());
        target.setScorers(this.getScorers());
        target.setAllGames(this.getAllGames());
        target.setHomeGames(this.getHomeGames());
        target.setAwayGames(this.getAwayGames());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<Player> getPlayers() {
        return players.get();
    }

    public ListProperty<Player> playersProperty() {
        return players;
    }

    public void setPlayers(ObservableList<Player> players) {
        this.players.set(players);
    }

    public ObservableList<Player> getScorers() {
        return scorers.get();
    }

    public ListProperty<Player> scorersProperty() {
        return scorers;
    }

    public void setScorers(ObservableList<Player> scorers) {
        this.scorers.set(scorers);
    }

    public int getPlayerCount() {
        return playerCount.get();
    }

    public IntegerProperty playerCountProperty() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount.set(playerCount);
    }

    public ObservableList<Game> getAllGames() {
        return allGames.get();
    }

    public ListProperty<Game> allGamesProperty() {
        return allGames;
    }

    public void setAllGames(ObservableList<Game> allGames) {
        this.allGames.set(allGames);
    }

    public ObservableList<Game> getHomeGames() {
        return homeGames.get();
    }

    public ListProperty<Game> homeGamesProperty() {
        return homeGames;
    }

    public void setHomeGames(ObservableList<Game> homeGames) {
        this.homeGames.set(homeGames);
    }

    public ObservableList<Game> getAwayGames() {
        return awayGames.get();
    }

    public ListProperty<Game> awayGamesProperty() {
        return awayGames;
    }

    public void setAwayGames(ObservableList<Game> awayGames) {
        this.awayGames.set(awayGames);
    }
}
