package hu.alkfejl.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Game {
    private IntegerProperty id = new SimpleIntegerProperty();
    private Team homeTeam;
    private Team awayTeam;
    private StringProperty gameDate = new SimpleStringProperty();
    private StringProperty refereeName = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private IntegerProperty homeScore = new SimpleIntegerProperty();
    private IntegerProperty awayScore = new SimpleIntegerProperty();
    private Travel awayTeamTravelInfo;
    private ListProperty<Player> activePlayersByTeam = new SimpleListProperty<>();

    public Game() {
    }

    public Game(int id, Team homeTeam, Team awayTeam, String gameDate, String refereeName, String location, int homeScore, int awayScore, Travel awayTeamTravelInfo, List<Player> activePlayersByTeam) {
        this.id.set(id);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameDate.set(gameDate);
        this.refereeName.set(refereeName);
        this.location.set(location);
        this.homeScore.set(homeScore);
        this.awayScore.set(awayScore);
        this.awayTeamTravelInfo = awayTeamTravelInfo;
        this.activePlayersByTeam.set(FXCollections.observableArrayList(activePlayersByTeam));
    }

    public Game(Team homeTeam, Team awayTeam, String gameDate, String refereeName, String location, int homeScore, int awayScore, Travel awayTeamTravelInfo) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameDate.set(gameDate);
        this.refereeName.set(refereeName);
        this.location.set(location);
        this.homeScore.set(homeScore);
        this.awayScore.set(awayScore);
        this.awayTeamTravelInfo = awayTeamTravelInfo;
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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getGameDate() {
        return gameDate.get();
    }

    public StringProperty gameDateProperty() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate.set(gameDate);
    }

    public String getRefereeName() {
        return refereeName.get();
    }

    public StringProperty refereeNameProperty() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName.set(refereeName);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public int getHomeScore() {
        return homeScore.get();
    }

    public IntegerProperty homeScoreProperty() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore.set(homeScore);
    }

    public int getAwayScore() {
        return awayScore.get();
    }

    public IntegerProperty awayScoreProperty() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore.set(awayScore);
    }

    public Travel getAwayTeamTravelInfo() {
        return awayTeamTravelInfo;
    }

    public void setAwayTeamTravelInfo(Travel awayTeamTravelInfo) {
        this.awayTeamTravelInfo = awayTeamTravelInfo;
    }

    public ObservableList<Player> getActivePlayersByTeam() {
        return activePlayersByTeam.get();
    }

    public ListProperty<Player> activePlayersByTeamProperty() {
        return activePlayersByTeam;
    }

    public void setActivePlayersByTeam(ObservableList<Player> activePlayersByTeam) {
        this.activePlayersByTeam.set(activePlayersByTeam);
    }
}
