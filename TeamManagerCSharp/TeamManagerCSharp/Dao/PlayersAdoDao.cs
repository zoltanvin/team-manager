using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TeamManagerCSharp.Model;

namespace TeamManagerCSharp.Dao
{
    /*
     * Dao megvalósítás játékosok adatainak menedzselésére adatbázissal.
     */
    class PlayersAdoDao : IPlayersDao
    {
        private static ITeamsDao _teamsDao = new TeamsAdoDao();
        /*
         * Új játékos beszúrása adatbázisba.
         */
        public bool AddPlayer(Player player)
        {
            if (player == null)
            {
                return false;
            }

            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                Player p = FindPlayerByPlayerName(conn, player);
                if (p != null)
                {
                    return false;
                }

                command.CommandText = "INSERT INTO players (name, position, birth_year, team_id) VALUES (@name, @position, @birthYear, @teamId);";

                command.Parameters.Add("name", System.Data.DbType.String).Value = player.Name;
                command.Parameters.Add("position", System.Data.DbType.String).Value = player.Position;
                command.Parameters.Add("birthYear", System.Data.DbType.Int32).Value = player.BirthYear;
                command.Parameters.Add("teamId", System.Data.DbType.Int32).Value = _teamsDao.GetTeam(player.TeamName).ID;

                if (command.ExecuteNonQuery() != 1)
                {
                    return false;
                }
                return true;
            }
        }
        /*
         * Egy játékos kinyerése adatbázisból ID alapján.
         */
        public Player GetPlayer(int id)
        {
            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "SELECT * FROM players WHERE id = @id;";
                command.Parameters.Add("id", System.Data.DbType.Int32).Value = id;

                using (var reader = command.ExecuteReader())
                {
                    List<Player> players = ReadPlayersFromReader(reader);
                    if (players.Count > 0)
                    {
                        return players[0];
                    }
                    else
                    {
                        return null;
                    }
                }
            }
        }
        /*
         * Összes játékos kinyerése adatbázisból.
         */
        public IEnumerable<Player> GetPlayers()
        {
            List<Player> players = new List<Player>();

            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "SELECT * FROM players;";

                using (var reader = command.ExecuteReader())
                {
                    players = ReadPlayersFromReader(reader);                   
                }
            }
            return players;
        }
        /*
         * Egy játékos módosítása adatbázisban ID alapján.
         */
        public bool ModifyPlayer(Player player)
        {
            if (player == null)
            {
                return false;
            }

            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "UPDATE players SET name = @name, position = @position, birth_year = @birthYear, team_id = @teamId WHERE id = @id;";

                command.Parameters.Add("name", System.Data.DbType.String).Value = player.Name;
                command.Parameters.Add("position", System.Data.DbType.String).Value = player.Position;
                command.Parameters.Add("birthYear", System.Data.DbType.Int32).Value = player.BirthYear;
                command.Parameters.Add("teamId", System.Data.DbType.Int32).Value = _teamsDao.GetTeam(player.TeamName).ID;
                command.Parameters.Add("id", System.Data.DbType.Int32).Value = player.ID;

                if (command.ExecuteNonQuery() != 1)
                {
                    return false;
                }
                return true;
            }
        }
        /*
         * Játékosok adatainak kinyerése adatbázisból.
         */
        private List<Player> ReadPlayersFromReader(SQLiteDataReader reader)
        {
            List<Player> players = new List<Player>();
            while (reader.Read())
            {
                players.Add(new Player
                {
                    ID = reader.GetInt32(reader.GetOrdinal("id")),
                    Name = reader.GetString(reader.GetOrdinal("name")),
                    Position = reader.GetString(reader.GetOrdinal("position")),
                    BirthYear = reader.GetInt32(reader.GetOrdinal("birth_year")),
                    TeamName = _teamsDao.GetTeam(reader.GetInt32(reader.GetOrdinal("team_id"))).Name
                });
            }
            return players;
        }
        /*
         * Egy játékos kinyerése adatbázisból név alapján. Leellenőrizzük, hogy
         * létezik e már a megadott névvel játékos.
         */
        private Player FindPlayerByPlayerName(SQLiteConnection conn, Player player)
        {
            using (SQLiteCommand command = conn.CreateCommand())
            {
                command.CommandText = "SELECT * FROM players WHERE name = @playerName;";
                command.Parameters.Add("playerName", System.Data.DbType.String).Value = player.Name;

                using (var reader = command.ExecuteReader())
                {
                    List<Player> players = ReadPlayersFromReader(reader);
                    if (players.Count > 0)
                    {
                        return players[0];
                    }
                    else
                    {
                        return null;
                    }
                }

            }
        }
    }
}
