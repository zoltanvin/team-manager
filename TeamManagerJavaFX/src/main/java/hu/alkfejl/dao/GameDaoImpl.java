package hu.alkfejl.dao;

import hu.alkfejl.model.Game;
import hu.alkfejl.model.Player;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao megvalósítás mérkőzések adatainak menedzselésére adatbázissal.
 */
public class GameDaoImpl implements GameDao {

    private static final String INSERT_GAME = "INSERT INTO games (home_team_id, away_team_id, date, referee_name, location, home_score, away_score, away_team_travel_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_GAME_INFO = "INSERT INTO game_info (game_id, home_team_id, away_team_id, player_id, score) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_GAMES_BY_TEAM = "SELECT * FROM games WHERE home_team_id = ? OR away_team_id = ?;";
    private static final String SELECT_ALL_HOME_GAMES_BY_TEAM = "SELECT * FROM games WHERE home_team_id = ?;";
    private static final String SELECT_ALL_AWAY_GAMES_BY_TEAM = "SELECT * FROM games WHERE away_team_id = ?;";
    private final TeamDao teamDao = new TeamDaoImpl();
    private final TravelDao travelDao = new TravelDaoImpl();
    private final PlayerDao playerDao = new PlayerDaoImpl();

    public GameDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Új mérkőzés beszúrása adatbázisba.
     */
    @Override
    public boolean addGame(Game game) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        try (Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING, config.toProperties());
             PreparedStatement ps = conn.prepareStatement(INSERT_GAME)) {
            ps.setInt(1, game.getHomeTeam().getId());
            ps.setInt(2, game.getAwayTeam().getId());
            ps.setString(3, game.getGameDate());
            ps.setString(4, game.getRefereeName());
            ps.setString(5, game.getLocation());
            ps.setInt(6, game.getHomeScore());
            ps.setInt(7, game.getAwayScore());
            ps.setInt(8, game.getAwayTeamTravelInfo().getId());

            int res = ps.executeUpdate();
            if (res == 1) {
                game.setId(ps.getGeneratedKeys().getInt(1));
                addGameInfos(game);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Új mérkőzés aktív és gólszerző játékosainak beszúrása listából.
     */
    private boolean addGameInfos(Game game) {
        boolean success = true;
        for (Player player : game.getHomeTeam().getPlayers()) {
            if (player.isActive()) {
                if (!addGameInfo(game, player)) {
                    success = false;
                    break;
                }
            }
        }
        for (Player player : game.getAwayTeam().getPlayers()) {
            if (player.isActive()) {
                if (!addGameInfo(game, player)) {
                    success = false;
                    break;
                }
            }
        }
        return success;
    }

    /**
     * Új mérkőzés beszúrásakor a mérkőzés aktív és gólszerző játékosainak beszúrása adatbázisba.
     */
    private boolean addGameInfo(Game game, Player player) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        try (Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING, config.toProperties());
             PreparedStatement ps = conn.prepareStatement(INSERT_GAME_INFO)) {
            ps.setInt(1, game.getId());
            ps.setInt(2, game.getHomeTeam().getId());
            ps.setInt(3, game.getAwayTeam().getId());
            ps.setInt(4, player.getId());
            ps.setInt(5, player.getGameScore());

            int res = ps.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Összes mérkőzés kinyerése adatbázisból, csapat id alapján.
     */
    @Override
    public List<Game> getAllGamesByTeam(int teamId) {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_GAMES_BY_TEAM)) {
            ps.setInt(1, teamId);
            ps.setInt(2, teamId);
            ResultSet rs = ps.executeQuery();
            games = readGamesFromResultSet(rs, teamId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    /**
     * Összes hazai mérkőzés kinyerése adatbázisból, csapat id alapján.
     */
    @Override
    public List<Game> getAllHomeGamesByTeam(int teamId) {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_HOME_GAMES_BY_TEAM)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            games = readGamesFromResultSet(rs, teamId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    /**
     * Összes idegen mérkőzés kinyerése adatbázisból, csapat id alapján.
     */
    @Override
    public List<Game> getAllAwayGamesByTeam(int teamId) {
        List<Game> games = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_AWAY_GAMES_BY_TEAM)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            games = readGamesFromResultSet(rs, teamId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    /**
     * Mérkőzések adatainak kinyerése adatbázisból.
     */
    private List<Game> readGamesFromResultSet(ResultSet rs, int teamId) throws SQLException {
        List<Game> games = new ArrayList<>();
        while (rs.next()) {
            Game game = new Game(
                    rs.getInt(1),
                    teamDao.getTeam(rs.getInt(2)),
                    teamDao.getTeam(rs.getInt(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    travelDao.getTravel(rs.getInt(9)),
                    playerDao.getAllActivePlayersByTeamAndGame(teamId, rs.getInt(1))
            );
            games.add(game);
        }
        return games;
    }
}
