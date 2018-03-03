using RailwayTrips.Data;
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
            Railway.Instance.OnEnableTripAdd += Instance_OnEnableTripAdd;
            Instance_OnEnableTripAdd(false);
        }

        private void Instance_OnEnableTripAdd(bool state)
        {
            bindingNavigatorAddNewItem.Enabled = state;
            tripsDataGridView.AllowUserToAddRows = state;
        }

        private void EnableRemoveTrip(bool state)
        {
            bindingNavigatorDeleteItem.Enabled = state;
            tripsDataGridView.AllowUserToDeleteRows = state;
            trainColumn.ReadOnly = !state;
            dataGridViewTextBoxColumn1.ReadOnly = !state;
        }

        private void TripsView_Load(object sender, System.EventArgs e)
        {
            trainColumn.DataSource = Railway.Instance.BindingTrains;
            trainColumn.DisplayMember = "TrainNumber";
            trainColumn.ValueMember = "TrainNumber";
        }
    }
}
