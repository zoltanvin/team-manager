using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TeamManagerCSharp.Dao;
using TeamManagerCSharp.Model;

namespace TeamManagerCSharp.Controller
{
    /*
     * Csapat controller a dao és view összekötésére.
     */
    public class TeamsController : ITeamsController
    {
        private ITeamsDao _dao;

        public TeamsController(ITeamsDao teamsDao)
        {
            _dao = teamsDao;
        }

        public bool AddTeam(Team team)
        {
            return _dao.AddTeam(team);
        }

        public Team GetTeam(int id)
        {
            return _dao.GetTeam(id);
        }

        public Team GetTeam(string name)
        {
            return _dao.GetTeam(name);
        }

        public IEnumerable<Team> GetTeams()
        {
            return _dao.GetTeams();
        }

        public bool ModifyTeam(Team team)
        {
            return _dao.ModifyTeam(team);
        }
    }
}
