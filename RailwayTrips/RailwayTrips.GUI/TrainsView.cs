using RailwayTrips.Logic;
using System.Windows.Forms;

namespace RailwayTrips.GUI
{
    public partial class TrainsView : UserControl
    {
        public TrainsView()
        {
            InitializeComponent();
            //bind railway object with trains on this form
            Railway.Instance.BindingTrains = trainsBindingSource;

            //control removing rows
            Railway.Instance.OnEnableTrainRemove += EnableRemoveTrain;
            dataGridViewTextBoxColumn1.ReadOnly = true;
        }

        public void EnableRemoveTrain(bool state)
        {
            bindingNavigatorDeleteItem.Enabled = state;
            trainsDataGridView.AllowUserToDeleteRows = state;
            ////we cant edit pk column while cascade refs exists
            //dataGridViewTextBoxColumn1.ReadOnly = !state;
        }
    }
}
