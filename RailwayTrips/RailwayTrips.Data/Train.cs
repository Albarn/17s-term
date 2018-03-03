using System;
using System.Collections.Generic;
using System.ComponentModel;

namespace RailwayTrips.Data
{
    /// <summary>
    /// train collection
    /// </summary>
    public class Trains : List<Train>
    {
        public Trains(){ }
    }

    /// <summary>
    /// railway train
    /// </summary>
    public class Train
    {
        /// <summary>
        /// count of created objects(for primary key)
        /// </summary>
        private static int Count = 0;
        
        /// <summary>
        /// primary key
        /// </summary>
        [DisplayName("Номер поезда")] public int TrainNumber { get; set; }
        [DisplayName("Место отправления")] public string DeparturePlace { get; set; }
        [DisplayName("Место следования")] public string ArrivalPlace { get; set; }
        [DisplayName("Время отправления")] public DateTime DepartureTime { get; set; }
        [DisplayName("Время прибытия")] public DateTime ArrivalTime { get; set; }

        /// <summary>
        /// creaty empry
        /// </summary>
        public Train()
        {
            //init pk with count
            //and other fields with default
            TrainNumber = Count++;
            DeparturePlace = ArrivalPlace = "<Place>";
            DepartureTime = ArrivalTime = DateTime.Now;
        }

        public override string ToString()
        {
            //string format of train object
            return string.Format("{0}, {1}-{2}, {3}-{4}",
                TrainNumber,
                DeparturePlace,
                ArrivalPlace,
                DepartureTime.Hour+":"+DepartureTime.Minute,
                ArrivalTime.Hour + ":" + ArrivalTime.Minute
            );
        }
    }
}
