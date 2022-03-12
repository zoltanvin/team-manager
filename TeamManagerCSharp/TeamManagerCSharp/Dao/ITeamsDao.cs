using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TeamManagerCSharp.Model;

namespace TeamManagerCSharp.Dao
{
    public interface ITeamsDao
    {
        #region Team
        bool AddTeam(Team team);
        IEnumerable<Team> GetTeams();
        bool ModifyTeam(Team team);
        Team GetTeam(int id);
        Team GetTeam(string name);
        #endregion
    }
}
