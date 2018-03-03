using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace RailwayTrips.Data
{
    /// <summary>
    /// trip collection
    /// </summary>
    public class Trips : List<Trip>
    {
        public Trips() { }
    }

    /// <summary>
    /// railway trip
    /// </summary>
    public class Trip
    {
        
        /// <summary>
        /// part of primary key
        /// </summary>
        [DisplayName("Дата поездки")] public DateTime TripDate { get; set; }

        /// <summary>
        /// foreign key, part of primary key
        /// </summary>
        [DisplayName("Номер поезда")] public int TrainNumber { get; set; }

        /// <summary>
        /// pk string contents trainNumber#tripDate
        /// </summary>
        [DisplayName("PK Check")] public string PK
        {
            get
            {
                return TrainNumber.ToString()+"#"+TripDate.ToString("dd.MM.yy");
            }

        }
        [DisplayName("Число купейных мест")] public int CompartmentPlaces { get; set; }
        [DisplayName("Число плацкартных мест")] public int ReservedPlaces { get; set; }
        [DisplayName("Число общих мест")] public int CommonPlaces { get; set; }

        /// <summary>
        /// create empty
        /// </summary>
        public Trip()
        {
            //init fields default values
            //we dont init fk
            TripDate = DateTime.Now;
            CompartmentPlaces = ReservedPlaces = CommonPlaces = 0;
        }

        public override string ToString()
        {
            return string.Format("{0}, {1} купе {2}, плацкарт {3}, общие{4}",
                TrainNumber,
                TripDate.Day+"."+TripDate.Month+"."+TripDate.Year,
                CompartmentPlaces,
                ReservedPlaces,
                CommonPlaces
            );
        }
    }
}
