using RailwayTrips.Data;
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
        }

        private void trainsBindingSource_CurrentChanged(object sender, System.EventArgs e)
        {
            int id = ((Train)trainsBindingSource.Current).TrainNumber;
            if (Railway.Instance.tripExist(id) ||
                Railway.Instance.ticketExist(id))
            {
                trainsBindingNavigator.DeleteItem.Enabled = false;
                trainsDataGridView.AllowUserToDeleteRows = false;
            }
            else
            {

                trainsBindingNavigator.DeleteItem.Enabled = true;
                trainsDataGridView.AllowUserToDeleteRows = true;
            }
        }
    }
}
