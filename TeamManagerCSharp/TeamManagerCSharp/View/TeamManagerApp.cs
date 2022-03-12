using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TeamManagerCSharp.Controller;
using TeamManagerCSharp.Dao;
using TeamManagerCSharp.Model;
using TeamManagerCSharp.View;

namespace TeamManagerCSharp
{
    /*
     * Alkalmazás belépési pontja.
     */
    public partial class TeamManagerMain : Form
    {
        private IPlayersController _controllerPlayer;
        private ITeamsController _controllerTeam;
        public TeamManagerMain()
        {
            InitializeComponent();
            _controllerPlayer = new PlayersController(new PlayersAdoDao());
            _controllerTeam = new TeamsController(new TeamsAdoDao());
        }
        /*
         * Játékost létrehozó/módosító ablak megnyitásának rakötése a menuitem-re.
         */
        private void addPlayerToolStripMenuItem_Click(object sender, EventArgs e)
        {
            using (AddPlayerDialog dialog = new AddPlayerDialog(_controllerPlayer, _controllerTeam))
            {
                dialog.ShowDialog();
            }
        }
        /*
         * Játékosok listáját tároló datagridview feltöltésének rakötése a menuitem-re.
         */
        private void listPlayersToolStripMenuItem_Click(object sender, EventArgs e)
        {
            dataGridViewTeams.Visible = false;
            dataGridViewPlayers.Visible = true;
            dataGridViewPlayers.DataSource = null;
            dataGridViewPlayers.DataSource = _controllerPlayer.GetPlayers();
        }
        /*
         * Játékos módosításához szükséges játékos létrehozó felület megnyitásának rákötése a datagridview celláira.
         */
        private void modifyPlayer(object sender, DataGridViewCellMouseEventArgs e)
        {
            try
            {
                if (!(dataGridViewPlayers.CurrentRow.DataBoundItem is Player player))
                {
                    return;
                }

                using (AddPlayerDialog dialog = new AddPlayerDialog(_controllerPlayer, _controllerTeam, player))
                {
                    dialog.ShowDialog();
                }

            } catch (Exception)
            {

            }
        }
        /*
         * Csapatot létrehozó/módosító ablak megnyitásának rakötése a menuitem-re.
         */
        private void addTeamToolStripMenuItem_Click(object sender, EventArgs e)
        {
            using (AddTeamDialog dialog = new AddTeamDialog(_controllerTeam))
            {
                dialog.ShowDialog();
            }
        }
        /*
         * Csapat módosításához szükséges csapat létrehozó felület megnyitásának rákötése a datagridview celláira.
         */
        private void modifyTeam(object sender, DataGridViewCellMouseEventArgs e)
        {
            try
            {
                if (!(dataGridViewTeams.CurrentRow.DataBoundItem is Team team))
                {
                    return;
                }

                using (AddTeamDialog dialog = new AddTeamDialog(_controllerTeam, team))
                {
                    dialog.ShowDialog();
                }

            }
            catch (Exception)
            {

            }
        }
        /*
         * Csapatok listáját tároló datagridview feltöltésének rakötése a menuitem-re.
         */
        private void listTeamsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            dataGridViewPlayers.Visible = false;
            dataGridViewTeams.Visible = true;
            dataGridViewTeams.DataSource = null;
            dataGridViewTeams.DataSource = _controllerTeam.GetTeams();
        }
    }
}
