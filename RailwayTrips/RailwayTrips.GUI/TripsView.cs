using RailwayTrips.Logic;
using System.Windows.Forms;

namespace RailwayTrips.GUI
{
    public partial class TripsView : UserControl
    {
        public TripsView()
        {
            InitializeComponent();
            Railway.Instance.BindingTrips = tripsBindingSource;
            Railway.Instance.OnEnableTripRemove += EnableRemoveTrip;
        }

        private void EnableRemoveTrip(bool state)
        {
            bindingNavigatorDeleteItem.Enabled = state;
            tripsDataGridView.AllowUserToDeleteRows = state;
        }

        private void TripsView_Load(object sender, System.EventArgs e)
        {
            trainColumn.DataSource = Railway.Instance.BindingTrains;
            trainColumn.DisplayMember = "TrainNumber";
            trainColumn.ValueMember = "TrainNumber";
        }
    }
}
