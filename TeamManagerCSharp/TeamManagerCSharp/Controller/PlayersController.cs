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
     * Játékos controller a dao és view összekötésére.
     */
    public class PlayersController : IPlayersController
    {
        private IPlayersDao _dao;
        public PlayersController (IPlayersDao playersDao)
        {
            _dao = playersDao;
        }
        public bool AddPlayer(Player player)
        {
            return _dao.AddPlayer(player);
        }

        public Player GetPlayer(int id)
        {
            return _dao.GetPlayer(id);
        }

        public IEnumerable<Player> GetPlayers()
        {
            return _dao.GetPlayers();
        }

        public bool ModifyPlayer(Player player)
        {
            return _dao.ModifyPlayer(player);
        }
    }
}
