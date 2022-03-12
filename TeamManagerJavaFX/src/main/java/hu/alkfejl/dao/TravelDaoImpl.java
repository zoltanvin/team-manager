package hu.alkfejl.dao;

import hu.alkfejl.model.Travel;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao megvalósítás utazási információ menedzselésére adatbázissal.
 */
public class TravelDaoImpl implements TravelDao {

    private static final String INSERT_TRAVEL = "INSERT INTO travels (team_id, start_date, arrive_date, type) VALUES (?, ?, ?, ?);";
    private static final String SELECT_TRAVEL = "SELECT * FROM travels WHERE id = ?;";
    private final TeamDao teamController = new TeamDaoImpl();

    public TravelDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Új utazási info beszúrása adatbázisba és a generált id-jének visszaadása.
     */
    @Override
    public int addTravel(Travel travel) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING, config.toProperties());
            PreparedStatement ps = conn.prepareStatement(INSERT_TRAVEL)){
            ps.setInt(1, travel.getTeam().getId());
            ps.setString(2, travel.getStartDate());
            ps.setString(3, travel.getArriveDate());
            ps.setString(4, travel.getType());

            int res = ps.executeUpdate();
            if (res == 1) {
                return ps.getGeneratedKeys().getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Egy utazási info kinyerése adatbázisból, id alapján.
     */
    @Override
    public Travel getTravel(int travelId) {
        try(Connection conn = DriverManager.getConnection(DBConfig.CONNECTION_STRING);
            PreparedStatement ps = conn.prepareStatement(SELECT_TRAVEL)) {
            ps.setInt(1, travelId);
            ResultSet rs = ps.executeQuery();
            List<Travel> travels = readTravelsFromResultSet(rs);
            if (travels.size() > 0) {
                return travels.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Utazási info adatainak kinyerése adatbázisból.
     */
    private List<Travel> readTravelsFromResultSet(ResultSet rs) throws SQLException {
        List<Travel> travels = new ArrayList<>();
        while (rs.next()) {
            Travel travel = new Travel(
                    rs.getInt(1),
                    teamController.getTeam(rs.getInt(2)),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            travels.add(travel);
        }
        return travels;
    }
}
