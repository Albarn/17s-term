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

        private void tripsBindingSource_CurrentChanged(object sender, System.EventArgs e)
        {
            string id = ((Trip)tripsBindingSource.Current).PK;
            if (Railway.Instance.ticketExist(id))
            {
                tripsBindingNavigator.DeleteItem.Enabled = false;
                tripsDataGridView.AllowUserToDeleteRows = false;
                dataGridViewTextBoxColumn1.ReadOnly = true;
                trainColumn.ReadOnly = true;
            }
            else
            {

                tripsBindingNavigator.DeleteItem.Enabled = true;
                tripsDataGridView.AllowUserToDeleteRows = true;
                dataGridViewTextBoxColumn1.ReadOnly = false;
                trainColumn.ReadOnly = false;
            }
        }

        private void tripsBindingSource_AddingNew(object sender, System.ComponentModel.AddingNewEventArgs e)
        {

        }

        private void TripsView_Enter(object sender, System.EventArgs e)
        {

        }
    }
}
