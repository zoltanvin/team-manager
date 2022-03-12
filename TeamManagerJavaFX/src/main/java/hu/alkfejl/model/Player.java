package hu.alkfejl.model;

import javafx.beans.property.*;

public class Player {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty position = new SimpleStringProperty();
    private IntegerProperty birthYear = new SimpleIntegerProperty();
    private IntegerProperty gameScore = new SimpleIntegerProperty();
    private BooleanProperty active = new SimpleBooleanProperty();
    private Message message;

    public Player() {
    }

    public Player(int id, String name, String position, int birthYear) {
        this.id.set(id);
        this.name.set(name);
        this.position.set(position);
        this.birthYear.set(birthYear);
        this.setActive(false);
    }

    public Player(int id, String name, String position, int birthYear, int score) {
        this.id.set(id);
        this.name.set(name);
        this.position.set(position);
        this.birthYear.set(birthYear);
        this.gameScore.set(score);
    }

    public Player(String name, String position, int birthYear) {
        this.name.set(name);
        this.position.set(position);
        this.birthYear.set(birthYear);
        this.setActive(false);
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

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public int getBirthYear() {
        return birthYear.get();
    }

    public IntegerProperty birthYearProperty() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear.set(birthYear);
    }

    public int getGameScore() {
        return gameScore.get();
    }

    public IntegerProperty gameScoreProperty() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore.set(gameScore);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
