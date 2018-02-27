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
        }

        private void TicketsView_Load(object sender, EventArgs e)
        {
            trainColumn.DataSource = Railway.Instance.BindingTrains;
            trainColumn.DisplayMember = "TrainNumber";
            trainColumn.ValueMember = "TrainNumber";

            tripColumn.DataSource = Railway.Instance.BindingTrips;
            tripColumn.DisplayMember = "TripDate";
            tripColumn.ValueMember = "TripDate";
        }
    }
}
