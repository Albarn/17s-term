using RailwayTrips.Logic;
using System.Windows.Forms;

namespace RailwayTrips.GUI
{
    public partial class TrainsView : UserControl
    {
        public TrainsView()
        {
            InitializeComponent();
            Railway.Instance.BindingTrains = trainsBindingSource;
            Railway.Instance.OnEnableTrainRemove += EnableRemoveTrain;
        }

        public void EnableRemoveTrain(bool state)
        {
            bindingNavigatorDeleteItem.Enabled = state;
            trainsDataGridView.AllowUserToDeleteRows = state;
            dataGridViewTextBoxColumn1.ReadOnly = !state;
        }
    }
}
