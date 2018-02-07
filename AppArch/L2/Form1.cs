using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Lib1;

namespace L2
{
    public partial class Form1 : Form
    {
        Items items = new Items();
        Items items2 = new Items();
        List<int> listId = new List<int>() { 1, 2, 3, 4, 5 };
        public Form1()
        {
            InitializeComponent();
        }

        private void itemsView1_Load(object sender, EventArgs e)
        {
            itemsView1.itemsBindingSource.DataSource = items;
            itemsDetails1.itemsBindingSource.DataSource = items2;
            bindingSource1.DataSource = listId;

        }
    }
}
