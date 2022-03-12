package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.List;

public class Travel {
    private IntegerProperty id = new SimpleIntegerProperty();
    private Team team;
    private String startDate;
    private String arriveDate;
    private StringProperty type = new SimpleStringProperty();

    public static final List<String> TRAVEL_TYPES = Arrays.asList("Autó", "Busz", "Hajó", "Repülő", "Vonat");

    public Travel() {
    }

    public Travel(int id, Team team, String startDate, String arriveDate, String type) {
        this.id.set(id);
        this.team = team;
        this.startDate = startDate;
        this.arriveDate = arriveDate;
        this.type.set(type);
    }

    public Travel(Team team, String startDate, String arriveDate, String type) {
        this.team = team;
        this.startDate = startDate;
        this.arriveDate = arriveDate;
        this.type.set(type);
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
