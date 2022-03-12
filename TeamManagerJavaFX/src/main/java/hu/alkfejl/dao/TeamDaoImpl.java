package hu.alkfejl.dao;

import hu.alkfejl.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao megvalósítás csapatok adatainak menedzselésére adatbázissal.
 */
public class TeamDaoImpl implements TeamDao {

    private static final String SELECT_TEAM = "SELECT * FROM teams WHERE id = ?;";
    private static final String SELECT_ALL_TEAMS = "SELECT * FROM teams;";
    private final PlayerDao playerDao = new PlayerDaoImpl();

    public TeamDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Egy csapat kinyerése adatbázisból, id alapján.
     */
    @Override
    public Team getTeam(int teamId) {
        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_TEAM)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            List<Team> teams = readTeamsFromResultSet(rs);
            if (teams.size() > 0) {
                return teams.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Összes csapat kinyerése adatbázisból.
     */
    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL_TEAMS);
            teams = readTeamsFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    /**
     * Csapatok adatainak kinyerése adatbázisból.
     */
    private List<Team> readTeamsFromResultSet(ResultSet rs) throws SQLException {
        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team team = new Team(
                    rs.getInt(1),
                    rs.getString(2),
                    playerDao.getAllPlayersByTeam(rs.getInt(1))
            );
            teams.add(team);
        }
        return teams;
    }
}
