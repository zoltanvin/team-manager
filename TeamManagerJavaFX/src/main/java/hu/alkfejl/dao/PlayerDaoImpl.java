package hu.alkfejl.dao;

import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao megvalósítás játékosok adatainak menedzselésére adatbázissal.
 */
public class PlayerDaoImpl implements PlayerDao {

    private static final String INSERT_MESSAGE = "INSERT INTO message_player (sender_player_id, receiver_player_id, title, message) VALUES (?, ?, ?, ?);";
    private static final String SELECT_MESSAGE = "SELECT * FROM message_player WHERE id = ?;";
    private static final String SELECT_MESSAGE_SENDER_PLAYERS = "SELECT p.id, p.name, p.position, p.birth_year, mp.id FROM players AS p JOIN message_player as mp ON (mp.sender_player_id = p.id) WHERE p.team_id = ? ORDER BY p.name;";
    private static final String SELECT_MESSAGE_RECEIVER_PLAYERS = "SELECT p.id, p.name, p.position, p.birth_year, mp.id FROM players AS p JOIN message_player as mp ON (mp.receiver_player_id = p.id) WHERE p.team_id = ? ORDER BY p.name;";
    private static final String SELECT_PLAYER = "SELECT * FROM players WHERE id = ?;";
    private static final String SELECT_ALL_PLAYERS_BY_TEAM = "SELECT * FROM players WHERE team_id = ? ORDER BY name;";
    private static final String SELECT_ACTIVE_PLAYERS_BY_TEAM_AND_GAME = "SELECT p.id, p.name, p.position, p.birth_year, gi.score FROM players AS p JOIN game_info AS gi ON (gi.player_id = p.id) WHERE p.team_id = ? AND gi.game_id = ?;";;

    public PlayerDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Egy játékos kinyerése adatbázisból, id alapján.
     */
    @Override
    public Player getPlayer(int playerId) {
        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_PLAYER)) {
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();
            List<Player> players = readPlayersFromResultSet(rs);
            if (players.size() > 0) {
                return players.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adott csapat játékosainak kinyerése adatbázisból, csapat id alapján.
     */
    @Override
    public List<Player> getAllPlayersByTeam(int teamId) {
        List<Player> players = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PLAYERS_BY_TEAM)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            players = readPlayersFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Üzenet beszúrása adatbázisba.
     */
    @Override
    public boolean sendMessage(Message message) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING, config.toProperties());
            PreparedStatement ps = conn.prepareStatement(INSERT_MESSAGE)){
            ps.setInt(1, message.getSenderPlayer().getId());
            ps.setInt(2, message.getReceiverPlayer().getId());
            ps.setString(3, message.getTitle());
            ps.setString(4, message.getMessageContent());

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
     * Üzenet kinyerése adatbázisból, id alapján.
     */
    @Override
    public Message getMessage(int messageId) {
        Message message = null;

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_MESSAGE)) {
            ps.setInt(1, messageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                message = new Message(
                        rs.getInt(1),
                        getPlayer(rs.getInt(2)),
                        getPlayer(rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * Üzenetet küldő játékosok kinyerése adatbázisból, csapat id alapján.
     */
    @Override
    public List<Player> getMessageSenderPlayers(int teamId) {
        List<Player> players = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_MESSAGE_SENDER_PLAYERS)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                player.setMessage(getMessage(rs.getInt(5)));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Üzenetet kapott játékosok kinyerése adatbázisból, csapat id alapján.
     */
    @Override
    public List<Player> getMessageReceiverPlayers(int teamId) {
        List<Player> players = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_MESSAGE_RECEIVER_PLAYERS)) {
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                player.setMessage(getMessage(rs.getInt(5)));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Adott mérkőzés játékosainak kinyerése adatbázisból, csapat és mérkőzés id alapján.
     */
    @Override
    public List<Player> getAllActivePlayersByTeamAndGame(int teamId, int gameId) {
        List<Player> players = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_ACTIVE_PLAYERS_BY_TEAM_AND_GAME)) {
            ps.setInt(1, teamId);
            ps.setInt(2, gameId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Játékosok adatainak kinyerése adatbázisból.
     */
    private List<Player> readPlayersFromResultSet(ResultSet rs) throws SQLException {
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Player player = new Player(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            );
            players.add(player);
        }
        return players;
    }
}
