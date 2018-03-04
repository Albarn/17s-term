using RailwayTrips.Data;
using System;
using System.Windows.Forms;

namespace RailwayTrips.Logic
{
    public class Railway
    {
        // Singleton design pattern 
        static Railway _this = null;

        public Railway() { _this = this; }

        public static Railway Instance
        {
            get
            {
                if (_this == null) new Railway();
                return _this;
            }
        }

        // our tables
        readonly Trains trains = new Trains();
        readonly Trips trips = new Trips();
        readonly Tickets tickets = new Tickets();

        // binding source objects 
        private BindingSource bsTrains;
        private BindingSource bsTrips;
        private BindingSource bsTickets;

        // adding/removing logic by bindingSource events 
        public BindingSource BindingTrains
        {
            get { return bsTrains; }
            set
            {
                bsTrains = value;
                //bind collection
                value.DataSource = trains;

                //add handlers for other tables
                value.ListChanged += TrainsOrTripsListChanged;
                value.ListChanged += TrainsListChanged;

                //control adding/removing rows
                value.PositionChanged += TrainsPositionChanged;
            }
        }

        public BindingSource BindingTrips
        {
            get { return bsTrips; }
            set
            {
                bsTrips = value;

                //bind collection
                value.DataSource = trips;

                //add new trip with correct foreign key
                value.AddingNew += AddingNewTrip;

                //add handler for tickets
                value.ListChanged += TrainsOrTripsListChanged;

                //control adding/removing rows
                value.PositionChanged += TripsPositionChanged;
            }
        }

        public BindingSource BindingTickets
        {
            get { return bsTickets; }
            set
            {
                bsTickets = value;
                value.DataSource = tickets;

                //add new ticket with correct foreign key
                value.AddingNew += AddingNewTicket;

                //control tickets/trains removing
                value.ListChanged += TicketsListChanged;
            }
        }

        // cascade control refs and control adding/removing 
        public delegate void EnableRemoveEvent(bool state);

        //we cant delete rows with cascade reference
        public event EnableRemoveEvent OnEnableTrainRemove;
        public event EnableRemoveEvent OnEnableTripRemove;

        //we cant add rows with null foreign key
        public delegate void EnableAddEvent(bool state);
        public event EnableAddEvent OnEnableTripAdd;
        public event EnableAddEvent OnEnableTicketAdd;

        private void TrainsPositionChanged(object sender, EventArgs e)
        {
            //check removing
            CheckTrainCascadeReference();
            //check trip & ticket adding
            OnEnableTripAdd?.Invoke(trains.Count != 0);
            OnEnableTicketAdd?.Invoke(trains.Count != 0 && trips.Count != 0);
        }

        private void TripsPositionChanged(object sender, EventArgs e)
        {
            //check removing trip 
            CheckTripCascadeReference();

            //check trip & ticket adding
            OnEnableTripAdd?.Invoke(trains.Count != 0);
            OnEnableTicketAdd?.Invoke(trains.Count != 0 && trips.Count != 0);
        }

        void CheckTrainCascadeReference()
        {
            if ((OnEnableTrainRemove == null) ||
                (bsTrains.Current == null)) return;

            //state based on trips and tickets refs
            int id = (bsTrains.Current as Train).TrainNumber;
            bool state =
                !trips.Exists(p => p.TrainNumber == id)&&
                !tickets.Exists(p => p.TrainNumber == id);

            OnEnableTrainRemove(state);
        }

        void CheckTripCascadeReference()
        {
            if ((OnEnableTripRemove == null) ||
                (bsTrips.Current == null)) return;

            //state based in tickets refs
            string id = (bsTrips.Current as Trip).PK;
            bool state =
                !tickets.Exists(p => p.FK == id);

            OnEnableTripRemove(state);
        }

        private void TicketsListChanged(object sender, System.ComponentModel.ListChangedEventArgs e)
        {
            //check refs in trains & tickets
            CheckTrainCascadeReference();
            CheckTripCascadeReference();
        }
        
        private void AddingNewTicket(object sender, System.ComponentModel.AddingNewEventArgs e)
        {
            //add new ticket with foreign key based on current trip
            e.NewObject = new Ticket()
            {
                FK = (bsTrips.Current as Trip).PK
            };
        }

        private void AddingNewTrip(object sender, System.ComponentModel.AddingNewEventArgs e)
        {
            //add new trip with foreign key based on current train
            e.NewObject = new Trip()
            {
                TrainNumber = (bsTrains.Current as Train).TrainNumber
            };
        }

        private void TrainsOrTripsListChanged(object sender, System.ComponentModel.ListChangedEventArgs e)
        {
            try
            {
                //allow new if trips and trains exist
                bsTickets.AllowNew = (bsTrips.Count) > 0 && (bsTrains.Count > 0);
                CheckTrainCascadeReference();
                CheckTripCascadeReference();
                
            }
            catch
            {
                bsTickets.AllowNew = false;
            }
        }

        private void TrainsListChanged(object sender, System.ComponentModel.ListChangedEventArgs e)
        {
            try
            {
                //allow new if train exist
                bsTrips.AllowNew = (bsTrains.Count) > 0;
                CheckTrainCascadeReference();
            }
            catch
            {
                bsTrips.AllowNew = false;
            }
        }
    }
}
