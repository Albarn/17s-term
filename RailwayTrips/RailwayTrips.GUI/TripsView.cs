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

            //control adding/removing
            Railway.Instance.OnEnableTripRemove += EnableRemoveTrip;
            Railway.Instance.OnEnableTripAdd += Instance_OnEnableTripAdd;

            //on control load there is no trains in Railway
            //so block add first
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

            //cant edit pk if cascade ref exist
            trainColumn.ReadOnly = !state;
            dataGridViewTextBoxColumn1.ReadOnly = !state;
        }

        private void TripsView_Load(object sender, System.EventArgs e)
        {

            //load fk from trains
            trainColumn.DataSource = Railway.Instance.BindingTrains;
            trainColumn.DisplayMember = "TrainNumber";
            trainColumn.ValueMember = "TrainNumber";
        }
    }
}
