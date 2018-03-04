using RailwayTrips.Logic;
using System;
using System.Windows.Forms;

namespace RailwayTrips.GUI
{
    public partial class TicketsView : UserControl
    {
        public TicketsView()
        {
            InitializeComponent();
            Railway.Instance.BindingTickets = ticketsBindingSource;

            //control adding
            Railway.Instance.OnEnableTicketAdd += Instance_OnEnableTicketAdd;
            Instance_OnEnableTicketAdd(false);
        }

        private void Instance_OnEnableTicketAdd(bool state)
        {
            bindingNavigatorAddNewItem.Enabled = state;
            ticketsDataGridView.AllowUserToAddRows = state;
        }

        private void TicketsView_Load(object sender, EventArgs e)
        {

            //load fk from trips
            fkColumn.DataSource = Railway.Instance.BindingTrips;
            fkColumn.DisplayMember = "PK";
            fkColumn.ValueMember = "PK";
        }

        private void ticketsDataGridView_DataError(object sender, DataGridViewDataErrorEventArgs e)
        {
            //e.Cancel = true;
        }
    }
}
