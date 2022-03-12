namespace TeamManagerCSharp
{
    partial class TeamManagerMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.playerToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.addPlayerToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.listPlayersToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.teamToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.addTeamToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.listTeamsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.dataGridViewPlayers = new System.Windows.Forms.DataGridView();
            this.dataGridViewTeams = new System.Windows.Forms.DataGridView();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewPlayers)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewTeams)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.playerToolStripMenuItem,
            this.teamToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // playerToolStripMenuItem
            // 
            this.playerToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addPlayerToolStripMenuItem,
            this.listPlayersToolStripMenuItem});
            this.playerToolStripMenuItem.Name = "playerToolStripMenuItem";
            this.playerToolStripMenuItem.Size = new System.Drawing.Size(57, 20);
            this.playerToolStripMenuItem.Text = "Játékos";
            // 
            // addPlayerToolStripMenuItem
            // 
            this.addPlayerToolStripMenuItem.Name = "addPlayerToolStripMenuItem";
            this.addPlayerToolStripMenuItem.Size = new System.Drawing.Size(175, 22);
            this.addPlayerToolStripMenuItem.Text = "Játékos hozzáadása";
            this.addPlayerToolStripMenuItem.Click += new System.EventHandler(this.addPlayerToolStripMenuItem_Click);
            // 
            // listPlayersToolStripMenuItem
            // 
            this.listPlayersToolStripMenuItem.Name = "listPlayersToolStripMenuItem";
            this.listPlayersToolStripMenuItem.Size = new System.Drawing.Size(175, 22);
            this.listPlayersToolStripMenuItem.Text = "Játékosok listázása";
            this.listPlayersToolStripMenuItem.Click += new System.EventHandler(this.listPlayersToolStripMenuItem_Click);
            // 
            // teamToolStripMenuItem
            // 
            this.teamToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addTeamToolStripMenuItem,
            this.listTeamsToolStripMenuItem});
            this.teamToolStripMenuItem.Name = "teamToolStripMenuItem";
            this.teamToolStripMenuItem.Size = new System.Drawing.Size(55, 20);
            this.teamToolStripMenuItem.Text = "Csapat";
            // 
            // addTeamToolStripMenuItem
            // 
            this.addTeamToolStripMenuItem.Name = "addTeamToolStripMenuItem";
            this.addTeamToolStripMenuItem.Size = new System.Drawing.Size(180, 22);
            this.addTeamToolStripMenuItem.Text = "Csapat hozzáadása";
            this.addTeamToolStripMenuItem.Click += new System.EventHandler(this.addTeamToolStripMenuItem_Click);
            // 
            // listTeamsToolStripMenuItem
            // 
            this.listTeamsToolStripMenuItem.Name = "listTeamsToolStripMenuItem";
            this.listTeamsToolStripMenuItem.Size = new System.Drawing.Size(180, 22);
            this.listTeamsToolStripMenuItem.Text = "Csapatok listázása";
            this.listTeamsToolStripMenuItem.Click += new System.EventHandler(this.listTeamsToolStripMenuItem_Click);
            // 
            // dataGridViewPlayers
            // 
            this.dataGridViewPlayers.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewPlayers.Location = new System.Drawing.Point(0, 27);
            this.dataGridViewPlayers.Name = "dataGridViewPlayers";
            this.dataGridViewPlayers.Size = new System.Drawing.Size(800, 424);
            this.dataGridViewPlayers.TabIndex = 1;
            this.dataGridViewPlayers.Visible = false;
            this.dataGridViewPlayers.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.modifyPlayer);
            // 
            // dataGridViewTeams
            // 
            this.dataGridViewTeams.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewTeams.Location = new System.Drawing.Point(0, 27);
            this.dataGridViewTeams.Name = "dataGridViewTeams";
            this.dataGridViewTeams.Size = new System.Drawing.Size(800, 424);
            this.dataGridViewTeams.TabIndex = 4;
            this.dataGridViewTeams.Visible = false;
            this.dataGridViewTeams.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.modifyTeam);
            // 
            // TeamManagerMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.dataGridViewTeams);
            this.Controls.Add(this.dataGridViewPlayers);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "TeamManagerMain";
            this.Text = "Team Manager";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewPlayers)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewTeams)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem playerToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem addPlayerToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem listPlayersToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem teamToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem addTeamToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem listTeamsToolStripMenuItem;
        private System.Windows.Forms.DataGridView dataGridViewPlayers;
        private System.Windows.Forms.DataGridView dataGridViewTeams;
    }
}

