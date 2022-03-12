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
     * Dao megvalósítás csapatok adatainak menedzselésére adatbázissal.
     */
    class TeamsAdoDao : ITeamsDao
    {
        /*
         * Új csapat beszúrása adatbázisba.
         */
        public bool AddTeam(Team team)
        {
            if (team == null)
            {
                return false;
            }

            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                /*
                 * Leellenőrizzük, hogy létezik e már a megadott névvel csapat.
                 */
                Team t = FindTeamByTeamName(conn, team);
                if (t != null)
                {
                    return false;
                }

                command.CommandText = "INSERT INTO teams (name) VALUES (@name);";
                command.Parameters.Add("name", System.Data.DbType.String).Value = team.Name;

                if (command.ExecuteNonQuery() != 1)
                {
                    return false;
                }
                return true;
            }
        }
        /*
         * Egy csapat kinyerése adatbázisból ID alapján.
         */
        public Team GetTeam(int id)
        {
            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "SELECT * FROM teams WHERE id = @id;";
                command.Parameters.Add("id", System.Data.DbType.Int32).Value = id;

                using (var reader = command.ExecuteReader())
                {
                    List<Team> teams = ReadTeamsFromReader(reader);
                    if (teams.Count > 0)
                    {
                        return teams[0];
                    }
                    else
                    {
                        return null;
                    }
                }
            }
        }
        /*
         * Egy csapat kinyerése adatbázisból név alapján.
         */
        public Team GetTeam(string name)
        {
            Team team = new Team
            {
                Name = name
            };
            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            {
                return FindTeamByTeamName(conn, team);
            }
        }
        /*
         * Összes csapat kinyerése adatbázisból.
         */
        public IEnumerable<Team> GetTeams()
        {
            List<Team> teams = new List<Team>();

            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "SELECT * FROM teams;";

                using (var reader = command.ExecuteReader())
                {
                    teams = ReadTeamsFromReader(reader);
                }
            }
            return teams;
        }
        /*
         * Egy csapat módosítása adatbázisban ID alapján.
         */
        public bool ModifyTeam(Team team)
        {
            if (team == null)
            {
                return false;
            }

            using (SQLiteConnection conn = new SQLiteConnection(DBConfig._connection_string))
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "UPDATE teams SET name = @name WHERE id = @id;";
                command.Parameters.Add("name", System.Data.DbType.String).Value = team.Name;
                command.Parameters.Add("id", System.Data.DbType.Int32).Value = team.ID;

                if (command.ExecuteNonQuery() != 1)
                {
                    return false;
                }
                return true;
            }
        }
        /*
         * Csapatok adatainak kinyerése adatbázisból.
         */
        private List<Team> ReadTeamsFromReader(SQLiteDataReader reader)
        {
            List<Team> teams = new List<Team>();
            while (reader.Read())
            {
                teams.Add(new Team
                {
                    ID = reader.GetInt32(reader.GetOrdinal("id")),
                    Name = reader.GetString(reader.GetOrdinal("name"))
                });
            }
            return teams;
        }
        /*
         * Egy csapat kinyerése adatbázisból név alapján.
         */
        private Team FindTeamByTeamName(SQLiteConnection conn, Team team)
        {
            using (SQLiteCommand command = conn.CreateCommand())
            {
                conn.Open();
                command.CommandText = "SELECT * FROM teams WHERE name = @teamName;";
                command.Parameters.Add("teamName", System.Data.DbType.String).Value = team.Name;

                using (var reader = command.ExecuteReader())
                {
                    List<Team> teams = ReadTeamsFromReader(reader);
                    if (teams.Count > 0)
                    {
                        return teams[0];
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
