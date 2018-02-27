namespace RailwayTrips.Main
{
    partial class Form1
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
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.trainsView1 = new RailwayTrips.GUI.TrainsView();
            this.tripsView1 = new RailwayTrips.GUI.TripsView();
            this.ticketsView1 = new RailwayTrips.GUI.TicketsView();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.tabPage3.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(284, 261);
            this.tabControl1.TabIndex = 0;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.trainsView1);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(276, 235);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Поезда";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.tripsView1);
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(276, 235);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Поездки";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // tabPage3
            // 
            this.tabPage3.Controls.Add(this.ticketsView1);
            this.tabPage3.Location = new System.Drawing.Point(4, 22);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3.Size = new System.Drawing.Size(276, 235);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "Билеты";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // trainsView1
            // 
            this.trainsView1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.trainsView1.Location = new System.Drawing.Point(3, 3);
            this.trainsView1.Name = "trainsView1";
            this.trainsView1.Size = new System.Drawing.Size(270, 229);
            this.trainsView1.TabIndex = 0;
            // 
            // tripsView1
            // 
            this.tripsView1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tripsView1.Location = new System.Drawing.Point(3, 3);
            this.tripsView1.Name = "tripsView1";
            this.tripsView1.Size = new System.Drawing.Size(270, 229);
            this.tripsView1.TabIndex = 0;
            // 
            // ticketsView1
            // 
            this.ticketsView1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ticketsView1.Location = new System.Drawing.Point(3, 3);
            this.ticketsView1.Name = "ticketsView1";
            this.ticketsView1.Size = new System.Drawing.Size(270, 229);
            this.ticketsView1.TabIndex = 0;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.tabControl1);
            this.Name = "Form1";
            this.Text = "Архитектура ПО л2 в 24";
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.tabPage3.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.TabPage tabPage3;
        private GUI.TrainsView trainsView1;
        private GUI.TripsView tripsView1;
        private GUI.TicketsView ticketsView1;
    }
}

