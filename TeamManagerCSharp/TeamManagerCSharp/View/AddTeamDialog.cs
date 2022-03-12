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
     * Csapat hozzáadására/módosítására UI felület.
     */
    public partial class AddTeamDialog : Form
    {
        private ITeamsController _controller;
        private bool _ifModify = false;
        private int _teamId = -1;
        /*
         * Controller és módosításkor a módosítandó csapat átadása a felületnek.
         */
        public AddTeamDialog(ITeamsController controller, Team team = null)
        {
            _controller = controller;
            InitializeComponent();
            /*
             * Módosításkor a csapat adatainak beállítása a UI elemeknek.
             */
            if (team != null)
            {
                _ifModify = true;
                _teamId = team.ID;
                textBoxTeamName.Text = team.Name;
                buttonOk.Text = "Módosít";
                this.Text = "Csapat módosítása";
            }
        }
        /*
         * Csapat hozzáadásának/módosításának lekezelése. Validálás és megfelelő hibaüzenet küldése.
         */
        private void buttonOk_Click(object sender, EventArgs e)
        {
            string name = textBoxTeamName.Text;

            if (name == string.Empty)
            {
                MessageBox.Show("ERROR", "A név nem lehet üres!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (_ifModify)
            {
                if (!_controller.ModifyTeam(new Team
                {
                    ID = _teamId,
                    Name = name
                }))
                {
                    MessageBox.Show("ERROR", "Nem sikerült a módosítás!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
            else
            {
                if (!_controller.AddTeam(new Team
                {
                    Name = name
                }))
                {
                    MessageBox.Show("ERROR", "Nem sikerült a mentés!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            }
        }
    }
}
