using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TeamManagerCSharp.Model;

namespace TeamManagerCSharp.Dao
{
    public interface IPlayersDao
    {
        #region Player
        bool AddPlayer(Player player);
        IEnumerable<Player> GetPlayers();
        bool ModifyPlayer(Player player);
        Player GetPlayer(int id);
        #endregion
    }
}
