using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Lib1;

namespace Lib2
{
    public partial class ItemsView : UserControl
    {
        List<int> listId = new List<int>() { 1, 2, 3, 4, 5 };
        public ItemsView()
        {
            InitializeComponent();
            bindingSource1.DataSource = listId;
            
        }

        private void itemsBindingSource_AddingNew(object sender, AddingNewEventArgs e)
        {
            e.NewObject = new Item() { ID = 1, Info = "111" };
        }
    }
}
