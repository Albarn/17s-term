using RailwayTrips.Data;
using System;
using System.Windows.Forms; //

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

        // Таблицы данных предметной области 
        readonly Trains trains = new Trains();
        readonly Trips trips = new Trips();
        readonly Tickets tickets = new Tickets();

        // Ссылки на объекты привязки данных 
        private BindingSource bsTrains;
        private BindingSource bsTrips;
        private BindingSource bsTickets;

        // Организация логики и ограничений удаления/добавления данных через события объектов привязки данных 
        public BindingSource BindingTrains
        {
            get { return bsTrains; }
            set
            {
                bsTrains = value;
                value.DataSource = trains;
                value.ListChanged += TrainsOrTripsListChanged;
                value.ListChanged += TrainsListChanged;
                //value.PositionChanged += TrainsPositionChanged;
            }
        }

        public BindingSource BindingTrips
        {
            get { return bsTrips; }
            set
            {
                bsTrips = value;
                value.DataSource = trips;
                value.AddingNew += AddingNewTrip;
                value.ListChanged += TrainsOrTripsListChanged;
                //value.PositionChanged += TripsPositionChanged;
            }
        }

        public BindingSource BindingTickets
        {
            get { return bsTickets; }
            set
            {
                bsTickets = value;
                value.DataSource = tickets;
                value.AddingNew += AddingNewTicket;
                value.ListChanged += TicketsListChanged;
            }
        }

        // Контроль наличия каскадной связи и блокировка удаления связанных записей 
        public delegate void EnableRemoveEvent(bool state);
        public event EnableRemoveEvent OnEnableTrainRemove;
        public event EnableRemoveEvent OnEnableTripRemove;

        private void TrainsPositionChanged(object sender, EventArgs e)
        {
            CheckTrainCascadeReference();
        }

        private void TripsPositionChanged(object sender, EventArgs e)
        {
            CheckTripCascadeReference();
        }

        void CheckTrainCascadeReference()
        {
            if ((OnEnableTrainRemove == null) ||
                (bsTrains.Current == null)) return;

            int id = (bsTrains.Current as Train).TrainNumber;
            bool state =
                !trips.Exists(p => p.TrainNumber == id)||
                !tickets.Exists(p => p.TrainNumber == id);

            OnEnableTrainRemove(state);
        }

        void CheckTripCascadeReference()
        {
            if ((OnEnableTripRemove == null) ||
                (bsTrips.Current == null)) return;

            string id = (bsTrips.Current as Trip).PK;
            bool state =
                !tickets.Exists(p => p.FK == id);
                //!registry.Exists(p => p.idDisease == id);

            OnEnableTripRemove(state);
        }

        private void TicketsListChanged(object sender, System.ComponentModel.ListChangedEventArgs e)
        {
            CheckTrainCascadeReference();
            CheckTripCascadeReference();
        }

        // Контроль и добавление записи в картотеку 
        private void AddingNewTicket(object sender, System.ComponentModel.AddingNewEventArgs e)
        {
            e.NewObject = new Ticket()
            {
                TripDate = (bsTrips.Current as Trip).TripDate,
                TrainNumber = (bsTrains.Current as Train).TrainNumber
            };
        }

        private void AddingNewTrip(object sender, System.ComponentModel.AddingNewEventArgs e)
        {
            e.NewObject = new Trip()
            {
                TrainNumber = (bsTrains.Current as Train).TrainNumber
            };
        }

        private void TrainsOrTripsListChanged(object sender, System.ComponentModel.ListChangedEventArgs e)
        {
            try
            {
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
