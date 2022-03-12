namespace TeamManagerCSharp.View
{
    partial class AddPlayerDialog
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
            this.labelName = new System.Windows.Forms.Label();
            this.labelPosition = new System.Windows.Forms.Label();
            this.labelBirthYear = new System.Windows.Forms.Label();
            this.textBoxPlayerName = new System.Windows.Forms.TextBox();
            this.comboBoxPlayersPosition = new System.Windows.Forms.ComboBox();
            this.numericUpDownPlayersBirthYear = new System.Windows.Forms.NumericUpDown();
            this.buttonOk = new System.Windows.Forms.Button();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.labelTeam = new System.Windows.Forms.Label();
            this.comboBoxPlayersTeam = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownPlayersBirthYear)).BeginInit();
            this.SuspendLayout();
            // 
            // labelName
            // 
            this.labelName.AutoSize = true;
            this.labelName.Location = new System.Drawing.Point(37, 34);
            this.labelName.Name = "labelName";
            this.labelName.Size = new System.Drawing.Size(27, 13);
            this.labelName.TabIndex = 0;
            this.labelName.Text = "Név";
            // 
            // labelPosition
            // 
            this.labelPosition.AutoSize = true;
            this.labelPosition.Location = new System.Drawing.Point(37, 83);
            this.labelPosition.Name = "labelPosition";
            this.labelPosition.Size = new System.Drawing.Size(43, 13);
            this.labelPosition.TabIndex = 1;
            this.labelPosition.Text = "Pozíció";
            // 
            // labelBirthYear
            // 
            this.labelBirthYear.AutoSize = true;
            this.labelBirthYear.Location = new System.Drawing.Point(37, 132);
            this.labelBirthYear.Name = "labelBirthYear";
            this.labelBirthYear.Size = new System.Drawing.Size(64, 13);
            this.labelBirthYear.TabIndex = 2;
            this.labelBirthYear.Text = "Születési év";
            // 
            // textBoxPlayerName
            // 
            this.textBoxPlayerName.Location = new System.Drawing.Point(153, 34);
            this.textBoxPlayerName.Name = "textBoxPlayerName";
            this.textBoxPlayerName.Size = new System.Drawing.Size(175, 20);
            this.textBoxPlayerName.TabIndex = 3;
            // 
            // comboBoxPlayersPosition
            // 
            this.comboBoxPlayersPosition.FormattingEnabled = true;
            this.comboBoxPlayersPosition.Location = new System.Drawing.Point(153, 83);
            this.comboBoxPlayersPosition.Name = "comboBoxPlayersPosition";
            this.comboBoxPlayersPosition.Size = new System.Drawing.Size(175, 21);
            this.comboBoxPlayersPosition.TabIndex = 4;
            // 
            // numericUpDownPlayersBirthYear
            // 
            this.numericUpDownPlayersBirthYear.Location = new System.Drawing.Point(153, 132);
            this.numericUpDownPlayersBirthYear.Maximum = new decimal(new int[] {
            2020,
            0,
            0,
            0});
            this.numericUpDownPlayersBirthYear.Minimum = new decimal(new int[] {
            1900,
            0,
            0,
            0});
            this.numericUpDownPlayersBirthYear.Name = "numericUpDownPlayersBirthYear";
            this.numericUpDownPlayersBirthYear.Size = new System.Drawing.Size(175, 20);
            this.numericUpDownPlayersBirthYear.TabIndex = 5;
            this.numericUpDownPlayersBirthYear.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.numericUpDownPlayersBirthYear.Value = new decimal(new int[] {
            1990,
            0,
            0,
            0});
            // 
            // buttonOk
            // 
            this.buttonOk.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.buttonOk.Location = new System.Drawing.Point(75, 238);
            this.buttonOk.Name = "buttonOk";
            this.buttonOk.Size = new System.Drawing.Size(75, 23);
            this.buttonOk.TabIndex = 6;
            this.buttonOk.Text = "OK";
            this.buttonOk.UseVisualStyleBackColor = true;
            this.buttonOk.Click += new System.EventHandler(this.buttonOk_Click);
            // 
            // buttonCancel
            // 
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.buttonCancel.Location = new System.Drawing.Point(219, 238);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(75, 23);
            this.buttonCancel.TabIndex = 7;
            this.buttonCancel.Text = "Mégse";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // labelTeam
            // 
            this.labelTeam.AutoSize = true;
            this.labelTeam.Location = new System.Drawing.Point(40, 179);
            this.labelTeam.Name = "labelTeam";
            this.labelTeam.Size = new System.Drawing.Size(40, 13);
            this.labelTeam.TabIndex = 8;
            this.labelTeam.Text = "Csapat";
            // 
            // comboBoxPlayersTeam
            // 
            this.comboBoxPlayersTeam.FormattingEnabled = true;
            this.comboBoxPlayersTeam.Location = new System.Drawing.Point(153, 179);
            this.comboBoxPlayersTeam.Name = "comboBoxPlayersTeam";
            this.comboBoxPlayersTeam.Size = new System.Drawing.Size(175, 21);
            this.comboBoxPlayersTeam.TabIndex = 9;
            // 
            // AddPlayerDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(373, 289);
            this.Controls.Add(this.comboBoxPlayersTeam);
            this.Controls.Add(this.labelTeam);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.buttonOk);
            this.Controls.Add(this.numericUpDownPlayersBirthYear);
            this.Controls.Add(this.comboBoxPlayersPosition);
            this.Controls.Add(this.textBoxPlayerName);
            this.Controls.Add(this.labelBirthYear);
            this.Controls.Add(this.labelPosition);
            this.Controls.Add(this.labelName);
            this.Name = "AddPlayerDialog";
            this.Text = "Játékos hozzáadása";
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownPlayersBirthYear)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelName;
        private System.Windows.Forms.Label labelPosition;
        private System.Windows.Forms.Label labelBirthYear;
        private System.Windows.Forms.TextBox textBoxPlayerName;
        private System.Windows.Forms.ComboBox comboBoxPlayersPosition;
        private System.Windows.Forms.NumericUpDown numericUpDownPlayersBirthYear;
        private System.Windows.Forms.Button buttonOk;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.Label labelTeam;
        private System.Windows.Forms.ComboBox comboBoxPlayersTeam;
    }
}