using RailwayTrips.Data;
using RailwayTrips.Logic;
using System;
using System.Collections.Generic;
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
            List<string> pks = Railway.Instance.GetPKs();
            fkColumn.Items.Clear();
            foreach (string pk in pks)
                fkColumn.Items.Add(pk);
        }

        private void ticketsBindingSource_AddingNew(object sender, System.ComponentModel.AddingNewEventArgs e)
        {
            TicketsView_Load(sender, e);
            //fkColumn.DataSource = Railway.Instance.GetPKs();
        }

        private void TicketsView_Enter(object sender, EventArgs e)
        {
            List<string> pks = Railway.Instance.GetPKs();
            fkColumn.Items.Clear();
            foreach (string pk in pks)
                fkColumn.Items.Add(pk);
        }

        private void ticketsBindingSource_DataError(object sender, BindingManagerDataErrorEventArgs e)
        {
            
        }

        private void ticketsDataGridView_DataError(object sender, DataGridViewDataErrorEventArgs e)
        {
            e.Cancel = true;
        }
    }
}
