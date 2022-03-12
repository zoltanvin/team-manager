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
using TeamManagerCSharp.Model;

namespace TeamManagerCSharp.View
{
    /*
     * Játékos hozzáadására/módosítására UI felület.
     */
    public partial class AddPlayerDialog : Form
    {
        private IPlayersController _controllerPlayer;
        private ITeamsController _controllerTeam;
        private bool _ifModify = false;
        private int _playerId = -1;
        /*
         * Controllerek és módosításkor a módosítandó játékos átadása a felületnek.
         */
        public AddPlayerDialog(IPlayersController controller, ITeamsController controllerTeam, Player player = null)
        {
            _controllerPlayer = controller;
            _controllerTeam = controllerTeam;
            InitializeComponent();
            comboBoxPlayersPosition.Items.AddRange(new string[] { "Kapus", "Hátvéd", "Középpályás", "Csatár" });
            List<Team> allTeam = _controllerTeam.GetTeams().ToList();
            var teams = allTeam.ToArray();
            comboBoxPlayersTeam.DisplayMember = "Name";
            comboBoxPlayersTeam.Items.AddRange(teams);
            /*
             * Módosításkor a játékos adatainak beállítása a UI elemeknek.
             */
            if (player != null)
            {
                _ifModify = true;
                _playerId = player.ID;
                textBoxPlayerName.Text = player.Name;
                numericUpDownPlayersBirthYear.Value = player.BirthYear;
                comboBoxPlayersPosition.SelectedIndex = comboBoxPlayersPosition.Items.IndexOf(player.Position);
                if (teams.Count() > 0 && player.TeamName != string.Empty)
                {
                    Team playersTeam = allTeam.FirstOrDefault(t => t.Name == player.TeamName);
                    comboBoxPlayersTeam.SelectedIndex = comboBoxPlayersTeam.Items.IndexOf(playersTeam);
                }
                buttonOk.Text = "Módosít";
                this.Text = "Játékos módosítása";
            }
            else
            {
                comboBoxPlayersPosition.SelectedIndex = 0;
                if (teams.Count() > 0)
                {
                    comboBoxPlayersTeam.SelectedIndex = 0;
                }
            }
        }
        /*
         * Játékos hozzáadásának/módosításának lekezelése. Validálás és megfelelő hibaüzenet küldése.
         */
        private void buttonOk_Click(object sender, EventArgs e)
        {
            string name = textBoxPlayerName.Text;
            string position = comboBoxPlayersPosition.Text;
            int birthYear = (int) numericUpDownPlayersBirthYear.Value;
            Team team = comboBoxPlayersTeam.SelectedItem as Team;

            if (name == string.Empty)
            {
                MessageBox.Show("ERROR", "A név nem lehet üres!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (team == null)
            {
                MessageBox.Show("ERROR", "Üres csapat!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (_ifModify)
            {
                if (!_controllerPlayer.ModifyPlayer(new Player
                {
                    ID = _playerId,
                    Name = name,
                    Position = position,
                    BirthYear = birthYear,
                    TeamName = team.Name
                }))
                {
                    MessageBox.Show("ERROR", "Nem sikerült a módosítás!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
            else
            {
                if (!_controllerPlayer.AddPlayer(new Player
                {
                    Name = name,
                    Position = position,
                    BirthYear = birthYear,
                    TeamName = team.Name
                }))
                {
                    MessageBox.Show("ERROR", "Nem sikerült a mentés!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                
            }
        }
    }
}
