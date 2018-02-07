namespace L2
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
            this.components = new System.ComponentModel.Container();
            this.itemsDetails1 = new Lib2.ItemsDetails();
            this.itemsView1 = new Lib2.ItemsView();
            this.bindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).BeginInit();
            this.SuspendLayout();
            // 
            // itemsDetails1
            // 
            this.itemsDetails1.Dock = System.Windows.Forms.DockStyle.Right;
            this.itemsDetails1.Location = new System.Drawing.Point(269, 0);
            this.itemsDetails1.Name = "itemsDetails1";
            this.itemsDetails1.Size = new System.Drawing.Size(220, 261);
            this.itemsDetails1.TabIndex = 1;
            // 
            // itemsView1
            // 
            this.itemsView1.Dock = System.Windows.Forms.DockStyle.Left;
            this.itemsView1.Location = new System.Drawing.Point(0, 0);
            this.itemsView1.Name = "itemsView1";
            this.itemsView1.Size = new System.Drawing.Size(263, 261);
            this.itemsView1.TabIndex = 0;
            this.itemsView1.Load += new System.EventHandler(this.itemsView1_Load);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(489, 261);
            this.Controls.Add(this.itemsDetails1);
            this.Controls.Add(this.itemsView1);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.bindingSource1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private Lib2.ItemsView itemsView1;
        private Lib2.ItemsDetails itemsDetails1;
        private System.Windows.Forms.BindingSource bindingSource1;
    }
}

