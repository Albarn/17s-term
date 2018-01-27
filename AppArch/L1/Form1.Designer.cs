namespace L1
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
            this.colorPanel1 = new LabComponents.ColorPanel();
            this.SuspendLayout();
            // 
            // colorPanel1
            // 
            this.colorPanel1.colors = new System.Drawing.Color[] {
        System.Drawing.Color.Red,
        System.Drawing.Color.Yellow,
        System.Drawing.Color.Blue};
            this.colorPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.colorPanel1.Location = new System.Drawing.Point(0, 0);
            this.colorPanel1.marginValues = new int[] {
        -3,
        5,
        2};
            this.colorPanel1.Max = 7;
            this.colorPanel1.Min = -2;
            this.colorPanel1.Name = "colorPanel1";
            this.colorPanel1.Size = new System.Drawing.Size(320, 181);
            this.colorPanel1.TabIndex = 0;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(320, 181);
            this.Controls.Add(this.colorPanel1);
            this.Name = "Form1";
            this.Text = "Арх. ПО л1 в24";
            this.ResumeLayout(false);

        }

        #endregion

        private LabComponents.ColorPanel colorPanel1;
    }
}

